package com.terraformersmc.terrestrium.init;

import net.minecraft.entity.EntityCategory;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

public class TerrestriumSpawns {
	public static void sort() {
		//weight min max
		Biomes.DESERT.getEntitySpawnList(EntityCategory.AMBIENT).add(new Biome.SpawnEntry(TerrestriumEntities.ROADRUNNER, 5, 1, 3));
	}
}
