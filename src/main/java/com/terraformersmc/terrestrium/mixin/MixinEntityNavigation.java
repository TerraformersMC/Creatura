package com.terraformersmc.terrestrium.mixin;

import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(EntityNavigation.class)
public class MixinEntityNavigation {

	@Shadow
	protected float field_6683;
	@Shadow
	@Final
	protected MobEntity entity;
	@Shadow protected Path currentPath;


	/**
	 * @author Wyn Price
	 */
	@Overwrite
	public void method_6339() {
		Vec3d vec3d_1 = this.getPos();
		this.field_6683 = this.entity.getWidth() > 0.75F ? this.entity.getWidth() / 2.0F : 0.75F - this.entity.getWidth() / 2.0F;
		Vec3d vec3d_2 = this.currentPath.getCurrentPosition();
		if (Math.abs(this.entity.x - (vec3d_2.x - (this.entity.getWidth() + 1) / 2D)) < (double)this.field_6683 && Math.abs(this.entity.z - (vec3d_2.z - (this.entity.getWidth() + 1) / 2D)) < (double)this.field_6683 && Math.abs(this.entity.y - vec3d_2.y) < 1.0D) {
			this.currentPath.setCurrentNodeIndex(this.currentPath.getCurrentNodeIndex() + 1);
		}

		this.method_6346(vec3d_1);
	}

	@Shadow
	protected void method_6346(Vec3d vec) {

	}

	@Shadow
	protected Vec3d getPos() {
		return Vec3d.ZERO;
	}



}
