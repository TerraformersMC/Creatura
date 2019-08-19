package com.terraformersmc.terrestrium.ai.goals;

import com.terraformersmc.terrestrium.entities.crocodile.CrocodileEntity;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;

public class CrocodileAttackGoal extends MeleeAttackGoal {

	private final CrocodileEntity entity;

	public CrocodileAttackGoal(CrocodileEntity entity, double double_1, boolean boolean_1) {
		super(entity, double_1, boolean_1);
		this.entity = entity;
	}

	@Override
	public void start() {
		super.start();
		entity.setTargeting(true);
	}

	@Override
	public void stop() {
		super.stop();
		entity.setTargeting(false);
	}

	public boolean isTargeting() {
		return entity.isTargeting();
	}
}
