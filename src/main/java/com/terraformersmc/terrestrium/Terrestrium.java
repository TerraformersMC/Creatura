package com.terraformersmc.terrestrium;

import com.terraformersmc.terrestrium.entities.WormEntity;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Terrestrium implements ModInitializer {
	public static final String MOD_ID = "terrestrium";
	public static ItemGroup ITEM_GROUP;

	@Override
	public void onInitialize() {
		ITEM_GROUP = FabricItemGroupBuilder.build(new Identifier(MOD_ID, "items"), () -> new ItemStack(Items.EGG));

		Registry.register(
			Registry.ENTITY_TYPE,
			new Identifier(Terrestrium.MOD_ID, "worm"),
			FabricEntityTypeBuilder.create(EntityCategory.AMBIENT, WormEntity::new).size(EntityDimensions.fixed(1, 1)).build()
		);
	}
}
