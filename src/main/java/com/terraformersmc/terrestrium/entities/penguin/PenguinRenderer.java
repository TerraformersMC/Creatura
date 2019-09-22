package com.terraformersmc.terrestrium.entities.penguin;

import com.terraformersmc.terrestrium.Terrestrium;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class PenguinRenderer extends MobEntityRenderer {

	public PenguinRenderer(EntityRenderDispatcher entityRenderDispatcher_1) {
		super(entityRenderDispatcher_1, new PenguinModel(), 0.5f);
	}

	@Override
	protected Identifier getTexture(Entity entity) {
		return new Identifier(Terrestrium.MOD_ID, "textures/entity/penguin/penguin.png");
	}
}
