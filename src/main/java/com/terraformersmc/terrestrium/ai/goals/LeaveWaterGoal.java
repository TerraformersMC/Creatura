package com.terraformersmc.terrestrium.ai.goals;

import com.terraformersmc.terrestrium.entities.TerrestriumAmphibiousEntity;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ViewableWorld;

public class LeaveWaterGoal extends MoveToTargetPosGoal {
	private final TerrestriumAmphibiousEntity waterMob;

	public LeaveWaterGoal(TerrestriumAmphibiousEntity entity, double double_1) {
		super(entity, double_1, 8, 2);
		this.waterMob = entity;
	}

	public boolean canStart() {
		return super.canStart() && this.waterMob.isInsideWater() && this.waterMob.y >= (double)(this.waterMob.world.getSeaLevel() - 3);
	}

	public boolean shouldContinue() {
		return super.shouldContinue();
	}

	protected boolean isTargetPos(ViewableWorld viewableWorld_1, BlockPos blockPos_1) {
		BlockPos blockPos_2 = blockPos_1.up();
		return (viewableWorld_1.isAir(blockPos_2) && viewableWorld_1.isAir(blockPos_2.up())) && viewableWorld_1.getBlockState(blockPos_1).hasSolidTopSurface(viewableWorld_1, blockPos_1, this.waterMob);
	}

	public void start() {
		this.waterMob.setTargetingUnderwater(false);
		this.waterMob.navigation = this.waterMob.landNavigation;
		super.start();
	}

	public void stop() {
		super.stop();
	}
}
