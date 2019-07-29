package com.terraformersmc.terrestrium.entities;

import com.terraformersmc.terrestrium.Terrestrium;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class WormRenderer extends MobEntityRenderer {

	public WormRenderer(EntityRenderDispatcher entityRenderDispatcher_1) {
		super(entityRenderDispatcher_1, new WormModel<>(), 0.1f);
	}

	@Override
	protected Identifier getTexture(Entity entity) {
		return new Identifier(Terrestrium.MOD_ID, "textures/entity/worm/worm.png");
	}
}
