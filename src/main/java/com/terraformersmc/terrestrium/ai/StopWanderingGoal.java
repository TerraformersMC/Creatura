package com.terraformersmc.terrestrium.ai;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntityWithAi;

import java.util.EnumSet;

public class StopWanderingGoal extends Goal {
	int timer;
	MobEntityWithAi entityWithAi;

	public StopWanderingGoal(MobEntityWithAi entityWithAi, int timer) {
		this.setControls(EnumSet.of(Control.LOOK, Control.JUMP, Control.MOVE));
		this.timer = timer;
		this.entityWithAi = entityWithAi;
	}

	@Override
	public boolean canStart() {
		return isMoving();
	}

	public boolean shouldContinue() {
		return this.canStart() && this.timer > 0;
	}

	public void start() {
		this.timer = 60;
	}

	public void tick() {
		--this.timer;
	}

	private boolean isMoving() {
		return entityWithAi.getVelocity().getZ() > 0 || entityWithAi.getVelocity().getX() > 0;
	}
}
