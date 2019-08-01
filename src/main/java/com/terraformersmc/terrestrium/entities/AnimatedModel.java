package com.terraformersmc.terrestrium.entities;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

import java.util.Map;
import java.util.WeakHashMap;

public abstract class AnimatedModel<T extends Entity> extends EntityModel<T> {

	//Animation stuff
	private final Map<Entity, Map<Cuboid, float[]>> snapshotMap = new WeakHashMap<>();
	private final Map<Entity, Map<Cuboid, float[]>> currentTransformsMap = new WeakHashMap<>();
	private Map<Entity, String> snapshotName = new WeakHashMap<>();

	public Map<Entity, Map<Cuboid, float[]>> getSnapshotMap() {
		return snapshotMap;
	}

	public Map<Entity, Map<Cuboid, float[]>> getCurrentTransformsMap() {
		return currentTransformsMap;
	}

	@Override
	public abstract void setAngles(Entity entity, float float_1, float float_2, float float_3, float float_4, float float_5, float float_6);

	protected abstract void stopAnimation(Entity entity, float ticksDone);

	//Animation helpers
	protected void ensureSnapshot(Entity entity, String snapshotName) {
		if (snapshotName.equals(this.snapshotName.get(entity))) {
			return;
		}
		this.snapshotName.put(entity, snapshotName);
		Map<Cuboid, float[]> rendererMap = snapshotMap.get(entity);
		Map<Cuboid, float[]> transformsMap = this.currentTransformsMap.get(entity);
		for (Cuboid renderer : this.cuboidList) {
			float[] floats = transformsMap.get(renderer);
			rendererMap.put(renderer, new float[]{floats[0], floats[1], floats[2], floats[3], floats[4], floats[5]});
		}
	}

	protected void setTransforms(Entity entity, Cuboid modelRenderer, float positionX, float positionY, float positionZ, float rotateX, float rotateY, float rotateZ, float alpha) {
		final float[] snapshot = this.snapshotMap.get(entity).get(modelRenderer);
		final float[] currentTransforms = this.currentTransformsMap.get(entity).get(modelRenderer);

		currentTransforms[0] = snapshot[0] + (positionX - snapshot[0]) * alpha;
		currentTransforms[1] = snapshot[1] + (positionY - snapshot[1]) * alpha;
		currentTransforms[2] = snapshot[2] + (positionZ - snapshot[2]) * alpha;

		currentTransforms[3] = snapshot[3] + (rotateX - snapshot[3]) * alpha;
		currentTransforms[4] = snapshot[4] + (rotateY - snapshot[4]) * alpha;
		currentTransforms[5] = snapshot[5] + (rotateZ - snapshot[5]) * alpha;
	}

	protected void applyAnimations(Entity entity) {
		Map<Cuboid, float[]> rendererMap = this.currentTransformsMap.get(entity);
		rendererMap.forEach((renderer, dat) -> {
			renderer.rotationPointX = dat[0];
			renderer.rotationPointY = dat[1];
			renderer.rotationPointZ = dat[2];

			renderer.pitch = dat[3];
			renderer.yaw = dat[4];
			renderer.roll = dat[5];
		});
	}
}
