package com.terraformersmc.terrestrium.entities;

import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

import java.util.HashMap;
import java.util.Map;

/**
 * The animated entry for a specified entity. This stores the information about the snapshot and the next transformations.
 * @author Dumbcode Animation Editor
 * @author Wyn Price
 */
public final class AnimatedEntityEntry {

	/**
	 * This entries Entity
	 */
	private final Entity entity;

	/**
	 * Contains the information about the current snapshot (where the cubes where at the start of this "pose". Used for interpolation)
	 */
	private final Map<Cuboid, float[]> snapshotMap = new HashMap<>();

	/**
	 * Contains the current information for the cubes. This information will be what the cubes are set to.
	 */
	private final Map<Cuboid, float[]> currentTransformsMap = new HashMap<>();

	/**
	 * The current name of the snapshot. Will be in the format `$group$id`
	 */
	private String snapshotName = "";

	/**
	 * The name of the current group of the snapshot. When this changes, the ticksOffset (and therefore ticksDone) is reset.
	 */
	private String snapshotGroup = "";
	/**
	 * The ticks since the current animation has started.
	 */
	private float tickOffset;

	public AnimatedEntityEntry(Entity entity, Map<Cuboid, float[]> defaultMap) {
		this.entity = entity;
		defaultMap.forEach((cuboid, floats) -> this.currentTransformsMap.put(cuboid, new float[]{floats[0], floats[1], floats[2], floats[3], floats[4], floats[5]}));
	}

	/**
	 * Makes sure that the current snapshot is active. If not, then a new snapshot is taken
	 * (meaning that the model's cubes current rotations and positions are captured and stored for interpolating.
	 *
	 * @param snapshotGroup the group name of the current snapshot
	 * @param id            id of the current snapshot
	 */
	public void ensureSnapshot(String snapshotGroup, int id) {
		String snapshotName = snapshotGroup + id;
		if (this.snapshotName.equals(snapshotName)) {
			return;
		}
		if (!this.snapshotGroup.equals(snapshotGroup)) {
			this.tickOffset = this.entity.age;
			this.snapshotGroup = snapshotGroup;
		}
		this.snapshotName = snapshotName;

		this.currentTransformsMap.forEach((renderer, floats) -> this.snapshotMap.put(renderer, new float[]{floats[0], floats[1], floats[2], floats[3], floats[4], floats[5]}));
	}

	/**
	 * Sets the transforms to the model renderer
	 *
	 * @param cuboid    the model renderer to set the transforms on
	 * @param positionX the x position (rotation point) to set to
	 * @param positionY the y position (rotation point) to set to
	 * @param positionZ the z position (rotation point) to set to
	 * @param rotateX   the x angle (in radians) to set to
	 * @param rotateY   the y angle (in radians) to set to
	 * @param rotateZ   the z angle (in radians) to set to
	 * @param alpha     the interpolation amount. 0 would means the transforms are set to the current snapshot. 1 means the transforms are set exactly as the parameters
	 */
	public void setTransforms(Cuboid cuboid, float positionX, float positionY, float positionZ, float rotateX, float rotateY, float rotateZ, float alpha) {
		final float[] snapshot = this.snapshotMap.get(cuboid);
		final float[] currentTransforms = this.currentTransformsMap.get(cuboid);

		currentTransforms[0] = snapshot[0] + (positionX - snapshot[0]) * alpha;
		currentTransforms[1] = snapshot[1] + (positionY - snapshot[1]) * alpha;
		currentTransforms[2] = snapshot[2] + (positionZ - snapshot[2]) * alpha;

		currentTransforms[3] = snapshot[3] + (rotateX - snapshot[3]) * alpha;
		currentTransforms[4] = snapshot[4] + (rotateY - snapshot[4]) * alpha;
		currentTransforms[5] = snapshot[5] + (rotateZ - snapshot[5]) * alpha;
	}

	public void applyTransforms() {
		this.currentTransformsMap.forEach((cuboid, data) -> {
			cuboid.rotationPointX = data[0];
			cuboid.rotationPointY = data[1];
			cuboid.rotationPointZ = data[2];

			cuboid.pitch = data[3];
			cuboid.yaw = data[4];
			cuboid.roll = data[5];
		});
	}

	/**
	 * Get the internal ticks done. This is optional
	 *
	 * @param partialTicks the interpolation between ticks
	 * @return the internal ticks done. See: https://github.com/Dumb-Code/Dumb-Code.github.io/wiki/Ticks-Done
	 */
	public float getTicksDone(float partialTicks) {
		return this.entity.age + partialTicks - this.tickOffset;
	}
}
