package net.dragonloot.entity.model;

import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3d;

@Environment(EnvType.CLIENT)
public class DragonElytraEntityModel<T extends LivingEntity> extends AnimalModel<T> {

    private final ModelPart rightWing;
    private final ModelPart leftWing;

    public DragonElytraEntityModel(ModelPart root) {
        this.leftWing = root.getChild("left_wing");
        this.rightWing = root.getChild("right_wing");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        Dilation dilation = new Dilation(1.0F);
        modelPartData.addChild("left_wing", ModelPartBuilder.create().uv(22, 0).cuboid(-10.0F, 0.0F, 0.0F, 20.0F, 21.0F, 2.0F, dilation), ModelTransform.of(5.0F, 0.0F, 0.0F, 0.2617994F, 0.0F, -0.2617994F));
        modelPartData.addChild("right_wing", ModelPartBuilder.create().uv(22, 0).mirrored().cuboid(-10.0F, 0.0F, 0.0F, 20.0F, 21.0F, 2.0F, dilation), ModelTransform.of(-5.0F, 0.0F, 0.0F, 0.2617994F, 0.0F, 0.2617994F));
        return TexturedModelData.of(modelData, 64, 32);
    }

    protected Iterable<ModelPart> getHeadParts() {
        return ImmutableList.of();
    }

    protected Iterable<ModelPart> getBodyParts() {
        return ImmutableList.of(this.leftWing, this.rightWing);
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
                o = 1.0F - (float)Math.pow(-vec3d2.y, 1.5D);
            }

            k = o * 0.34906584F + (1.0F - o) * k;
            l = o * -1.5707964F + (1.0F - o) * l;
        } else if (livingEntity.isInSneakingPose()) {
            k = 0.6981317F;
            l = -0.7853982F;
            m = 3.0F;
            n = 0.08726646F;
        }

        this.leftWing.pivotY = m;
        if (livingEntity instanceof AbstractClientPlayerEntity) {
            AbstractClientPlayerEntity abstractClientPlayerEntity = (AbstractClientPlayerEntity)livingEntity;
            abstractClientPlayerEntity.elytraPitch = (float)((double)abstractClientPlayerEntity.elytraPitch + (double)(k - abstractClientPlayerEntity.elytraPitch) * 0.1D);
            abstractClientPlayerEntity.elytraYaw = (float)((double)abstractClientPlayerEntity.elytraYaw + (double)(n - abstractClientPlayerEntity.elytraYaw) * 0.1D);
            abstractClientPlayerEntity.elytraRoll = (float)((double)abstractClientPlayerEntity.elytraRoll + (double)(l - abstractClientPlayerEntity.elytraRoll) * 0.1D);
            this.leftWing.pitch = abstractClientPlayerEntity.elytraPitch;
            this.leftWing.yaw = abstractClientPlayerEntity.elytraYaw;
            this.leftWing.roll = abstractClientPlayerEntity.elytraRoll;
        } else {
            this.leftWing.pitch = k;
            this.leftWing.roll = l;
            this.leftWing.yaw = n;
        }

        this.rightWing.yaw = -this.leftWing.yaw;
        this.rightWing.pivotY = this.leftWing.pivotY;
        this.rightWing.pitch = this.leftWing.pitch;
        this.rightWing.roll = -this.leftWing.roll;
    }
}
