package com.terraformersmc.terrestrium.entities;

import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.pathing.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class TerrestriumAmphibiousEntity extends TerrestriumWaterMob {

	public MobNavigation landNavigation;
	public SwimNavigation waterNavigation;

	public TerrestriumAmphibiousEntity(EntityType<? extends TerrestriumWaterMob> entityType_1, World world_1) {
		super(entityType_1, world_1);
		this.waterNavigation = new AmphibiousSwimNavigation(this, world_1);
		this.landNavigation = new MobNavigation(this, world_1);
		this.navigation = createNavigation(world_1);
		this.moveControl = new AmphibiousMoveControl(this);
	}

	public void setNavigation(MobNavigation navigation) {
		this.navigation = navigation;
	}

	@Override
	public void travel(Vec3d vec3d_1) {
		if (this.canMoveVoluntarily() && this.isInsideWater()) {
			this.updateVelocity(0.1F, vec3d_1);
			this.move(MovementType.SELF, this.getVelocity());
			this.setVelocity(this.getVelocity().multiply(0.9D));
			if (this.getTarget() == null) {
				this.setVelocity(this.getVelocity().add(0.0D, -0.005D, 0.0D));
			}
		} else {
			super.travel(vec3d_1);
		}
	}

	@Override
	protected EntityNavigation createNavigation(World world_1) {
		return this.isInsideWater() ? this.waterNavigation : this.landNavigation;
	}

	public static class AmphibiousSwimNavigation extends SwimNavigation {
		AmphibiousSwimNavigation(TerrestriumAmphibiousEntity entity, World world_1) {
			super(entity, world_1);
		}

		protected PathNodeNavigator createPathNodeNavigator(int int_1) {
			return new PathNodeNavigator(new AmphibiousPathNodeMaker(), int_1);
		}

		public boolean isValidPosition(BlockPos blockPos_1) {
			if (this.entity instanceof TerrestriumAmphibiousEntity) {
				return this.world.getBlockState(blockPos_1).getBlock() == Blocks.WATER;
			}
			return !this.world.getBlockState(blockPos_1.down()).isAir();
		}
	}

	@Override
	public EntityNavigation getNavigation() {
		return this.navigation;
	}

	public static class AmphibiousMoveControl extends MoveControl {
		private final TerrestriumAmphibiousEntity amphibiousEntity;

		AmphibiousMoveControl(TerrestriumAmphibiousEntity entity) {
			super(entity);
			this.amphibiousEntity = entity;
		}

		private void updateVelocity() {
			if (this.amphibiousEntity.isInsideWater()) {
				this.amphibiousEntity.setVelocity(this.amphibiousEntity.getVelocity().add(0.0D, 0.005D, 0.0D));
			} else if (this.amphibiousEntity.onGround) {
				this.amphibiousEntity.setMovementSpeed(Math.max(this.amphibiousEntity.getMovementSpeed() / 1.5F, 0.4F));
			}
		}

		@Override
		public void tick() {
			this.updateVelocity();
			if (this.amphibiousEntity.isInsideWater()) {
				this.amphibiousEntity.navigation = this.amphibiousEntity.waterNavigation;
			} else {
				this.amphibiousEntity.navigation = this.amphibiousEntity.landNavigation;
			}
			if (this.state == State.MOVE_TO && !this.amphibiousEntity.getNavigation().isIdle()) {
				double x = this.targetX - this.amphibiousEntity.x;
				double y = this.targetY - this.amphibiousEntity.y;
				double z = this.targetZ - this.amphibiousEntity.z;
				double distance = MathHelper.sqrt(x * x + y * y + z * z);
				y /= distance;
				float float_1 = (float)(MathHelper.atan2(z, x) * 57.2957763671875D) - 90.0F;
				this.amphibiousEntity.yaw = this.changeAngle(this.amphibiousEntity.yaw, float_1, 90.0F);
				this.amphibiousEntity.field_6283 = this.amphibiousEntity.yaw;
				float float_2 = (float)(this.speed * this.amphibiousEntity.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).getValue());
				this.amphibiousEntity.setMovementSpeed(MathHelper.lerp(0.125F, this.amphibiousEntity.getMovementSpeed(), float_2));
				this.amphibiousEntity.setVelocity(this.amphibiousEntity.getVelocity().add(0.0D, (double)this.amphibiousEntity.getMovementSpeed() * y * 0.1D, 0.0D));
			} else {
				this.amphibiousEntity.setMovementSpeed(0.0F);
			}
		}
	}
}
