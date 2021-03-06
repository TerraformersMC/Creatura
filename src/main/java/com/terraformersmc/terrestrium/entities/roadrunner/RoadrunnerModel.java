package com.terraformersmc.terrestrium.entities.roadrunner;

import com.terraformersmc.terrestrium.entities.AnimatedEntityEntry;
import com.terraformersmc.terrestrium.entities.AnimatedModel;
import net.minecraft.client.model.Cuboid;

public class RoadrunnerModel extends AnimatedModel<RoadrunnerEntity> {
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
		this.rotateCuboid(leg_right, 0.42F, 0.0F, 0.0F);
		this.head_feathers = new Cuboid(this, 2, 13);
		this.head_feathers.setRotationPoint(0.0F, 0.5F, 0.0F);
		this.head_feathers.addBox(-0.5F, -1.8F, -2.2F, 1, 3, 3, -0.6F);
		this.rotateCuboid(head_feathers, -0.36F, 0.0F, 0.0F);
		this.tail_feathers_middle = new Cuboid(this, 12, 12);
		this.tail_feathers_middle.setRotationPoint(0.0F, 0.0F, 0.9F);
		this.tail_feathers_middle.addBox(-1.0F, 0.0F, 0.0F, 2, 1, 7, -0.1F);
		this.rotateCuboid(tail_feathers_middle, 0.41F, 0.0F, 0.0F);
		this.body = new Cuboid(this, 14, 0);
		this.body.setRotationPoint(-1.0F, 17.0F, -2.5F);
		this.body.addBox(0.5F, 0.0F, 0.0F, 2, 3, 5, 0.0F);
		this.rotateCuboid(body, -0.42F, 0.0F, 0.0F);
		this.wing_right = new Cuboid(this, 28, 0);
		this.wing_right.setRotationPoint(0.3F, -0.1F, 0.0F);
		this.wing_right.addBox(-0.3F, 0.0F, 0.0F, 1, 3, 6, -0.3F);
		this.rotateCuboid(wing_right,0.0F, 0.0F, 0.21F);
		this.beak = new Cuboid(this, 22, 21);
		this.beak.setRotationPoint(0.0F, 0.6F, -0.3F);
		this.beak.addBox(-1.0F, -1.0F, 0.0F, 2, 4, 2, -0.6F);
		this.rotateCuboid(beak, 0.18F, 0.0F, 0.0F);
		this.head = new Cuboid(this, 13, 23);
		this.head.setRotationPoint(0.0F, -0.3F, -3.0F);
		this.head.addBox(-1.0F, -0.6F, -0.5F, 2, 2, 2, -0.2F);
		this.rotateCuboid( head, -0.55F, 0.0F, 0.0F);
		this.neck = new Cuboid(this, 0, 21);
		this.neck.setRotationPoint(1.5F, 0.7F, 0.3F);
		this.neck.addBox(-1.0F, -0.9F, -2.8F, 2, 2, 4, -0.5F);
		this.rotateCuboid(neck,-0.87F, 0.0F, 0.0F);
		this.leg_left = new Cuboid(this, 27, 12);
		this.leg_left.setRotationPoint(0.0F, 2.0F, 3.0F);
		this.leg_left.addBox(0.0F, 0.0F, 0.0F, 1, 4, 1, 0.0F);
		this.rotateCuboid(leg_left, 0.42F, 0.0F, 0.0F);
		this.tail_base = new Cuboid(this, 17, 8);
		this.tail_base.setRotationPoint(1.5F, 0.4F, 4.0F);
		this.tail_base.addBox(-1.0F, 0.0F, -0.8F, 2, 2, 2, 0.0F);
		this.rotateCuboid(tail_base, 0.36F, 0.0F, 0.0F);
		this.wing_left = new Cuboid(this, 0, 0);
		this.wing_left.setRotationPoint(2.0F, 0.0F, 0.0F);
		this.wing_left.addBox(0.0F, 0.0F, 0.0F, 1, 3, 6, -0.3F);
		this.rotateCuboid(wing_left, 0.0F, 0.0F, -0.21F);
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
	public void render(RoadrunnerEntity entity_1, float float_1, float float_2, float float_3, float float_4, float float_5, float float_6) {
		this.body.render(float_6);
	}

	/**
	 * Play the animation {@code walk}, which is 10 ticks long
	 * @param entry The entry to run the animation on
	 * @param ticksDone the amount of ticks that this animation has been running for. This doesn't have to start at 0
	 * This method is generated from DumbCode Animation Studio v0.3.9
	 */
	private void playAnimationWalk(AnimatedEntityEntry entry, float ticksDone) {
		ticksDone %= 10;  //Comment this for the animation NOT to loop

		int snapshotID;
		if(ticksDone < 5) snapshotID = 0;
		else snapshotID = 1;
		entry.ensureSnapshot("walk", snapshotID);

		if (ticksDone > 0) {
			float percentage = (ticksDone - 0F) / 5F;
			if(percentage > 1F) percentage = 1F;
			entry.setTransforms(this.beak, 0F, 0.6F, -0.3F, 0.182F, 0F, 0F, percentage);
			entry.setTransforms(this.head_feathers, 0F, 0.5F, 0F, -0.364F, 0F, 0F, percentage);
			entry.setTransforms(this.head, 0F, -0.3F, -2.5F, -1.309F, 0F, 0F, percentage);
			entry.setTransforms(this.neck, 1.5F, 0.7F, 0.3F, -0.175F, 0F, 0F, percentage);
			entry.setTransforms(this.tail_feathers_middle, 0F, 0F, 0.9F, 0F, 0F, 0F, percentage);
			entry.setTransforms(this.tail_base, 1.5F, 0.4F, 4F, 0F, 0F, 0F, percentage);
			entry.setTransforms(this.wing_left, 2F, 0F, 0F, 0F, 0F, -0.209F, percentage);
			entry.setTransforms(this.wing_right, 0.3F, -0.1F, 0F, 0F, 0F, 0.209F, percentage);
			entry.setTransforms(this.leg_left, 0F, 2F, 3F, -0.785F, 0F, 0F, percentage);
			entry.setTransforms(this.leg_right, 3F, 2F, 3F, 0.785F, 0F, 0F, percentage);
			entry.setTransforms(this.body, -1F, 18F, -2.5F, 0F, 0F, 0F, percentage);
		}
		if (ticksDone > 5) {
			float percentage = (ticksDone - 5F) / 5F;
			if(percentage > 1F) percentage = 1F;
			entry.setTransforms(this.neck, 1.5F, 0.7F, 0.3F, -0.262F, 0F, 0F, percentage);
			entry.setTransforms(this.tail_feathers_middle, 0F, 0F, 0.9F, -0.087F, 0F, 0F, percentage);
			entry.setTransforms(this.leg_left, 0F, 2F, 3F, 0.785F, 0F, 0F, percentage);
			entry.setTransforms(this.leg_right, 3F, 2F, 3F, -0.785F, 0F, 0F, percentage);
		}

	}

	@Override
	protected void runAnimations(RoadrunnerEntity entity, AnimatedEntityEntry entry, float partialTicks) {
		if (entity.getVelocity().getX() != 0.0 || entity.getVelocity().getZ() != 0.0) {
			if (entity.isInsideWater()) {
				this.playAnimationWalk(entry, entry.getTicksDone(partialTicks));
			}
			this.playAnimationWalk(entry, entry.getTicksDone(partialTicks * 2));
		} else {
			this.stopAnimation(entry, entry.getTicksDone(partialTicks));
		}
	}

	@Override
	protected AnimatedEntityEntry getEntry(RoadrunnerEntity entity) {
		if(entity.entry == null) {
			entity.entry = new AnimatedEntityEntry(entity, this.defaultMap);
		}
		return entity.entry;
	}
}
