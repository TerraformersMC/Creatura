package com.terraformersmc.terrestrium.entities.crocodile;

import com.terraformersmc.terrestrium.entities.AnimatedEntityEntry;
import com.terraformersmc.terrestrium.entities.AnimatedModel;
import net.minecraft.client.model.Cuboid;

public class CrocodileModel extends AnimatedModel<CrocodileEntity> {
	public Cuboid body_base;
	public Cuboid body_chest;
	public Cuboid hip;
	public Cuboid collar;
	public Cuboid arm_left;
	public Cuboid arm_right;
	public Cuboid head;
	public Cuboid jaw;
	public Cuboid upper_jaw;
	public Cuboid tooth5;
	public Cuboid tooth6;
	public Cuboid tooth7;
	public Cuboid tooth8;
	public Cuboid tooth9;
	public Cuboid tooth6_1;
	public Cuboid nose;
	public Cuboid tooth1;
	public Cuboid tooth2;
	public Cuboid tooth3;
	public Cuboid tooth4;
	public Cuboid tail_base;
	public Cuboid leg_left;
	public Cuboid leg_right;
	public Cuboid tail_mid;
	public Cuboid tail_tip;

	public CrocodileModel() {
		this.textureWidth = 128;
		this.textureHeight = 128;
		this.collar = new Cuboid(this, 5, 0);
		this.collar.setRotationPoint(0.0F, -1.2F, -9.3F);
		this.collar.addBox(-3.5F, -0.3F, -6.0F, 7, 7, 8, 0.2F);
		this.hip = new Cuboid(this, 3, 53);
		this.hip.setRotationPoint(4.4F, 0.5F, 10.0F);
		this.hip.addBox(-3.5F, 0.0F, 0.0F, 7, 7, 10, 0.0F);
		this.rotateCuboid(hip, -0.06981317007977318F, 0.0F, 0.0F);
		this.tooth6 = new Cuboid(this, 0, 0);
		this.tooth6.setRotationPoint(0.5F, -1.0F, -9.7F);
		this.tooth6.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, -0.2F);
		this.tooth9 = new Cuboid(this, 0, 0);
		this.tooth9.setRotationPoint(0.8F, -1.0F, -5.0F);
		this.tooth9.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, -0.2F);
		this.tail_base = new Cuboid(this, 5, 71);
		this.tail_base.setRotationPoint(0.3F, 0.5F, 9.5F);
		this.tail_base.addBox(-2.8F, 0.0F, 0.0F, 5, 6, 10, 0.0F);
		this.rotateCuboid(tail_base, -0.09075712110370514F, 0.0F, 0.0F);
		this.tooth6_1 = new Cuboid(this, 0, 0);
		this.tooth6_1.setRotationPoint(-1.8F, -1.0F, -5.0F);
		this.tooth6_1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, -0.2F);
		this.body_base = new Cuboid(this, 1, 34);
		this.body_base.setRotationPoint(-4.5F, 11.8F, 0.0F);
		this.body_base.addBox(0.0F, 0.0F, 0.0F, 9, 8, 10, 0.4F);
		this.jaw = new Cuboid(this, 73, 19);
		this.jaw.setRotationPoint(0.0F, 3.4F, -4.0F);
		this.jaw.addBox(-2.0F, 0.0F, -10.0F, 4, 2, 10, -0.2F);
		this.rotateCuboid(jaw, 0.17453292519943295F, 0.0F, 0.0F);
		this.tooth2 = new Cuboid(this, 0, 0);
		this.tooth2.setRotationPoint(-2.0F, -1.0F, -9.0F);
		this.tooth2.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, -0.2F);
		this.tail_mid = new Cuboid(this, 6, 88);
		this.tail_mid.setRotationPoint(-0.3F, 0.7F, 9.5F);
		this.tail_mid.addBox(-2.0F, 0.0F, 0.0F, 4, 5, 10, 0.0F);
		this.rotateCuboid(tail_mid, 0.05235987755982988F, 0.0F, 0.0F);
		this.leg_right = new Cuboid(this, 73, 37);
		this.leg_right.setRotationPoint(-3.0F, 6.0F, -1.0F);
		this.leg_right.addBox(-3.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
		this.rotateCuboid(leg_right, 0.6981317007977318F, 0.0F, 0.0F);
		this.tail_tip = new Cuboid(this, 9, 104);
		this.tail_tip.setRotationPoint(0.0F, 0.7F, 9.5F);
		this.tail_tip.addBox(-1.5F, 0.0F, 0.0F, 3, 4, 8, 0.0F);
		this.rotateCuboid(tail_tip, 0.05235987755982988F, 0.0F, 0.0F);
		this.tooth8 = new Cuboid(this, 0, 0);
		this.tooth8.setRotationPoint(-1.8F, -1.0F, -7.5F);
		this.tooth8.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, -0.2F);
		this.nose = new Cuboid(this, 102, 12);
		this.nose.setRotationPoint(0.0F, -1.4F, -6.0F);
		this.nose.addBox(-2.0F, -2.0F, -4.0F, 4, 3, 3, 0.1F);
		this.tooth1 = new Cuboid(this, 0, 0);
		this.tooth1.setRotationPoint(1.0F, -1.0F, -9.0F);
		this.tooth1.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, -0.2F);
		this.upper_jaw = new Cuboid(this, 74, 6);
		this.upper_jaw.setRotationPoint(0.0F, 3.2F, -4.4F);
		this.upper_jaw.addBox(-2.0F, -3.0F, -9.0F, 4, 3, 9, 0.0F);
		this.rotateCuboid(upper_jaw, 0.18203784098300857F, 0.0F, 0.0F);
		this.leg_left = new Cuboid(this, 54, 37);
		this.leg_left.setRotationPoint(3.0F, 6.0F, -1.0F);
		this.leg_left.addBox(-1.0F, 0.0F, 0.0F, 4, 8, 4, 0.0F);
		this.rotateCuboid(leg_left, 0.6981317007977318F, 0.0F, 0.0F);
		this.head = new Cuboid(this, 48, 6);
		this.head.setRotationPoint(0.0F, 0.4F, -5.5F);
		this.head.addBox(-3.0F, -0.3F, -5.4F, 6, 6, 6, 0.2F);
		this.rotateCuboid(head, -0.20943951023931953F, 0.0F, 0.0F);
		this.body_chest = new Cuboid(this, 3, 16);
		this.body_chest.setRotationPoint(4.5F, 2.0F, 0.3F);
		this.body_chest.addBox(-4.5F, -2.0F, -8.0F, 9, 8, 8, 0.1F);
		this.rotateCuboid(body_chest, 0.054105206811824215F, 0.0F, 0.0F);
		this.tooth7 = new Cuboid(this, 0, 0);
		this.tooth7.setRotationPoint(0.8F, 0.0F, -7.5F);
		this.tooth7.addBox(0.0F, -1.0F, 0.0F, 1, 2, 1, -0.2F);
		this.tooth5 = new Cuboid(this, 0, 0);
		this.tooth5.setRotationPoint(-1.5F, -1.0F, -9.7F);
		this.tooth5.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, -0.2F);
		this.tooth3 = new Cuboid(this, 0, 0);
		this.tooth3.setRotationPoint(-2.0F, -1.0F, -6.0F);
		this.tooth3.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, -0.2F);
		this.arm_left = new Cuboid(this, 50, 51);
		this.arm_left.setRotationPoint(3.0F, 3.0F, -8.0F);
		this.arm_left.addBox(0.0F, 0.0F, 0.0F, 3, 3, 7, 0.0F);
		this.rotateCuboid(arm_left, -1.3962634015954636F, 0.0F, 0.0F);
		this.tooth4 = new Cuboid(this, 0, 0);
		this.tooth4.setRotationPoint(1.0F, -1.0F, -6.0F);
		this.tooth4.addBox(0.0F, 0.0F, 0.0F, 1, 2, 1, -0.2F);
		this.arm_right = new Cuboid(this, 73, 51);
		this.arm_right.setRotationPoint(-3.0F, 3.0F, -8.0F);
		this.arm_right.addBox(-3.0F, 0.0F, 0.0F, 3, 3, 7, 0.0F);
		this.rotateCuboid(arm_right, -1.3962634015954636F, 0.0F, 0.0F);
		this.body_chest.addChild(this.collar);
		this.body_base.addChild(this.hip);
		this.jaw.addChild(this.tooth6);
		this.jaw.addChild(this.tooth9);
		this.hip.addChild(this.tail_base);
		this.jaw.addChild(this.tooth6_1);
		this.head.addChild(this.jaw);
		this.upper_jaw.addChild(this.tooth2);
		this.tail_base.addChild(this.tail_mid);
		this.hip.addChild(this.leg_right);
		this.tail_mid.addChild(this.tail_tip);
		this.jaw.addChild(this.tooth8);
		this.upper_jaw.addChild(this.nose);
		this.upper_jaw.addChild(this.tooth1);
		this.head.addChild(this.upper_jaw);
		this.hip.addChild(this.leg_left);
		this.collar.addChild(this.head);
		this.body_base.addChild(this.body_chest);
		this.jaw.addChild(this.tooth7);
		this.jaw.addChild(this.tooth5);
		this.upper_jaw.addChild(this.tooth3);
		this.body_chest.addChild(this.arm_left);
		this.upper_jaw.addChild(this.tooth4);
		this.body_chest.addChild(this.arm_right);
	}

	@Override
	public void render(CrocodileEntity entity_1, float float_1, float float_2, float float_3, float float_4, float float_5, float float_6) {
		this.body_base.render(float_6);
	}

	@Override
	protected void runAnimations(CrocodileEntity entity, AnimatedEntityEntry entry, float partialTicks) {
		this.stopAnimation(entry, entry.getTicksDone(partialTicks));
	}

	@Override
	protected AnimatedEntityEntry getEntry(CrocodileEntity entity) {
		if(entity.entry == null) {
			entity.entry = new AnimatedEntityEntry(entity, this.defaultMap);
		}
		return entity.entry;
	}
}
