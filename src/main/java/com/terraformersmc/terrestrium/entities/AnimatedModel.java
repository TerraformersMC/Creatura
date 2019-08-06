package com.terraformersmc.terrestrium.entities;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

import java.util.HashMap;
import java.util.Map;

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

	@Override
	public void animateModel(T entity, float limbSwing, float limbSwingAmount, float partialTicks) {
		if(this.defaultMap == null) {
			this.defaultMap = new HashMap<>();
			for (Cuboid renderer : this.cuboidList) {
				this.defaultMap.put(renderer, new float[]{renderer.rotationPointX, renderer.rotationPointY, renderer.rotationPointZ, renderer.pitch, renderer.yaw, renderer.roll });
			}
		}

		AnimatedEntityEntry entry = this.getEntry(entity);
		this.runAnimations(entity, entry, partialTicks);
		entry.applyTransforms();
	}

	/**
	 * Stops the animations, and returns the model to it's default state. By changing {@code duration}, you can change how long this takes.
	 * @param entry the animation entry to run the animations on
	 * @param ticksDone the amount of ticks done for this animation. See: https://github.com/Dumb-Code/Dumb-Code.github.io/wiki/Ticks-Done
	 */
	public void stopAnimation(AnimatedEntityEntry entry, float ticksDone) {
		final float duration = 5F; //This is the time taken (in ticks) to get back to the idle pose
		entry.ensureSnapshot("@@none@@", 0);
		float percentage = ticksDone > duration ? 1F : ticksDone / duration;
		this.defaultMap.forEach((renderer, floats) -> entry.setTransforms(renderer, floats[0], floats[1], floats[2], floats[3], floats[4], floats[5], percentage));
	}

	/**
	 * This method is for calling your animation methods, generated from the animation editor.
	 * @param entity the entity to call the animations on
	 * @param entry the animation entry to run the animations on
	 * @param partialTicks the interpolation between ticks
	 */
	protected abstract void runAnimations(T entity, AnimatedEntityEntry entry, float partialTicks);


	/**
	 * Gets the animation entity entry from the specified entity. If there isn't any an entry, one should be generated
	 * @param entity the entity to get the entry from
	 * @return the already existing or generated entry. This SHOULD not be null
	 */
	protected abstract AnimatedEntityEntry getEntry(T entity);

	//Model helper class for setting rotations of the original model.
	public void rotateCuboid(Cuboid cuboid, float x, float y, float z) {
		cuboid.pitch = x;
		cuboid.yaw = y;
		cuboid.roll = z;
	}
}
