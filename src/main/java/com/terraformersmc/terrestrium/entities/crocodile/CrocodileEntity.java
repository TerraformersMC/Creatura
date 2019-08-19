package com.terraformersmc.terrestrium.entities.crocodile;

import com.sun.istack.internal.Nullable;
import com.terraformersmc.terrestrium.ai.goals.CrocodileAttackGoal;
import com.terraformersmc.terrestrium.entities.AnimatedEntityEntry;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.AmphibiousPathNodeMaker;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeNavigator;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

public class CrocodileEntity extends AnimalEntity {

	protected AnimatedEntityEntry entry;
	private boolean targeting;

	public CrocodileEntity(EntityType<? extends CrocodileEntity> entityType_1, World world_1) {
		super(entityType_1, world_1);
		this.moveControl = new CrocodileEntity.CrocodileMoveControl(this);
		this.stepHeight = 1.0F;
	}


	@Nullable
	public EntityData initialize(IWorld iWorld_1, LocalDifficulty localDifficulty_1, SpawnType spawnType_1, @Nullable EntityData entityData_1, @Nullable CompoundTag compoundTag_1) {
		return super.initialize(iWorld_1, localDifficulty_1, spawnType_1, entityData_1, compoundTag_1);
	}

	protected void initGoals() {
		this.targetSelector.add(0, new CrocodileAttackGoal(this, 20, true));
		this.targetSelector.add(1, new FollowTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
		this.targetSelector.add(2, new FollowTargetGoal<FishEntity>(this, FishEntity.class, true));
		this.goalSelector.add(3, new CrocodileEntity.WanderInWaterGoal(this, 1.0D, 100, this));
		this.goalSelector.add(1, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
		this.goalSelector.add(2, new CrocodileEntity.WanderOnLandGoal(this, 1.0D, 100));
	}

	protected void initAttributes() {
		super.initAttributes();
		this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(30.0D);
		this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		this.getAttributeContainer().register(EntityAttributes.ATTACK_DAMAGE);
	}

	public boolean canFly() {
		return false;
	}

	public boolean canBreatheInWater() {
		return true;
	}

	public EntityGroup getGroup() {
		return EntityGroup.AQUATIC;
	}

	public int getMinAmbientSoundDelay() {
		return 200;
	}

	@Nullable
	protected SoundEvent getAmbientSound() {
		return !this.isInsideWater() && this.onGround && !this.isBaby() ? SoundEvents.ENTITY_TURTLE_AMBIENT_LAND : super.getAmbientSound();
	}

	protected void playSwimSound(float float_1) {
		super.playSwimSound(float_1 * 1.5F);
	}

	protected SoundEvent getSwimSound() {
		return SoundEvents.ENTITY_TURTLE_SWIM;
	}

	@Nullable
	protected SoundEvent getHurtSound(DamageSource damageSource_1) {
		return this.isBaby() ? SoundEvents.ENTITY_TURTLE_HURT_BABY : SoundEvents.ENTITY_TURTLE_HURT;
	}

	@Nullable
	protected SoundEvent getDeathSound() {
		return this.isBaby() ? SoundEvents.ENTITY_TURTLE_DEATH_BABY : SoundEvents.ENTITY_TURTLE_DEATH;
	}

	protected void playStepSound(BlockPos blockPos_1, BlockState blockState_1) {
		SoundEvent soundEvent_1 = this.isBaby() ? SoundEvents.ENTITY_TURTLE_SHAMBLE_BABY : SoundEvents.ENTITY_TURTLE_SHAMBLE;
		this.playSound(soundEvent_1, 0.15F, 1.0F);
	}

	public boolean canEat() {
		return super.canEat();
	}

	protected float calculateStepDelta() {
		return this.distanceWalked + 0.15F;
	}

	public float getScaleFactor() {
		return this.isBaby() ? 0.3F : 1.0F;
	}

	protected EntityNavigation createNavigation(World world_1) {
		return new CrocodileEntity.CrocodileSwimNavigation(this, world_1);
	}

	public float getPathfindingFavor(BlockPos blockPos_1, ViewableWorld viewableWorld_1) {
		if (viewableWorld_1.getFluidState(blockPos_1).matches(FluidTags.WATER)) {
			return 10.0F;
		} else {
			return viewableWorld_1.getBlockState(blockPos_1.down()).getBlock() == Blocks.SAND ? 10.0F : viewableWorld_1.getBrightness(blockPos_1) - 0.5F;
		}
	}

	@Override
	public PassiveEntity createChild(PassiveEntity passiveEntity) {
		return null;
	}

	public void tickMovement() {
		super.tickMovement();
	}

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

	public boolean canBeLeashedBy(PlayerEntity playerEntity_1) {
		return false;
	}

	public void onStruckByLightning(LightningEntity lightningEntity_1) {
		this.damage(DamageSource.LIGHTNING_BOLT, 3.4028235E38F);
	}

	public boolean isTargeting() {
		return targeting;
	}

	public void setTargeting(boolean targeting) {
		this.targeting = targeting;
	}

	static class CrocodileSwimNavigation extends SwimNavigation {
		CrocodileSwimNavigation(CrocodileEntity crocodileEntity_1, World world_1) {
			super(crocodileEntity_1, world_1);
		}

		protected PathNodeNavigator createPathNodeNavigator(int int_1) {
			return new PathNodeNavigator(new AmphibiousPathNodeMaker(), int_1);
		}

		public boolean isValidPosition(BlockPos blockPos_1) {
			if (this.entity instanceof CrocodileEntity) {
				CrocodileEntity crocodileEntity_1 = (CrocodileEntity)this.entity;
				return this.world.getBlockState(blockPos_1).getBlock() == Blocks.WATER;
			}

			return !this.world.getBlockState(blockPos_1.down()).isAir();
		}
	}

	static class CrocodileMoveControl extends MoveControl {
		private final CrocodileEntity crocodile;

		CrocodileMoveControl(CrocodileEntity crocodileEntity_1) {
			super(crocodileEntity_1);
			this.crocodile = crocodileEntity_1;
		}

		private void updateVelocity() {
			if (this.crocodile.isInsideWater()) {
				this.crocodile.setVelocity(this.crocodile.getVelocity().add(0.0D, 0.005D, 0.0D));
			} else if (this.crocodile.onGround) {
				this.crocodile.setMovementSpeed(Math.max(this.crocodile.getMovementSpeed() / 2.0F, 0.24F));
			}

		}

		public void tick() {
			this.updateVelocity();
			if (this.state == State.MOVE_TO && !this.crocodile.getNavigation().isIdle()) {
				double x = this.targetX - this.crocodile.x;
				double y = this.targetY - this.crocodile.y;
				double z = this.targetZ - this.crocodile.z;
				double distance = (double)MathHelper.sqrt(x * x + y * y + z * z);
				y /= distance;
				float float_1 = (float)(MathHelper.atan2(z, x) * 57.2957763671875D) - 90.0F;
				this.crocodile.yaw = this.changeAngle(this.crocodile.yaw, float_1, 90.0F);
				this.crocodile.field_6283 = this.crocodile.yaw;
				float float_2 = (float)(this.speed * this.crocodile.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).getValue());
				this.crocodile.setMovementSpeed(MathHelper.lerp(0.125F, this.crocodile.getMovementSpeed(), float_2));
				this.crocodile.setVelocity(this.crocodile.getVelocity().add(0.0D, (double)this.crocodile.getMovementSpeed() * y * 0.1D, 0.0D));
			} else {
				this.crocodile.setMovementSpeed(0.0F);
			}
		}
	}

	static class WanderInWaterGoal extends SwimAroundGoal {
		private final CrocodileEntity crocodile;

		public WanderInWaterGoal(MobEntityWithAi mobEntityWithAi_1, double double_1, int int_1, CrocodileEntity crocodile) {
			super(mobEntityWithAi_1, double_1, int_1);
			this.crocodile = crocodile;
		}
	}

	static class WanderOnLandGoal extends WanderAroundGoal {
		private final CrocodileEntity crocodile;

		private WanderOnLandGoal(CrocodileEntity crocodileEntity_1, double double_1, int int_1) {
			super(crocodileEntity_1, double_1, int_1);
			this.crocodile = crocodileEntity_1;
		}

		public boolean canStart() {
			return (!this.mob.isInsideWater() && super.canStart());
		}
	}
}
