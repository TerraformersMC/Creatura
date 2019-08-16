package com.terraformersmc.terrestrium.ai.goals;

import com.terraformersmc.terrestrium.entities.TerrestriumWaterMob;
import net.minecraft.entity.ai.PathfindingUtil;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.util.math.Vec3d;

public class EnterWaterGoal extends Goal {
	private TerrestriumWaterMob entity;
	private double speed;
	private int minY;
	private boolean field_7248;

	public EnterWaterGoal(TerrestriumWaterMob entity, double double_1, int int_1) {
		this.entity = entity;
		this.speed = double_1;
		this.minY = int_1;
	}

	public boolean canStart() {
		return this.entity.isInsideWater() && this.entity.y < (double)(this.minY - 2);
	}

	public boolean shouldContinue() {
		return this.canStart() && !this.field_7248;
	}

	public void tick() {
		if (this.entity.y < (double)(this.minY - 1) && (this.entity.getNavigation().isIdle())) {
			Vec3d vec3d_1 = PathfindingUtil.method_6373(this.entity, 4, 8, new Vec3d(this.entity.x, (double)(this.minY - 1), this.entity.z));
			if (vec3d_1 == null) {
				this.field_7248 = true;
				return;
			}

			this.entity.getNavigation().startMovingTo(vec3d_1.x, vec3d_1.y, vec3d_1.z, this.speed);
		}

	}

	public void start() {
		this.entity.setTargetingUnderwater(true);
		this.field_7248 = false;
	}

	public void stop() {
		this.entity.setTargetingUnderwater(false);
	}
}
