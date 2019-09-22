package com.terraformersmc.terrestrium;

import com.terraformersmc.terrestrium.entities.crocodile.CrocodileEntity;
import com.terraformersmc.terrestrium.entities.crocodile.CrocodileRenderer;
import com.terraformersmc.terrestrium.entities.penguin.PenguinEntity;
import com.terraformersmc.terrestrium.entities.penguin.PenguinRenderer;
import com.terraformersmc.terrestrium.entities.roadrunner.RoadrunnerEntity;
import com.terraformersmc.terrestrium.entities.roadrunner.RoadrunnerRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;

// This class is an entrypoint
@SuppressWarnings("unused")
public class TerrestriumClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EntityRendererRegistry.INSTANCE.register(RoadrunnerEntity.class, ((entityRenderDispatcher, context) -> new RoadrunnerRenderer(entityRenderDispatcher)));
		EntityRendererRegistry.INSTANCE.register(PenguinEntity.class, ((entityRenderDispatcher, context) -> new PenguinRenderer(entityRenderDispatcher)));
		EntityRendererRegistry.INSTANCE.register(CrocodileEntity.class, ((entityRenderDispatcher, context) -> new CrocodileRenderer(entityRenderDispatcher)));
	}
}
