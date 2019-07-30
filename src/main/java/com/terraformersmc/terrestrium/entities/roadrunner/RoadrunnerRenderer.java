package com.terraformersmc.terrestrium.entities.roadrunner;

import com.terraformersmc.terrestrium.Terrestrium;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;

public class RoadrunnerRenderer extends MobEntityRenderer {

	public RoadrunnerRenderer(EntityRenderDispatcher entityRenderDispatcher_1) {
		super(entityRenderDispatcher_1, new RoadrunnerModel(), 0.2f);
	}

	@Override
	protected Identifier getTexture(Entity entity) {
		return new Identifier(Terrestrium.MOD_ID, "textures/entity/roadrunner/roadrunner.png");
	}
}
