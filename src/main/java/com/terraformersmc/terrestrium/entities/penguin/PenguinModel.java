package com.terraformersmc.terrestrium.entities.penguin;

import com.terraformersmc.terrestrium.entities.AnimatedEntityEntry;
import com.terraformersmc.terrestrium.entities.AnimatedModel;
import net.minecraft.client.model.Cuboid;

public class PenguinModel extends AnimatedModel<PenguinEntity> {
	public Cuboid body;
	public Cuboid foot_left;
	public Cuboid foot_right;
	public Cuboid belly;
	public Cuboid head;
	public Cuboid tail;
	public Cuboid flipper_right;
	public Cuboid flipper_left;
	public Cuboid beak;
	public Cuboid mouth;

	public PenguinModel() {
		this.textureWidth = 64;
		this.textureHeight = 64;
		this.mouth = new Cuboid(this, 36, 26);
		this.mouth.setRotationPoint(0.0F, 0.6F, -1.7F);
		this.mouth.addBox(-1.0F, 0.0F, -4.0F, 2, 1, 2, -0.2F);
		this.belly = new Cuboid(this, 0, 27);
		this.belly.setRotationPoint(-3.5F, -16.2F, -1.5F);
		this.belly.addBox(0.0F, -1.0F, 0.0F, 7, 17, 2, -0.8F);
		this.flipper_left = new Cuboid(this, 15, 42);
		this.flipper_left.setRotationPoint(6.0F, 0.0F, 1.0F);
		this.flipper_left.addBox(0.0F, 0.0F, 0.0F, 2, 11, 4, -0.6F);
		this.rotateCuboid(flipper_left, 0.19198621771937624F, 0.0F, -0.3490658503988659F);
		this.foot_right = new Cuboid(this, 20, 32);
		this.foot_right.setRotationPoint(-1.4F, -1.0F, 1.0F);
		this.foot_right.addBox(-1.0F, 0.0F, 0.0F, 3, 2, 6, -0.4F);
		this.rotateCuboid(foot_right, 0.22689280275926282F, -2.792526803190927F, 0.0F);
		this.tail = new Cuboid(this, 46, 38);
		this.tail.setRotationPoint(0.0F, -3.4F, 5.1F);
		this.tail.addBox(-2.0F, 0.0F, 0.0F, 4, 2, 4, -0.3F);
		this.rotateCuboid(tail, -0.8651597102135892F, 0.0F, 0.0F);
		this.beak = new Cuboid(this, 35, 17);
		this.beak.setRotationPoint(0.0F, -2.8F, 2.2F);
		this.beak.addBox(-1.0F, 0.0F, -6.0F, 2, 1, 3, 0.0F);
		this.head = new Cuboid(this, 32, 0);
		this.head.setRotationPoint(0.0F, -16.9F, 2.6F);
		this.head.addBox(-4.0F, -6.7F, -4.1F, 8, 9, 8, -1.9F);
		this.rotateCuboid(head, -0.27314402793711257F, 0.0F, 0.0F);
		this.body = new Cuboid(this, 0, 0);
		this.body.setRotationPoint(0.0F, 23.9F, -2.0F);
		this.body.addBox(-4.0F, -18.0F, -1.0F, 8, 18, 7, -0.7F);
		this.rotateCuboid(body, 0.22689280275926282F, 0.0F, 0.0F);
		this.foot_left = new Cuboid(this, 20, 32);
		this.foot_left.setRotationPoint(1.4F, -1.0F, 1.0F);
		this.foot_left.addBox(-3.0F, 0.0F, 0.0F, 3, 2, 6, -0.4F);
		this.rotateCuboid(foot_left, 0.22689280275926282F, 2.7890361446869387F, 0.0F);
		this.flipper_right = new Cuboid(this, 33, 42);
		this.flipper_right.setRotationPoint(0.0F, 0.0F, 1.0F);
		this.flipper_right.addBox(-1.0F, 0.0F, 0.0F, 2, 11, 4, -0.6F);
		this.rotateCuboid(flipper_right, 0.19198621771937624F, 0.0F, 0.3490658503988659F);
		this.beak.addChild(this.mouth);
		this.body.addChild(this.belly);
		this.belly.addChild(this.flipper_left);
		this.body.addChild(this.foot_right);
		this.body.addChild(this.tail);
		this.head.addChild(this.beak);
		this.body.addChild(this.head);
		this.body.addChild(this.foot_left);
		this.belly.addChild(this.flipper_right);
	}

	@Override
	public void render(PenguinEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
		this.body.render(f5);
	}

	@Override
	protected void runAnimations(PenguinEntity entity, AnimatedEntityEntry entry, float partialTicks) {
		if (entity.getVelocity().getX() > 0.05 || entity.getVelocity().getZ() > 0.05 || entity.getVelocity().getX() < -0.05 || entity.getVelocity().getZ() < -0.05) {
			playAnimationWalk(entry, entry.getTicksDone(partialTicks));
		} else {
			stopAnimation(entry, entry.getTicksDone(partialTicks));
		}
	}

	@Override
	protected AnimatedEntityEntry getEntry(PenguinEntity entity) {
		if(entity.entry == null) {
			entity.entry = new AnimatedEntityEntry(entity, this.defaultMap);
		}
		return entity.entry;
	}

	/**
	 * Play the animation {@code walk}, which is 10 ticks long
	 * @param entry The entry to run the animation on
	 * @param ticksDone the amount of ticks that this animation has been running for. This doesn't have to start at 0
	 * This method is generated from DumbCode Animation Studio v0.3.10
	 */
	private void playAnimationWalk(AnimatedEntityEntry entry, float ticksDone) {
		ticksDone *= 1; //Speed of the animation

		int snapshotID;
		if(ticksDone < 5) snapshotID = 0;
		else snapshotID = 1;
		entry.ensureSnapshot("walk", snapshotID);

		if (ticksDone > 0) {
			float percentage = (ticksDone - 0F) / 5F;
			if(percentage > 1F) percentage = 1F;
			entry.setTransforms(this.foot_left, 1.4F, -1F, 1F, 0.227F, 2.789F, 0F, percentage);
			entry.setTransforms(this.foot_right, -1.4F, -2.5F, 1F, 0.436F, -2.793F, 0F, percentage);
			entry.setTransforms(this.flipper_right, 0F, 0F, 1F, 0.192F, 0F, 0.698F, percentage);
			entry.setTransforms(this.flipper_left, 6F, 0F, 1F, 0.192F, 0F, -0.698F, percentage);
			entry.setTransforms(this.belly, -3.5F, -16.2F, -1.5F, 0F, 0F, 0F, percentage);
			entry.setTransforms(this.mouth, 0F, 0.6F, -1.7F, 0F, 0F, 0F, percentage);
			entry.setTransforms(this.beak, 0F, -2.8F, 2.2F, 0F, 0F, 0F, percentage);
			entry.setTransforms(this.head, 0F, -16.9F, 2F, -0.273F, 0F, 0F, percentage);
			entry.setTransforms(this.tail, 0F, -3.4F, 5.1F, -0.865F, 0F, 0F, percentage);
			entry.setTransforms(this.body, 0F, 23.9F, -2F, 0.227F, -0.175F, 0F, percentage);
		}
		if (ticksDone > 5) {
			float percentage = (ticksDone - 5F) / 5F;
			if(percentage > 1F) percentage = 1F;
			entry.setTransforms(this.foot_left, 1.4F, -2.5F, 1F, 0.436F, 2.789F, 0F, percentage);
			entry.setTransforms(this.foot_right, -1.4F, -1F, 1F, 0.227F, -2.793F, 0F, percentage);
			entry.setTransforms(this.flipper_right, 0F, 0F, 1F, 0.192F, 0F, 0.785F, percentage);
			entry.setTransforms(this.flipper_left, 6F, 0F, 1F, 0.192F, 0F, -0.785F, percentage);
			entry.setTransforms(this.body, 0F, 23.9F, -2F, 0.227F, 0.175F, 0F, percentage);
		}

	}
}

