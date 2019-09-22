package com.terraformersmc.terrestrium.ai.goals;

import com.terraformersmc.terrestrium.entities.crocodile.CrocodileEntity;
import net.minecraft.entity.ai.goal.SwimAroundGoal;
import net.minecraft.entity.mob.MobEntityWithAi;

public class WanderInWaterGoal extends SwimAroundGoal {
	private final CrocodileEntity crocodile;

	public WanderInWaterGoal(MobEntityWithAi mobEntityWithAi_1, double double_1, int int_1, CrocodileEntity crocodile) {
		super(mobEntityWithAi_1, double_1, int_1);
		this.crocodile = crocodile;
	}
}
