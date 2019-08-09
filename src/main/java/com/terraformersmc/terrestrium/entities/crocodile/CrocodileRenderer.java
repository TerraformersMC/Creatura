package com.terraformersmc.terrestrium.entities.crocodile;

import com.terraformersmc.terrestrium.Terrestrium;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class CrocodileRenderer extends MobEntityRenderer {

	public CrocodileRenderer(EntityRenderDispatcher entityRenderDispatcher_1) {
		super(entityRenderDispatcher_1, new CrocodileModel(), 0.2f);
	}

	@Override
	protected Identifier getTexture(Entity entity) {
		return new Identifier(Terrestrium.MOD_ID, "textures/entity/crocodile/crocodile.png");
	}
}
