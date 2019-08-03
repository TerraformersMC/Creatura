package com.terraformersmc.terrestrium.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;

public abstract class TerrestriumPassiveEntity extends PassiveEntity {

	protected TerrestriumPassiveEntity(EntityType<? extends PassiveEntity> entityType_1, World world_1) {
		super(entityType_1, world_1);
	}

	public void tick() {
		super.tick();
	}

	protected boolean cannotMove() {
		return this.getHealth() <= 0.0F;
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

	public abstract boolean isWalking();
}
