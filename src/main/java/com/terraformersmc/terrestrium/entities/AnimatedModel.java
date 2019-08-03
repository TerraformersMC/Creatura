package com.terraformersmc.terrestrium.entities;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * This holds all the base fields, methods and classes, acting like an API to allow for mods to easily animate tabula models.
 * @author Dumbcode Animation Editor
 * @author Wyn Price
 * @param <T> the entity type
 */
public abstract class AnimatedModel<T extends Entity> extends EntityModel<T> {

	/**
	 * Holds information about where the model's cubes are by default
	 */
	protected Map<Cuboid, float[]> defaultMap = null;

	/**
	 * Stores a map of {@link Entity#getEntityId()} to {@link AnimatedEntityEntry}
	 */
	private final Map<Integer, AnimatedEntityEntry> entityEntryMap = new WeakHashMap<>();

	@Override
	public final void animateModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
		if(this.defaultMap == null) {
			this.defaultMap = new HashMap<>();
			for (Cuboid cuboid : this.cuboidList) {
				this.defaultMap.put(cuboid, new float[]{cuboid.rotationPointX, cuboid.rotationPointY, cuboid.rotationPointZ, cuboid.pitch, cuboid.yaw, cuboid.roll });
			}
		}

		this.runAnimations(entity, partialTicks);

		this.getEntry(entity).currentTransformsMap.forEach((renderer, data) -> {
			renderer.rotationPointX = data[0];
			renderer.rotationPointY = data[1];
			renderer.rotationPointZ = data[2];

			renderer.pitch = data[3];
			renderer.yaw = data[4];
			renderer.roll = data[5];
		});
	}

	/**
	 * This method is for calling your animation methods, generated from the animation editor.
	 * @param entity the entity to call the animations on
	 * @param partialTicks the interpolation between ticks
	 */
	protected abstract void runAnimations(T entity, float partialTicks);

	/**
	 * Stops the animations, and returns the model to it's default state. By changing {@code duration}, you can change how long this takes.
	 * @param entity the entity to return the model to the default state.
	 * @param ticksDone the amount of ticks done for this animation. See: https://github.com/Dumb-Code/Dumb-Code.github.io/wiki/Ticks-Done
	 */
	protected void stopAnimation(Entity entity, float ticksDone) {
		final float duration = 5F; //This is the time taken (in ticks) to get back to the idle pose
		this.ensureSnapshot(entity, "@@none@@", 0);
		float percentage = ticksDone / duration;
		if(percentage > 1) percentage = 1F;

		for (Cuboid cuboid : this.cuboidList) {
			float[] floats = this.defaultMap.get(cuboid);
			this.setTransforms(entity, cuboid, floats[0], floats[1], floats[2], floats[3], floats[4], floats[5], percentage);
		}
	}

	/**
	 * Makes sure that the current snapshot is active. If not, then a new snapshot is taken
	 * (meaning that the model's cubes current rotations and positions are captured and stored for interpolating.
	 * @param entity tee entity to get the snapshot from
	 * @param snapshotGroup the group name of the current snapshot
	 * @param id id of the current snapshot
	 */
	protected void ensureSnapshot(Entity entity, String snapshotGroup, int id) {
		String snapshotName = snapshotGroup + id;
		AnimatedEntityEntry entry = this.getEntry(entity);
		if (snapshotName.equals(entry.snapshotName)) {
			return;
		}
		if(!snapshotGroup.equals(entry.snapshotName)) {
			entry.tickOffset = entity.age;
		}
		entry.snapshotName = snapshotName;
		for (Cuboid renderer : this.cuboidList) {
			float[] floats = entry.currentTransformsMap.get(renderer);
			entry.snapshotMap.put(renderer, new float[]{floats[0], floats[1], floats[2], floats[3], floats[4], floats[5]});
		}
	}

	/**
	 * Sets the transforms to the model renderer
	 * @param entity the entity to set the transforms to
	 * @param modelRenderer the model renderer to set the transforms on
	 * @param positionX the x position (rotation point) to set to 
	 * @param positionY the y position (rotation point) to set to 
	 * @param positionZ the z position (rotation point) to set to 
	 * @param rotateX the x angle (in radians) to set to
	 * @param rotateY the y angle (in radians) to set to
	 * @param rotateZ the z angle (in radians) to set to
	 * @param alpha the interpolation amount. 0 would means the transforms are set to the current snapshot. 1 means the transforms are set exactly as the parameters
	 */
	protected void setTransforms(Entity entity, Cuboid modelRenderer, float positionX, float positionY, float positionZ, float rotateX, float rotateY, float rotateZ, float alpha) {
		AnimatedEntityEntry entry = this.getEntry(entity);

		final float[] snapshot = entry.snapshotMap.get(modelRenderer);
		final float[] currentTransforms = entry.currentTransformsMap.get(modelRenderer);

		currentTransforms[0] = snapshot[0] + (positionX - snapshot[0]) * alpha;
		currentTransforms[1] = snapshot[1] + (positionY - snapshot[1]) * alpha;
		currentTransforms[2] = snapshot[2] + (positionZ - snapshot[2]) * alpha;

		currentTransforms[3] = snapshot[3] + (rotateX - snapshot[3]) * alpha;
		currentTransforms[4] = snapshot[4] + (rotateY - snapshot[4]) * alpha;
		currentTransforms[5] = snapshot[5] + (rotateZ - snapshot[5]) * alpha;
	}

	/**
	 * Get the internal ticks done. This is optional
	 * @param entity the entity to use
	 * @param partialTicks the interpolation between ticks
	 * @return the internal ticks done. See: https://github.com/Dumb-Code/Dumb-Code.github.io/wiki/Ticks-Done
	 */
	protected float getTicksDone(Entity entity, float partialTicks) {
		AnimatedEntityEntry entry = this.getEntry(entity);
		return entity.age + partialTicks - entry.tickOffset;
	}

	/**
	 * Gets or creates the specified entry for the entity
	 * @param entity the entity to get or create the entry from
	 * @return the already generated entry, or a new entry if one was not found
	 */
	protected AnimatedEntityEntry getEntry(Entity entity) {
		return this.entityEntryMap.computeIfAbsent(entity.getEntityId(), i -> new AnimatedEntityEntry());
	}

	protected class AnimatedEntityEntry {
		protected final Map<Cuboid, float[]> snapshotMap = new HashMap<>();
		protected final Map<Cuboid, float[]> currentTransformsMap = new HashMap<>();

		protected int tickOffset = 0;

		protected String snapshotName = "";

		AnimatedEntityEntry() {
			for (Cuboid renderer : AnimatedModel.this.cuboidList) {
				float[] floats = AnimatedModel.this.defaultMap.get(renderer);
				this.currentTransformsMap.put(renderer, new float[]{floats[0], floats[1], floats[2], floats[3], floats[4], floats[5]});
			}
		}
	}

	//Model helper class for setting rotations of the original model.
	public void setRotateAngle(Cuboid cuboid, float x, float y, float z) {
		cuboid.pitch = x;
		cuboid.yaw = y;
		cuboid.roll = z;
	}
}
