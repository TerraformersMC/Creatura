package com.terraformersmc.terrestrium;

import com.terraformersmc.terrestrium.entities.WormEntity;
import com.terraformersmc.terrestrium.entities.WormRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;

// This class is an entrypoint
@SuppressWarnings("unused")
public class TerrestriumClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(WormEntity.class, ((entityRenderDispatcher, context) -> new WormRenderer(entityRenderDispatcher)));
	}
}
