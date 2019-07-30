package com.terraformersmc.terrestrium.entities.roadrunner;

import net.minecraft.client.model.Cuboid;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class RoadrunnerModel<T extends Entity> extends EntityModel<T> {
	public Cuboid body;
	public Cuboid neck;
	public Cuboid tail_base;
	public Cuboid wing_left;
	public Cuboid wing_right;
	public Cuboid leg_left;
	public Cuboid leg_right;
	public Cuboid head;
	public Cuboid beak;
	public Cuboid head_feathers;
	public Cuboid tail_feathers_middle;

	private final Map<Cuboid, float[]> snapshotMap = new HashMap<>();
	private String snapshotName = "";

	public RoadrunnerModel() {
		this.textureWidth = 44;
		this.textureHeight = 28;
		this.wing_right = new Cuboid(this, 28, 0);
		this.wing_right.setRotationPoint(0.3F, -0.1F, 0.0F);
		this.wing_right.addBox(-0.3F, 0.0F, 0.0F, 1, 3, 6, -0.3F);
		this.wing_right.setRotationPoint(0.0F, 0.0F, 0.20943951023931953F);
		this.leg_right = new Cuboid(this, 12, 12);
		this.leg_right.setRotationPoint(3.0F, 2.0F, 3.0F);
		this.leg_right.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
		this.leg_right.setRotationPoint(0.41887902047863906F, 0.0F, 0.0F);
		this.wing_left = new Cuboid(this, 0, 0);
		this.wing_left.setRotationPoint(2.0F, 0.0F, 0.0F);
		this.wing_left.addBox(0.0F, 0.0F, 0.0F, 1, 3, 6, -0.3F);
		this.wing_left.setRotationPoint(0.0F, 0.0F, -0.20943951023931953F);
		this.leg_left = new Cuboid(this, 27, 12);
		this.leg_left.setRotationPoint(0.0F, 2.0F, 3.0F);
		this.leg_left.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
		this.leg_left.setRotationPoint(0.41887902047863906F, 0.0F, 0.0F);
		this.tail_base = new Cuboid(this, 17, 8);
		this.tail_base.setRotationPoint(1.5F, 0.4F, 4.0F);
		this.tail_base.addBox(-1.0F, 0.0F, -0.8F, 2, 2, 2, 0.0F);
		this.tail_base.setRotationPoint(0.36425021489121656F, 0.0F, 0.0F);
		this.neck = new Cuboid(this, 0, 21);
		this.neck.setRotationPoint(1.5F, 0.7F, 0.3F);
		this.neck.addBox(-1.0F, -0.9F, -2.8F, 2, 2, 4, -0.5F);
		this.neck.setRotationPoint(-0.8651597102135892F, 0.0F, 0.0F);
		this.body = new Cuboid(this, 14, 0);
		this.body.setRotationPoint(-1.0F, 17.0F, -2.5F);
		this.body.addBox(0.5F, 0.0F, 0.0F, 2, 3, 5, 0.0F);
		this.body.setRotationPoint(-0.41887902047863906F, 0.0F, 0.0F);
		this.head_feathers = new Cuboid(this, 2, 13);
		this.head_feathers.setRotationPoint(0.0F, 0.5F, 0.0F);
		this.head_feathers.addBox(-0.5F, -1.8F, -2.2F, 1, 3, 3, -0.6F);
		this.head_feathers.setRotationPoint(-0.36425021489121656F, 0.0F, 0.0F);
		this.head = new Cuboid(this, 13, 23);
		this.head.setRotationPoint(0.0F, -0.3F, -3.0F);
		this.head.addBox(-1.0F, -0.6F, -0.5F, 2, 2, 2, -0.2F);
		this.head.setRotationPoint(-0.5462880558742251F, 0.0F, 0.0F);
		this.beak = new Cuboid(this, 22, 21);
		this.beak.setRotationPoint(0.0F, 0.6F, -0.3F);
		this.beak.addBox(-1.0F, -1.0F, 0.0F, 2, 4, 2, -0.6F);
		this.beak.setRotationPoint(0.18203784098300857F, 0.0F, 0.0F);
		this.tail_feathers_middle = new Cuboid(this, 12, 12);
		this.tail_feathers_middle.setRotationPoint(0.0F, 0.0F, 0.9F);
		this.tail_feathers_middle.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 7, -0.1F);
		this.tail_feathers_middle.setRotationPoint(0.40980330836826856F, 0.0F, 0.0F);
		this.head.addChild(this.head_feathers);
		this.head.addChild(this.beak);
		this.neck.addChild(this.head);
		this.tail_base.addChild(this.tail_feathers_middle);
		this.body.addChild(this.wing_right);
		this.body.addChild(this.leg_right);
		this.body.addChild(this.wing_left);
		this.body.addChild(this.leg_left);
		this.body.addChild(this.tail_base);
		this.body.addChild(this.neck);
	}

	@Override
	public void setAngles(T entity_1, float float_1, float float_2, float float_3, float float_4, float float_5, float float_6) {
		super.setAngles(entity_1, float_1, float_2, float_3, float_4, float_5, float_6);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.body.render(f5);
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

	private void playAnimationWalk(float ticksDone) {
		ticksDone %= 11.35;  //Uncomment this for the animation to loop
		if (ticksDone > -0.34999999999999787) {
			this.ensureSnapshot("walk0");
			float percentage = (ticksDone - -0.34999999999999787F) / 0.00001F;
			if (percentage > 1F) {
				percentage = 1F;
			}
			this.setTransforms(this.beak, 0F, 14.4F, -4.8F, -0.182F, 0F, 0F, percentage);
			this.setTransforms(this.head_feathers, 0F, 16F, 0F, 0.364F, 0F, 0F, percentage);
			this.setTransforms(this.head, 0F, 28.8F, -48F, 0.546F, 0F, 0F, percentage);
			this.setTransforms(this.neck, -24F, 12.8F, 4.8F, 0.865F, 0F, 0F, percentage);
			this.setTransforms(this.tail_feathers_middle, 0F, 24F, 14.4F, -0.41F, 0F, 0F, percentage);
			this.setTransforms(this.tail_base, -24F, 17.6F, 64F, -0.364F, 0F, 0F, percentage);
			this.setTransforms(this.wing_left, -32F, 24F, 0F, 0F, 0F, -0.209F, percentage);
			this.setTransforms(this.wing_right, -4.8F, 25.6F, 0F, 0F, 0F, 0.209F, percentage);
			this.setTransforms(this.leg_left, 0F, -8F, 48F, -0.419F, 0F, 0F, percentage);
			this.setTransforms(this.leg_right, -48F, -8F, 48F, -0.419F, 0F, 0F, percentage);
			this.setTransforms(this.body, 16F, -248F, -40F, 0.419F, 0F, 0F, percentage);
		}
		if (ticksDone > -0.349999999999997871) {
			this.ensureSnapshot("walk1");
			float percentage = (ticksDone - -0.349999999999997871F) / 0.34999999999999787F;
			if (percentage > 1F) {
				percentage = 1F;
			}
		}
		if (ticksDone > 0) {
			this.ensureSnapshot("walk2");
			float percentage = (ticksDone - 0F) / 1.35F;
			if (percentage > 1F) {
				percentage = 1F;
			}
			this.setTransforms(this.tail_base, -24F, 17.6F, 64F, 0.012F, 0F, 0F, percentage);
		}
		if (ticksDone > 1.35) {
			this.ensureSnapshot("walk3");
			float percentage = (ticksDone - 1.35F) / 5F;
			if (percentage > 1F) {
				percentage = 1F;
			}
			this.setTransforms(this.head, 0F, 28.8F, -48F, 0.511F, 0F, 0F, percentage);
			this.setTransforms(this.neck, -24F, 12.8F, 4.8F, 0.686F, 0F, 0F, percentage);
			this.setTransforms(this.leg_left, 0F, -8F, 48F, 0.424F, 0F, 0F, percentage);
			this.setTransforms(this.body, 16F, -248F, -40F, 0.12F, 0F, 0F, percentage);
		}
		if (ticksDone > 6.35) {
			this.ensureSnapshot("walk4");
			float percentage = (ticksDone - 6.35F) / 5F;
			if (percentage > 1F) {
				percentage = 1F;
			}
			this.setTransforms(this.tail_base, -24F, 17.6F, 64F, 0.12F, 0F, 0F, percentage);
			this.setTransforms(this.leg_left, 0F, -8F, 48F, -0.401F, 0F, 0F, percentage);
			this.setTransforms(this.leg_right, -48F, -8F, 48F, 0.403F, 0F, 0F, percentage);
			this.setTransforms(this.body, 16F, -248F, -40F, 0.251F, 0F, 0F, percentage);
		}
	}
}
