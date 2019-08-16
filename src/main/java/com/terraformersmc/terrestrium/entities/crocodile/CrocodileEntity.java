package com.terraformersmc.terrestrium.entities.crocodile;

import com.sun.istack.internal.Nullable;
import com.terraformersmc.terrestrium.ai.StopWanderingGoal;
import com.terraformersmc.terrestrium.entities.AnimatedEntityEntry;
import com.terraformersmc.terrestrium.entities.TerrestriumWaterMob;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnType;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

import java.util.Iterator;

public class CrocodileEntity extends TerrestriumWaterMob {

	protected AnimatedEntityEntry entry;

	public CrocodileEntity(EntityType<? extends CrocodileEntity> entityType_1, World world_1) {
		super(entityType_1, world_1);
		this.moveControl = new CrocodileMoveControl();
	}

	protected void initGoals() {
		this.goalSelector.add(0, new SwimGoal(this));
		this.goalSelector.add(1, new WanderAroundFarGoal(this, 0.8D));
		this.goalSelector.add(1, new StopWanderingGoal(this, 60));
		this.targetSelector.add(2, new FollowTargetGoal<PlayerEntity>(this, PlayerEntity.class, true));
		this.goalSelector.add(3, new MeleeAttackGoal(this, 1.0, true));
	}

	@Override
	protected void initAttributes() {
		super.initAttributes();
		this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(20);
		this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.25D);
		this.getAttributeInstance(EntityAttributes.KNOCKBACK_RESISTANCE).setBaseValue(10.0D);
	}

	public void tickMovement() {
		if (this.cannotMove()) {
			this.jumping = false;
			this.sidewaysSpeed = 0.0F;
			this.forwardSpeed = 0.0F;
			this.field_6267 = 0.0F;
		}
		super.tickMovement();
	}

	@Override
	public float getPathfindingFavor(BlockPos blockPos_1) {
		return world.getBlockState(blockPos_1.down()).getBlock() == Blocks.WATER ? 10.0F : world.getBrightness(blockPos_1) - 0.5F;
	}

	@Nullable
	public EntityData initialize(IWorld iWorld_1, LocalDifficulty localDifficulty_1, SpawnType spawnType_1, @Nullable EntityData entityData_1, @Nullable CompoundTag compoundTag_1) {
		return super.initialize(iWorld_1, localDifficulty_1, spawnType_1, (EntityData)entityData_1, compoundTag_1);
	}

	class CrocodileMoveControl extends MoveControl {
		public CrocodileMoveControl() {
			super(CrocodileEntity.this);
		}

		public void tick() {
			super.tick();
		}
	}

	public void handleFallDamage(float float_1, float float_2) {
		int int_1 = MathHelper.ceil((float_1 - 5.0F) * float_2);
		if (int_1 > 0) {
			this.damage(DamageSource.FALL, (float)int_1);
			if (this.hasPassengers()) {
				Iterator<Entity> var4 = this.getPassengersDeep().iterator();

				while(var4.hasNext()) {
					Entity entity_1 = var4.next();
					entity_1.damage(DamageSource.FALL, (float)int_1);
				}
			}

			BlockState blockState_1 = this.world.getBlockState(new BlockPos(this.x, this.y - 0.2D - (double)this.prevYaw, this.z));
			if (!blockState_1.isAir() && !this.isSilent()) {
				BlockSoundGroup blockSoundGroup_1 = blockState_1.getSoundGroup();
				this.world.playSound((PlayerEntity)null, this.x, this.y, this.z, blockSoundGroup_1.getStepSound(), this.getSoundCategory(), blockSoundGroup_1.getVolume() * 0.5F, blockSoundGroup_1.getPitch() * 0.75F);
			}
		}
	}
}
