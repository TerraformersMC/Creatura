package com.terraformersmc.terrestrium.init;

import com.terraformersmc.terrestrium.Terrestrium;
import com.terraformersmc.terrestrium.entities.crocodile.CrocodileEntity;
import com.terraformersmc.terrestrium.entities.roadrunner.RoadrunnerEntity;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TerrestriumEntities {

	public static EntityType CROCODILE;
	public static EntityType ROADRUNNER;

	public static void register() {
		CROCODILE = register("crocodile", EntityCategory.CREATURE, CrocodileEntity::new, 0.75F, 0.75F);
		ROADRUNNER = register("roadrunner", EntityCategory.CREATURE, RoadrunnerEntity::new, 0.75F, 0.75F);
	}

	private static <T extends Entity> EntityType<T> register(String name, EntityCategory category, EntityType.EntityFactory<T> function, float width, float height) {
		return Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(Terrestrium.MOD_ID, name),
			FabricEntityTypeBuilder.create(category, function).size(EntityDimensions.fixed(width, height)).build()
		);
	}
}
