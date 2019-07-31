package com.terraformersmc.terrestrium.entities.roadrunner;

import com.terraformersmc.terrestrium.entities.AnimatedModel;
import net.minecraft.client.model.Cuboid;
import net.minecraft.entity.Entity;

import java.util.HashMap;
import java.util.Map;

public class RoadrunnerModel extends AnimatedModel {
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

	public RoadrunnerModel() {
		this.textureWidth = 64;
		this.textureHeight = 32;
		this.leg_right = new Cuboid(this, 12, 12);
		this.leg_right.setRotationPoint(3.0F, 2.0F, 3.0F);
		this.leg_right.addBox(-1.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
		this.leg_right.setRotationPoint(0.41887902047863906F, 0.0F, 0.0F);
		this.head_feathers = new Cuboid(this, 2, 13);
		this.head_feathers.setRotationPoint(0.0F, 0.5F, 0.0F);
		this.head_feathers.addBox(-0.5F, -1.8F, -2.2F, 1, 3, 3, -0.6F);
		this.head_feathers.setRotationPoint(-0.36425021489121656F, 0.0F, 0.0F);
		this.tail_feathers_middle = new Cuboid(this, 12, 12);
		this.tail_feathers_middle.setRotationPoint(0.0F, 0.0F, 0.9F);
		this.tail_feathers_middle.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 7, -0.1F);
		this.tail_feathers_middle.setRotationPoint(0.40980330836826856F, 0.0F, 0.0F);
		this.body = new Cuboid(this, 14, 0);
		this.body.setRotationPoint(-1.0F, 17.0F, -2.5F);
		this.body.addBox(0.5F, 0.0F, 0.0F, 2, 3, 5, 0.0F);
		this.body.setRotationPoint(-0.41887902047863906F, 0.0F, 0.0F);
		this.wing_right = new Cuboid(this, 28, 0);
		this.wing_right.setRotationPoint(0.3F, -0.1F, 0.0F);
		this.wing_right.addBox(-0.3F, 0.0F, 0.0F, 1, 3, 6, -0.3F);
		this.wing_right.setRotationPoint(0.0F, 0.0F, 0.20943951023931953F);
		this.beak = new Cuboid(this, 22, 21);
		this.beak.setRotationPoint(0.0F, 0.6F, -0.3F);
		this.beak.addBox(-1.0F, -1.0F, 0.0F, 2, 4, 2, -0.6F);
		this.beak.setRotationPoint(0.18203784098300857F, 0.0F, 0.0F);
		this.head = new Cuboid(this, 13, 23);
		this.head.setRotationPoint(0.0F, -0.3F, -3.0F);
		this.head.addBox(-1.0F, -0.6F, -0.5F, 2, 2, 2, -0.2F);
		this.head.setRotationPoint(-0.5462880558742251F, 0.0F, 0.0F);
		this.neck = new Cuboid(this, 0, 21);
		this.neck.setRotationPoint(1.5F, 0.7F, 0.3F);
		this.neck.addBox(-1.0F, -0.9F, -2.8F, 2, 2, 4, -0.5F);
		this.neck.setRotationPoint(0.8651597102135892F, 0.0F, 0.0F);
		this.leg_left = new Cuboid(this, 27, 12);
		this.leg_left.setRotationPoint(0.0F, 2.0F, 3.0F);
		this.leg_left.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
		this.leg_left.setRotationPoint(0.41887902047863906F, 0.0F, 0.0F);
		this.tail_base = new Cuboid(this, 17, 8);
		this.tail_base.setRotationPoint(1.5F, 0.4F, 4.0F);
		this.tail_base.addBox(-1.0F, 0.0F, -0.8F, 2, 2, 2, 0.0F);
		this.tail_base.setRotationPoint(0.36425021489121656F, 0.0F, 0.0F);
		this.wing_left = new Cuboid(this, 0, 0);
		this.wing_left.setRotationPoint(2.0F, 0.0F, 0.0F);
		this.wing_left.addBox(0.0F, 0.0F, 0.0F, 1, 3, 6, -0.3F);
		this.wing_left.setRotationPoint(0.0F, 0.0F, -0.20943951023931953F);
		this.body.addChild(this.leg_right);
		this.head.addChild(this.head_feathers);
		this.tail_base.addChild(this.tail_feathers_middle);
		this.body.addChild(this.wing_right);
		this.head.addChild(this.beak);
		this.neck.addChild(this.head);
		this.body.addChild(this.neck);
		this.body.addChild(this.leg_left);
		this.body.addChild(this.tail_base);
		this.body.addChild(this.wing_left);
	}

	@Override
	public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.body.render(f5);
	}

	@Override
	public void setAngles(Entity entity_1, float float_1, float float_2, float float_3, float float_4, float float_5, float float_6) {
		playAnimationWalk(entity_1, entity_1.age);
	}

	@Override
	protected void stopAnimation(Entity entity, float ticksDone) {
		final float duration = 2.5F; //This is the time taken (in ticks) to get back to the idle pose
		if(ticksDone < duration) {
			float percentage = ticksDone / duration;
			this.setTransforms(entity, this.beak, 0F, 0.6F, -0.3F, 10.43F, 0F, 0F, percentage);
			this.setTransforms(entity, this.head_feathers, 0F, 0.5F, 0F, -20.87F, 0F, 0F, percentage);
			this.setTransforms(entity, this.head, 0F, -0.3F, -3F, -31.3F, 0F, 0F, percentage);
			this.setTransforms(entity, this.neck, 1.5F, 0.7F, 0.3F, -49.57F, 0F, 0F, percentage);
			this.setTransforms(entity, this.tail_feathers_middle, 0F, 0F, 0.9F, 23.48F, 0F, 0F, percentage);
			this.setTransforms(entity, this.tail_base, 1.5F, 0.4F, 4F, 20.87F, 0F, 0F, percentage);
			this.setTransforms(entity, this.wing_left, 2F, 0F, 0F, 0F, 0F, -12F, percentage);
			this.setTransforms(entity, this.wing_right, 0.3F, -0.1F, 0F, 0F, 0F, 12F, percentage);
			this.setTransforms(entity, this.leg_left, 0F, 2F, 3F, 24F, 0F, 0F, percentage);
			this.setTransforms(entity, this.leg_right, 3F, 2F, 3F, 24F, 0F, 0F, percentage);
			this.setTransforms(entity, this.body, -1F, 17F, -2.5F, -24F, 0F, 0F, percentage);
		}
	}

	/**
	 * Play the animation {@code walk}, which is 10 ticks long
	 * @param entity The entity to run the animation on
	 * @param ticksDone the amount of ticks that this animation has been running for. Doesn't have to start at 0
	 * This method is generated from DumbCode Animation Studio v0.3.9
	 */
	private void playAnimationWalk(Entity entity, float ticksDone) {
		if(!this.getSnapshotMap().containsKey(entity)) {
			this.getSnapshotMap().put(entity, new HashMap<>());

			Map<Cuboid, float[]> map = new HashMap<>();
			for (Cuboid cuboid : this.cuboidList) {
				map.put(cuboid, new float[6]);
			}
			this.getCurrentTransformsMap().put(entity, map);
		}
		ticksDone %= 5;  //Comment this for the animation NOT to loop
		if (ticksDone > 0) {
			float percentage = (ticksDone - 0F) / 2.5F;
			if(ticksDone < 2.5) this.ensureSnapshot(entity, "walk0");
			else percentage = 1F;
			this.setTransforms(entity, this.beak, 0F, 0.6F, -0.3F, 0.182F, 0F, 0F, percentage);
			this.setTransforms(entity, this.head_feathers, 0F, 0.5F, 0F, -0.364F, 0F, 0F, percentage);
			this.setTransforms(entity, this.head, 0F, -0.3F, -3F, -0.881F, 0F, 0F, percentage);
			this.setTransforms(entity, this.neck, 1.5F, 0.7F, 0.3F, -0.337F, 0F, 0F, percentage);
			this.setTransforms(entity, this.tail_feathers_middle, 0F, 0F, 0.9F, 0.41F, 0F, 0F, percentage);
			this.setTransforms(entity, this.tail_base, 1.5F, 0.4F, 4F, -0.054F, 0F, 0F, percentage);
			this.setTransforms(entity, this.wing_left, 2F, 0F, 0F, 0F, 0F, -0.209F, percentage);
			this.setTransforms(entity, this.wing_right, 0.3F, -0.1F, 0F, 0F, 0F, 0.209F, percentage);
			this.setTransforms(entity, this.leg_left, 0F, 2F, 3F, 0.785F, 0F, 0F, percentage);
			this.setTransforms(entity, this.leg_right, 3F, 2F, 3F, -0.785F, 0F, 0F, percentage);
			this.setTransforms(entity, this.body, -1F, 17.4F, -2.5F, -0.054F, 0F, 0F, percentage);
		}
		if (ticksDone > 2.5) {
			float percentage = (ticksDone - 2.5F) / 2.5F;
			if(ticksDone < 5) this.ensureSnapshot(entity, "walk1");
			else percentage = 1F;
			this.setTransforms(entity, this.head, 0F, -0.3F, -3F, -0.686F, 0F, 0F, percentage);
			this.setTransforms(entity, this.neck, 1.5F, 0.7F, 0.3F, -0.578F, 0F, 0F, percentage);
			this.setTransforms(entity, this.tail_base, 1.5F, 0.4F, 4F, 0.054F, 0F, 0F, percentage);
			this.setTransforms(entity, this.leg_left, 0F, 2F, 3F, -0.785F, 0F, 0F, percentage);
			this.setTransforms(entity, this.leg_right, 3F, 2F, 3F, 0.785F, 0F, 0F, percentage);
			this.setTransforms(entity, this.body, -1F, 17.4F, -2.5F, -0.141F, 0F, 0F, percentage);
		}

		this.applyAnimations(entity);
	}
}
