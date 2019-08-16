package com.terraformersmc.terrestrium.ai.movecontrols;

import com.terraformersmc.terrestrium.entities.TerrestriumAmphibiousEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.math.MathHelper;

public class AmphibiousMoveControl extends MoveControl {
	private final TerrestriumAmphibiousEntity drowned;

	public AmphibiousMoveControl(TerrestriumAmphibiousEntity mobEntity_1) {
		super(mobEntity_1);
		this.drowned = mobEntity_1;
	}

	public void tick() {
		LivingEntity livingEntity_1 = this.drowned.getTarget();
		if (this.drowned.isTargetingUnderwater() && this.drowned.isInsideWater()) {
			if (livingEntity_1 != null && livingEntity_1.y > this.drowned.y || this.drowned.isTargetingUnderwater()) {
				this.drowned.setVelocity(this.drowned.getVelocity().add(0.0D, 0.002D, 0.0D));
			}

			if (this.state != State.MOVE_TO || this.drowned.getNavigation().isIdle()) {
				this.drowned.setMovementSpeed(0.0F);
				return;
			}

			double double_1 = this.targetX - this.drowned.x;
			double double_2 = this.targetY - this.drowned.y;
			double double_3 = this.targetZ - this.drowned.z;
			double double_4 = (double) MathHelper.sqrt(double_1 * double_1 + double_2 * double_2 + double_3 * double_3);
			double_2 /= double_4;
			float float_1 = (float)(MathHelper.atan2(double_3, double_1) * 57.2957763671875D) - 90.0F;
			this.drowned.yaw = this.changeAngle(this.drowned.yaw, float_1, 90.0F);
			this.drowned.field_6283 = this.drowned.yaw;
			float float_2 = (float)(this.speed * this.drowned.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).getValue());
			float float_3 = MathHelper.lerp(0.125F, this.drowned.getMovementSpeed(), float_2);
			this.drowned.setMovementSpeed(float_3);
			this.drowned.setVelocity(this.drowned.getVelocity().add((double)float_3 * double_1 * 0.005D, (double)float_3 * double_2 * 0.1D, (double)float_3 * double_3 * 0.005D));
		} else {
			if (!this.drowned.onGround) {
				this.drowned.setVelocity(this.drowned.getVelocity().add(0.0D, -0.008D, 0.0D));
			}

			super.tick();
		}

	}
}
