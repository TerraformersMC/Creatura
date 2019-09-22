package com.terraformersmc.terrestrium.ai.goals;

import com.terraformersmc.terrestrium.entities.TerrestriumAmphibiousEntity;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.MobEntityWithAi;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ViewableWorld;

public class EnterWaterGoal extends MoveToTargetPosGoal {
	private TerrestriumAmphibiousEntity waterMob;

	public EnterWaterGoal(MobEntityWithAi mobEntityWithAi_1, double double_1, int int_1) {
		super(mobEntityWithAi_1, double_1, int_1);
	}

	public boolean canStart() {
		return super.canStart()  && !this.waterMob.isInsideWater() && this.waterMob.y >= (double)(this.waterMob.world.getSeaLevel() - 3);
	}

	public boolean shouldContinue() {
		return super.shouldContinue();
	}

	@Override
	protected boolean isTargetPos(ViewableWorld viewableWorld, BlockPos pos) {
		BlockPos blockPos_2 = pos.up();
		return (viewableWorld.isWaterAt(pos) && viewableWorld.isAir(blockPos_2.up())) && viewableWorld.getBlockState(pos).isTranslucent(viewableWorld, pos);
	}

	public void start() {
		this.waterMob.setTargetingUnderwater(true);
		this.waterMob.setNavigation(this.waterMob.landNavigation);
		super.start();
	}

	public void stop() {
		super.stop();
	}
}
