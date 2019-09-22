package com.terraformersmc.terrestrium.ai.goals;

import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.EnumSet;
import java.util.Random;

public class WanderAroundOnSurfaceGoal extends Goal {
	private final MobEntityWithAi mob;
	private double x;
	private double y;
	private double z;
	private final double speed;
	private final World world;

	public WanderAroundOnSurfaceGoal(MobEntityWithAi mobEntityWithAi_1, double double_1) {
		this.mob = mobEntityWithAi_1;
		this.speed = double_1;
		this.world = mobEntityWithAi_1.world;
		this.setControls(EnumSet.of(Control.MOVE));
	}

	public boolean canStart() {
		if (!this.world.isDaylight()) {
			return false;
		} else if (this.mob.isInsideWater()) {
			return false;
		} else {
			Vec3d vec3d_1 = this.getWanderTarget();
			if (vec3d_1 == null) {
				return false;
			} else {
				this.x = vec3d_1.x;
				this.y = vec3d_1.y;
				this.z = vec3d_1.z;
				return true;
			}
		}
	}

	public boolean shouldContinue() {
		return !this.mob.getNavigation().isIdle();
	}

	public void start() {
		this.mob.getNavigation().startMovingTo(this.x, this.y, this.z, this.speed);
	}

	private Vec3d getWanderTarget() {
		Random random_1 = this.mob.getRand();
		BlockPos blockPos_1 = new BlockPos(this.mob.x, this.mob.getBoundingBox().minY, this.mob.z);

		for(int int_1 = 0; int_1 < 10; ++int_1) {
			BlockPos blockPos_2 = blockPos_1.add(random_1.nextInt(20) - 10, 2 - random_1.nextInt(8), random_1.nextInt(20) - 10);
			if (this.world.getBlockState(blockPos_2).getBlock() == Blocks.WATER) {
				return new Vec3d((double)blockPos_2.getX(), (double)blockPos_2.getY(), (double)blockPos_2.getZ());
			}
		}

		return null;
	}
}
