package com.terraformersmc.terrestrium.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.WaterCreatureEntity;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.world.World;

public class TerrestriumAmphibiousEntity extends TerrestriumWaterMob {

	public EntityNavigation navigation;
	public MobNavigation landNavigation;
	public SwimNavigation waterNavigation;

	protected TerrestriumAmphibiousEntity(EntityType<? extends WaterCreatureEntity> entityType_1, World world_1) {
		super(entityType_1, world_1);
		this.navigation = landNavigation;
	}


}
