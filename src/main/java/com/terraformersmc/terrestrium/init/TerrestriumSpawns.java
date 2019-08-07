package com.terraformersmc.terrestrium.init;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;

public class TerrestriumSpawns {
	public static void sort() {
		//weight min max
		Biomes.DESERT.getEntitySpawnList(TerrestriumEntities.ROADRUNNER.getCategory()).add(new Biome.SpawnEntry(TerrestriumEntities.ROADRUNNER, 3, 1, 3));
	}
}
