package com.terraformersmc.terrestrium.entities.roadrunner;

import com.sun.istack.internal.Nullable;
import net.minecraft.block.BlockState;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.FoxEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.predicate.entity.EntityPredicates;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorld;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.Iterator;
import java.util.function.Predicate;

public class RoadrunnerEntity extends PassiveEntity {

	private static final TrackedData<Integer> TYPE;
	private static final TrackedData<Byte> ROADRUNNER_FLAGS;
	private static final Predicate<Entity> NOTICEABLE_PLAYER_FILTER;

	public RoadrunnerEntity(EntityType<? extends RoadrunnerEntity> entityType_1, World world_1) {
		super(entityType_1, world_1);
		this.moveControl = new RoadrunnerEntity.RoadrunnerMoveControl();
		this.setPathNodeTypeWeight(PathNodeType.DANGER_OTHER, 0.0F);
		this.setPathNodeTypeWeight(PathNodeType.DAMAGE_OTHER, 0.0F);
	}

	protected void initDataTracker() {
		super.initDataTracker();
		this.dataTracker.startTracking(TYPE, 0);
		this.dataTracker.startTracking(ROADRUNNER_FLAGS, (byte)0);
	}

	protected void initGoals() {
		this.goalSelector.add(1, new RoadrunnerEntity.StopWanderingGoal());
		this.goalSelector.add(3, new FleeEntityGoal<>(this, PlayerEntity.class, 16.0F, 1.6D, 1.4D, (livingEntity_1) -> {
			return NOTICEABLE_PLAYER_FILTER.test(livingEntity_1);
		}));
		this.goalSelector.add(3, new FleeEntityGoal<>(this, WolfEntity.class, 8.0F, 1.6D, 1.4D, (livingEntity_1) -> {
			return !((WolfEntity)livingEntity_1).isTamed();
		}));
		this.goalSelector.add(3, new FleeEntityGoal<>(this, FoxEntity.class, 8.0F, 1.6D, 1.4D, (livingEntity_1) -> {
			return !((WolfEntity)livingEntity_1).isTamed();
		}));
		this.goalSelector.add(10, new WanderAroundFarGoal(this, 1.0D));
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

	protected boolean cannotMove() {
		return this.getHealth() <= 0.0F;
	}

	@Nullable
	public EntityData initialize(IWorld iWorld_1, LocalDifficulty localDifficulty_1, SpawnType spawnType_1, @Nullable EntityData entityData_1, @Nullable CompoundTag compoundTag_1) {
		return super.initialize(iWorld_1, localDifficulty_1, spawnType_1, (EntityData)entityData_1, compoundTag_1);
	}

	class RoadrunnerMoveControl extends MoveControl {
		public RoadrunnerMoveControl() {
			super(RoadrunnerEntity.this);
		}

		public void tick() {
			super.tick();
		}
	}

	public boolean isWalking() {
		return this.getRoadrunnerFlag(64);
	}

	private void setWalking(boolean boolean_1) {
		this.setRoadrunnerFlag(64, boolean_1);
	}

	private void setRoadrunnerFlag(int int_1, boolean boolean_1) {
		if (boolean_1) {
			this.dataTracker.set(ROADRUNNER_FLAGS, (byte)((Byte)this.dataTracker.get(ROADRUNNER_FLAGS) | int_1));
		} else {
			this.dataTracker.set(ROADRUNNER_FLAGS, (byte)((Byte)this.dataTracker.get(ROADRUNNER_FLAGS) & ~int_1));
		}

	}

	private boolean getRoadrunnerFlag(int int_1) {
		return ((Byte)this.dataTracker.get(ROADRUNNER_FLAGS) & int_1) != 0;
	}

	public void tick() {
		super.tick();
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

	private void stopActions() {
		this.setWalking(false);
	}

	static {
		TYPE = DataTracker.registerData(RoadrunnerEntity.class, TrackedDataHandlerRegistry.INTEGER);
		ROADRUNNER_FLAGS = DataTracker.registerData(RoadrunnerEntity.class, TrackedDataHandlerRegistry.BYTE);
		NOTICEABLE_PLAYER_FILTER = (entity_1) -> {
			return !entity_1.isSneaking() && EntityPredicates.EXCEPT_CREATIVE_OR_SPECTATOR.test(entity_1);
		};
	}

	class StopWanderingGoal extends Goal {
		int timer;

		public StopWanderingGoal() {
			this.setControls(EnumSet.of(Control.LOOK, Control.JUMP, Control.MOVE));
		}

		public boolean canStart() {
			return RoadrunnerEntity.this.isWalking();
		}

		public boolean shouldContinue() {
			return this.canStart() && this.timer > 0;
		}

		public void start() {
			this.timer = 40;
		}

		public void stop() {
			RoadrunnerEntity.this.setWalking(false);
		}

		public void tick() {
			--this.timer;
		}
	}

	class DelayedCalmDownGoal extends RoadrunnerEntity.CalmDownGoal {
		private int timer;

		public DelayedCalmDownGoal() {
			super();
			this.timer = RoadrunnerEntity.this.random.nextInt(140);
			this.setControls(EnumSet.of(Control.MOVE, Control.LOOK, Control.JUMP));
		}

		public boolean canStart() {
			if (RoadrunnerEntity.this.sidewaysSpeed == 0.0F && RoadrunnerEntity.this.upwardSpeed == 0.0F && RoadrunnerEntity.this.forwardSpeed == 0.0F) {
				return this.canNotCalmDown();
			} else {
				return false;
			}
		}

		public boolean shouldContinue() {
			return this.canNotCalmDown();
		}

		private boolean canNotCalmDown() {
			if (this.timer > 0) {
				--this.timer;
				return false;
			} else {
				return this.isAtFavoredLocation() && !this.canCalmDown();
			}
		}

		public void stop() {
			this.timer = RoadrunnerEntity.this.random.nextInt(140);
			RoadrunnerEntity.this.stopActions();
		}

		public void start() {
			RoadrunnerEntity.this.setJumping(false);
			RoadrunnerEntity.this.getNavigation().stop();
			RoadrunnerEntity.this.getMoveControl().moveTo(RoadrunnerEntity.this.x, RoadrunnerEntity.this.y, RoadrunnerEntity.this.z, 0.0D);
		}
	}

	abstract class CalmDownGoal extends Goal {
		private final TargetPredicate WORRIABLE_ENTITY_PREDICATE;

		private CalmDownGoal() {
			this.WORRIABLE_ENTITY_PREDICATE = (new TargetPredicate()).setBaseMaxDistance(12.0D).includeHidden().setPredicate(RoadrunnerEntity.this.new WorriableEntityFilter());
		}

		protected boolean isAtFavoredLocation() {
			BlockPos blockPos_1 = new BlockPos(RoadrunnerEntity.this);
			return RoadrunnerEntity.this.getPathfindingFavor(blockPos_1) >= 0.0F;
		}

		protected boolean canCalmDown() {
			return !RoadrunnerEntity.this.world.getTargets(LivingEntity.class, this.WORRIABLE_ENTITY_PREDICATE, RoadrunnerEntity.this, RoadrunnerEntity.this.getBoundingBox().expand(12.0D, 6.0D, 12.0D)).isEmpty();
		}
	}

	public class WorriableEntityFilter implements Predicate<LivingEntity> {
		
		public WorriableEntityFilter() {}
		
		@Override
		public boolean test(LivingEntity livingEntity) {
			if (livingEntity instanceof RoadrunnerEntity) {
				return false;
			} else {
				if (livingEntity instanceof TameableEntity) {
					return false;
				} else if (livingEntity instanceof PlayerEntity && (livingEntity.isSpectator() || ((PlayerEntity)livingEntity).isCreative())) {
					return false;
				}
				return true;
			}
		}
	}

	@Override
	public PassiveEntity createChild(PassiveEntity passiveEntity) {
		return null;
	}

	@Override
	public void readCustomDataFromTag(CompoundTag compoundTag) {
		super.readCustomDataFromTag(compoundTag);
	}

	@Override
	public void writeCustomDataToTag(CompoundTag compoundTag) {
		super.writeCustomDataToTag(compoundTag);
	}
}
