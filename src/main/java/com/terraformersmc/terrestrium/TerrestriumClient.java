package com.terraformersmc.terrestrium;

import com.terraformersmc.terrestrium.entities.roadrunner.RoadrunnerEntity;
import com.terraformersmc.terrestrium.entities.roadrunner.RoadrunnerRenderer;
import com.terraformersmc.terrestrium.entities.worm.WormEntity;
import com.terraformersmc.terrestrium.entities.worm.WormRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;

// This class is an entrypoint
@SuppressWarnings("unused")
public class TerrestriumClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(WormEntity.class, ((entityRenderDispatcher, context) -> new WormRenderer(entityRenderDispatcher)));
		EntityRendererRegistry.INSTANCE.register(RoadrunnerEntity.class, ((entityRenderDispatcher, context) -> new RoadrunnerRenderer(entityRenderDispatcher)));
	}
}
