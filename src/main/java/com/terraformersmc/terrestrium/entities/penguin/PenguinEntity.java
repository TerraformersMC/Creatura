package com.terraformersmc.terrestrium.entities.penguin;

import com.terraformersmc.terrestrium.entities.AnimatedEntityEntry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.world.World;

public class PenguinEntity extends AnimalEntity {

	protected AnimatedEntityEntry entry;

	public PenguinEntity(EntityType<? extends AnimalEntity> entityType_1, World world_1) {
		super(entityType_1, world_1);
	}

	@Override
	public PassiveEntity createChild(PassiveEntity passiveEntity) {
		return null;
	}
}
