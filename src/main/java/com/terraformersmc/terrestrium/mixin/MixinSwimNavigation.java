package com.terraformersmc.terrestrium.mixin;

import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.SwimNavigation;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(SwimNavigation.class)
public abstract class MixinSwimNavigation extends EntityNavigation {

	public MixinSwimNavigation(MobEntity mobEntity_1, World world_1) {
		super(mobEntity_1, world_1);
	}

	/**
	 * @author Wyn Price
	 */
	@Overwrite
	public void method_6339() {
		if (this.currentPath != null) {
			Vec3d vec3d_1 = this.getPos();
			float float_1 = this.entity.getWidth();
			float float_2 = float_1 > 0.75F ? float_1 / 2.0F : 0.75F - float_1 / 2.0F;
			Vec3d vec3d_2 = this.entity.getVelocity();
			if (Math.abs(vec3d_2.x) > 0.2D || Math.abs(vec3d_2.z) > 0.2D) {
				float_2 = (float)((double)float_2 * vec3d_2.length() * 6.0D);
			}

			Vec3d vec3d_3 = this.currentPath.getCurrentPosition();
			if (Math.abs(this.entity.x - (vec3d_3.x - (this.entity.getWidth() + 1) / 2D)) < (double)float_2 && Math.abs(this.entity.z - (vec3d_3.z - (this.entity.getWidth() + 1) / 2D)) < (double)float_2 && Math.abs(this.entity.y - vec3d_3.y) < (double)(float_2 * 2.0F)) {
				this.currentPath.next();
			}

			for(int int_2 = Math.min(this.currentPath.getCurrentNodeIndex() + 6, this.currentPath.getLength() - 1); int_2 > this.currentPath.getCurrentNodeIndex(); --int_2) {
				vec3d_3 = this.currentPath.getNodePosition(this.entity, int_2);
				if (vec3d_3.squaredDistanceTo(vec3d_1) <= 36.0D && this.canPathDirectlyThrough(vec3d_1, vec3d_3, 0, 0, 0)) {
					this.currentPath.setCurrentNodeIndex(int_2);
					break;
				}
			}

			this.method_6346(vec3d_1);
		}
	}
}
