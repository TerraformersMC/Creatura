package com.terraformersmc.terrestrium.init;

import com.terraformersmc.terrestrium.Terrestrium;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TerrestriumItems {

	static Item CROCODILE_SPAWN_EGG;
	static Item PENGUIN_SPAWN_EGG;
	static Item ROADRUNNER_SPAWN_EGG;

	public static void register() {
		ROADRUNNER_SPAWN_EGG = registerSpawnEgg("roadrunner", TerrestriumEntities.ROADRUNNER, 6905430, 13158598);
		PENGUIN_SPAWN_EGG = registerSpawnEgg("penguin", TerrestriumEntities.PENGUIN, 16250871, 3684408);
		CROCODILE_SPAWN_EGG = registerSpawnEgg("crocodile", TerrestriumEntities.CROCODILE, 4616521, 10929577);
	}

	private static Item registerSpawnEgg(String name, EntityType entityType, int primaryColor, int secondaryColor) {
		return register(name + "_spawn_egg", (Item)(new SpawnEggItem(entityType, primaryColor, secondaryColor, (new Item.Settings()).group(ItemGroup.MISC))));
	}

	private static Item register(String name, Item item) {
		return Registry.register(Registry.ITEM, new Identifier(Terrestrium.MOD_ID, name), item);
	}
}
