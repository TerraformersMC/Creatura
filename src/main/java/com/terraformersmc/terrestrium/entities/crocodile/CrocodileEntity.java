package com.terraformersmc.terrestrium.entities.crocodile;

import com.terraformersmc.terrestrium.ai.goals.WanderInWaterGoal;
import com.terraformersmc.terrestrium.entities.AnimatedEntityEntry;
import com.terraformersmc.terrestrium.entities.TerrestriumAmphibiousEntity;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.FishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.ViewableWorld;
import net.minecraft.world.World;

import java.util.Random;

public class CrocodileEntity extends TerrestriumAmphibiousEntity {

	protected AnimatedEntityEntry entry;
	private static final TrackedData<Boolean> ANGRY;

	public CrocodileEntity(EntityType<? extends CrocodileEntity> entityType_1, World world_1) {
		super(entityType_1, world_1);
		this.stepHeight = 1.0F;
	}

	public EntityData initialize(IWorld iWorld_1, LocalDifficulty localDifficulty_1, SpawnType spawnType_1, EntityData entityData_1, CompoundTag compoundTag_1) {
		return super.initialize(iWorld_1, localDifficulty_1, spawnType_1, entityData_1, compoundTag_1);
	}

	protected void initGoals() {
		this.targetSelector.add(1, new MeleeAttackGoal(this, 20, true));
		this.targetSelector.add(2, new FollowTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
		this.targetSelector.add(3, new FollowTargetGoal<FishEntity>(this, FishEntity.class, true));
		this.goalSelector.add(1, new WanderInWaterGoal(this, 1.0D, 100, this));
		this.goalSelector.add(3, new LookAtEntityGoal(this, PlayerEntity.class, 1.0F));
		this.goalSelector.add(4, new WanderAroundFarGoal(this, 0.7D));
		this.goalSelector.add(5, new LookAroundGoal(this));
	}

	protected void initAttributes() {
		super.initAttributes();
		this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(30.0D);
		this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.15D);
		this.getAttributeContainer().register(EntityAttributes.ATTACK_DAMAGE);
		this.getAttributeInstance(EntityAttributes.ATTACK_DAMAGE).setBaseValue(3.5D);
	}

	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(ANGRY, false);
	}

	@Override
	public void setTarget(LivingEntity livingEntity_1) {
		super.setTarget(livingEntity_1);
		if (livingEntity_1 == null) {
			this.dataTracker.set(ANGRY, false);
		} else {
			this.dataTracker.set(ANGRY, true);
		}
	}

	public boolean isAngry() {
		return (Boolean)this.dataTracker.get(ANGRY);
	}

	static {
		ANGRY = DataTracker.registerData(CrocodileEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
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

	protected SoundEvent getAmbientSound() {
		return !this.isInsideWater() && this.onGround ? SoundEvents.ENTITY_TURTLE_AMBIENT_LAND : super.getAmbientSound();
	}

	protected void playSwimSound(float float_1) {
		super.playSwimSound(float_1 * 1.5F);
	}

	protected SoundEvent getSwimSound() {
		return SoundEvents.ENTITY_TURTLE_SWIM;
	}

	protected SoundEvent getHurtSound(DamageSource damageSource_1) {
		return this.isBaby() ? SoundEvents.ENTITY_TURTLE_HURT_BABY : SoundEvents.ENTITY_TURTLE_HURT;
	}

	protected SoundEvent getDeathSound() {
		return SoundEvents.ENTITY_TURTLE_DEATH;
	}

	protected void playStepSound(BlockPos blockPos_1, BlockState blockState_1) {
		SoundEvent soundEvent_1 = SoundEvents.ENTITY_TURTLE_SHAMBLE;
		this.playSound(soundEvent_1, 0.15F, 1.0F);
	}

	protected float calculateStepDelta() {
		return this.distanceWalked + 0.15F;
	}

	public float getScaleFactor() {
		return this.isBaby() ? 0.3F : 1.0F;
	}

	public float getPathfindingFavor(BlockPos blockPos_1, ViewableWorld viewableWorld_1) {
		if (viewableWorld_1.getFluidState(blockPos_1).matches(FluidTags.WATER)) {
			return 10.0F;
		} else {
			return viewableWorld_1.getBlockState(blockPos_1.down()).getBlock() == Blocks.GRASS_BLOCK ? 10.0F : viewableWorld_1.getBrightness(blockPos_1) - 0.5F;
		}
	}

	@Override
	public void tickMovement() {
		super.tickMovement();
		Random random = new Random();
		if (this.isAngry() && this.isInsideWater()) {
			this.world.addParticle(ParticleTypes.BUBBLE,
				this.getPos().getX() + (this.getRotationVector().getX() * 2.5) + (random.nextDouble() - 0.5D),
				this.getPos().getY() + (0.5 * (random.nextDouble() - 0.5D)),
				this.getPos().getZ() + (this.getRotationVector().getZ() * 2.5 + (random.nextDouble() - 0.5D)),
				this.getVelocity().getX() - 0.15,
				this.getVelocity().getY() + 1.0D,
				this.getVelocity().getZ() - 0.15);
		}
	}
}
