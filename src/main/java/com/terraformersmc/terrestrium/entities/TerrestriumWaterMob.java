package com.terraformersmc.terrestrium.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.WaterCreatureEntity;
import net.minecraft.world.World;

public class TerrestriumWaterMob extends WaterCreatureEntity {
	protected TerrestriumWaterMob(EntityType<? extends WaterCreatureEntity> entityType_1, World world_1) {
		super(entityType_1, world_1);
	}
}
