package com.terraformersmc.terrestrium.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.World;

public class WormEntity extends PassiveEntity {

	public WormEntity(EntityType<?> entityType_1, World world_1) {
		super((EntityType<? extends PassiveEntity>) entityType_1, world_1);
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
