package com.terraformersmc.terrestrium.entities.worm;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class WormModel<T extends Entity> extends EntityModel<T> {
	public Cuboid shape1;
	public Cuboid shape2;
	public Cuboid shape3;

	private final Map<Cuboid, float[]> snapshotMap = new HashMap<>();
	private String snapshotName = "";

	public WormModel() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.shape1 = new Cuboid(this, 0, 0);
		this.shape1.setRotationPoint(-3.0F, 23.0F, 0.0F);
		this.shape1.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
		this.shape2 = new Cuboid(this, 7, 0);
		this.shape2.setRotationPoint(2.0F, 0.0F, 0.0F);
		this.shape2.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
		this.shape3 = new Cuboid(this, 14, 0);
		this.shape3.setRotationPoint(2.0F, 0.0F, 0.0F);
		this.shape3.addBox(0.0F, 0.0F, 0.0F, 2, 1, 1, 0.0F);
		this.shape1.addChild(this.shape2);
		this.shape2.addChild(this.shape3);
	}

	@Override
	public void setAngles(T entity_1, float float_1, float float_2, float float_3, float float_4, float float_5, float float_6) {
		//Just plays it infinitely
		playAnimationWiggle(entity_1.age);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.shape1.render(f5);
	}

	private void ensureSnapshot(String snapshotName) {
		if (this.snapshotName.equals(snapshotName)) {
			return;
		}
		this.snapshotName = snapshotName;
		for (Cuboid renderer : this.cuboidList) {
			this.snapshotMap.put(renderer, new float[]{ renderer.rotationPointX, renderer.rotationPointY, renderer.rotationPointZ, renderer.pitch, renderer.yaw, renderer.roll });
		}
	}

	private void setTransforms(Cuboid cuboid, float positionX, float positionY, float positionZ, float rotateX, float rotateY, float rotateZ, float alpha) {
		final float[] snapshot = this.snapshotMap.get(cuboid);

		cuboid.rotationPointX = snapshot[0] + (positionX - snapshot[0]) * alpha;
		cuboid.rotationPointY = snapshot[1] + (positionY - snapshot[1]) * alpha;
		cuboid.rotationPointZ = snapshot[2] + (positionZ - snapshot[2]) * alpha;

		cuboid.pitch = snapshot[3] + (rotateX - snapshot[3]) * alpha;
		cuboid.yaw = snapshot[4] + (rotateY - snapshot[4]) * alpha;
		cuboid.roll = snapshot[5] + (rotateZ - snapshot[5]) * alpha;
	}

	private void resetAnimationToIdle(float ticksDone) {
		final float duration = 5F; //This is the time taken to get back to the idle pose in ticks
		if(ticksDone < duration) {
			float percentage = ticksDone / duration;
			this.setTransforms(this.shape3, 2F, 0F, 0F, 0F, 0F, 0F, percentage);
			this.setTransforms(this.shape2, 2F, 0F, 0F, 0F, 0F, 0F, percentage);
			this.setTransforms(this.shape1, -3F, 23F, 0F, 0F, 0F, 0F, percentage);
		}
	}

	private void playAnimationWiggle(float ticksDone) {
		ticksDone %= 30;  //Uncomment this for the animation to loop
		if (ticksDone > 0) {
			if(ticksDone < 10) {
				this.ensureSnapshot("wiggle0");
			}
			float percentage = (ticksDone - 0F) / 10F;
			if(percentage > 1F) {
				percentage = 1F;
			}
			this.setTransforms(this.shape3, 2F, 0F, 0F, 3.142F, 0.367F, 0.443F, percentage);
			this.setTransforms(this.shape2, 2F, 0F, 0F, 0.576F, 0.367F, 0.03F, percentage);
			this.setTransforms(this.shape1, -3F, 23F, 0F, 0F, 0F, 0F, percentage);
		}
		if (ticksDone > 10) {
			if(ticksDone < 20) {
				this.ensureSnapshot("wiggle1");
			}
			float percentage = (ticksDone - 10F) / 10F;
			if(percentage > 1F) {
				percentage = 1F;
			}
			this.setTransforms(this.shape3, 2F, 0F, 0F, 1.491F, 1.04F, -0.513F, percentage);
		}
		if (ticksDone > 20) {
			if(ticksDone < 30) {
				this.ensureSnapshot("wiggle2");
			}
			float percentage = (ticksDone - 20F) / 10F;
			if(percentage > 1F) {
				percentage = 1F;
			}
			this.setTransforms(this.shape1, -3F, 23F, 0F, 0.771F, -0.852F, 1.248F, percentage);
		}
	}
}
