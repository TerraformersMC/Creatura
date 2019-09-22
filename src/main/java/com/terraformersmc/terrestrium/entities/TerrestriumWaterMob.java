package com.terraformersmc.terrestrium.entities;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.world.World;

public class TerrestriumWaterMob extends MobEntityWithAi {

	private boolean isTargetingUnderwater;

	protected TerrestriumWaterMob(EntityType<? extends TerrestriumWaterMob> entityType_1, World world_1) {
		super(entityType_1, world_1);
		isTargetingUnderwater = false;
	}

	public boolean isTargetingUnderwater() {
		return isTargetingUnderwater;
	}

	public void setTargetingUnderwater(boolean targetingUnderwater) {
		isTargetingUnderwater = targetingUnderwater;
	}
}
