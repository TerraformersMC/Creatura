package com.terraformersmc.terrestrium.entities.roadrunner;

import com.terraformersmc.terrestrium.ai.StopWanderingGoal;
import com.terraformersmc.terrestrium.entities.AnimatedEntityEntry;
import com.terraformersmc.terrestrium.entities.TerrestriumPassiveEntity;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

import java.util.Iterator;
import java.util.function.Predicate;

public class RoadrunnerEntity extends TerrestriumPassiveEntity {

	protected AnimatedEntityEntry entry;
	private static final Predicate<Entity> NOTICEABLE_PLAYER_FILTER;

	public RoadrunnerEntity(EntityType<? extends RoadrunnerEntity> entityType_1, World world_1) {
		super(entityType_1, world_1);
		this.setPathNodeTypeWeight(PathNodeType.DANGER_OTHER, 0.0F);
	}

	protected void initGoals() {
		this.goalSelector.add(1, new WanderAroundFarGoal(this, 0.8D));
		this.goalSelector.add(1, new StopWanderingGoal(this, 60));
		this.goalSelector.add(2, new FleeEntityGoal<PlayerEntity>(this, PlayerEntity.class, 16.0F, 0.8D, 1.4D, NOTICEABLE_PLAYER_FILTER::test));
		this.goalSelector.add(2, new FleeEntityGoal<WolfEntity>(this, WolfEntity.class, 8.0F, 0.8D, 1.4D, (livingEntity_1) -> !((WolfEntity)livingEntity_1).isTamed()));
		this.goalSelector.add(2, new FleeEntityGoal<FoxEntity>(this, FoxEntity.class, 8.0F, 0.8D, 1.4D, (livingEntity_1) -> !livingEntity_1.isSleeping()));
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

	static {
		NOTICEABLE_PLAYER_FILTER = (entity_1) -> !entity_1.isSneaking() && EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(entity_1);
	}
}
