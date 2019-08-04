package com.terraformersmc.terrestrium.init;

import com.terraformersmc.terrestrium.Terrestrium;
import com.terraformersmc.terrestrium.entities.roadrunner.RoadrunnerEntity;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TerrestriumEntities {

	public static EntityType ROADRUNNER;

	public static void register() {
		ROADRUNNER = register("roadrunner", EntityCategory.CREATURE, RoadrunnerEntity::new, 1, 1);
	}

	private static <T extends Entity> EntityType<T> register(String name, EntityCategory category, EntityType.EntityFactory<T> function, int width, int height) {
		return Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(Terrestrium.MOD_ID, name),
			FabricEntityTypeBuilder.create(category, function).size(EntityDimensions.fixed(width, height)).build()
		);
	}
}
