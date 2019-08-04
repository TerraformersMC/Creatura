package com.terraformersmc.terrestrium;

import com.terraformersmc.terrestrium.init.TerrestriumEntities;
import com.terraformersmc.terrestrium.init.TerrestriumItems;
import net.fabricmc.api.ModInitializer;

public class Terrestrium implements ModInitializer {
	public static final String MOD_ID = "terrestrium";

	@Override
	public void onInitialize() {
		TerrestriumEntities.register();
		TerrestriumItems.register();
	}
}
