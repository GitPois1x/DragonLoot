package net.dragonloot.entity.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class DragonElytraEntityModel<T extends LivingEntity> extends AnimalModel<T> {
    private final ModelPart field_3364;
    private final ModelPart field_3365 = new ModelPart(this, 0, 0);

    public DragonElytraEntityModel() {
        this.field_3365.addCuboid(-10.0F, 0.0F, 0.0F, 20.0F, 21.0F, 2.0F, 1.0F);
        this.field_3364 = new ModelPart(this, 0, 0);
        this.field_3364.mirror = true;
        this.field_3364.addCuboid(-10.0F, 0.0F, 0.0F, 20.0F, 21.0F, 2.0F, 1.0F);
    }

    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of();
    }

    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.field_3365, this.field_3364);
    }

    public void setAngles(T livingEntity, float f, float g, float h, float i, float j) {
        float k = 0.2617994F;
        float l = -0.2617994F;
        float m = 0.0F;
        float n = 0.0F;
        if (livingEntity.isFallFlying()) {
            float o = 1.0F;
            Vec3d vec3d = livingEntity.getVelocity();
            if (vec3d.y < 0.0D) {
                Vec3d vec3d2 = vec3d.normalize();
                o = 1.0F - (float) Math.pow(-vec3d2.y, 1.5D);
            }

            k = o * 0.34906584F + (1.0F - o) * k;
            l = o * -1.5707964F + (1.0F - o) * l;
        } else if (livingEntity.isInSneakingPose()) {
            k = 0.6981317F;
            l = -0.7853982F;
            m = 3.0F;
            n = 0.08726646F;
        }

        this.field_3365.pivotX = 5.0F;
        this.field_3365.pivotY = m;
        if (livingEntity instanceof AbstractClientPlayerEntity) {
            AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity) livingEntity;
            abstractClientPlayerEntity.elytraPitch = (float) ((double) abstractClientPlayerEntity.elytraPitch
                    + (double) (k - abstractClientPlayerEntity.elytraPitch) * 0.1D);
            abstractClientPlayerEntity.elytraYaw = (float) ((double) abstractClientPlayerEntity.elytraYaw
                    + (double) (n - abstractClientPlayerEntity.elytraYaw) * 0.1D);
            abstractClientPlayerEntity.elytraRoll = (float) ((double) abstractClientPlayerEntity.elytraRoll
                    + (double) (l - abstractClientPlayerEntity.elytraRoll) * 0.1D);
            this.field_3365.pitch = abstractClientPlayerEntity.elytraPitch;
            this.field_3365.yaw = abstractClientPlayerEntity.elytraYaw;
            this.field_3365.roll = abstractClientPlayerEntity.elytraRoll;
        } else {
            this.field_3365.pitch = k;
            this.field_3365.roll = l;
            this.field_3365.yaw = n;
        }

        this.field_3364.pivotX = -this.field_3365.pivotX;
        this.field_3364.yaw = -this.field_3365.yaw;
        this.field_3364.pivotY = this.field_3365.pivotY;
        this.field_3364.pitch = this.field_3365.pitch;
        this.field_3364.roll = -this.field_3365.roll;
    }
}
