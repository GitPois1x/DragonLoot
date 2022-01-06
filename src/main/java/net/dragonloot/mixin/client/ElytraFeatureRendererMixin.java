package net.dragonloot.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.dragonloot.entity.model.DragonElytraEntityModel;
import net.dragonloot.init.ItemInit;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.ElytraFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
@Mixin(ElytraFeatureRenderer.class)
public abstract class ElytraFeatureRendererMixin<T extends LivingEntity, M extends EntityModel<T>> extends FeatureRenderer<T, M> {
    private static final Identifier DRAGON_ELYTRA_TEXTURE = new Identifier("dragonloot:textures/entity/dragon_elytra.png");
    private final DragonElytraEntityModel<T> dragonElytraModel = new DragonElytraEntityModel<>(DragonElytraEntityModel.getTexturedModelData().createModel());

    public ElytraFeatureRendererMixin(FeatureRendererContext<T, M> context) {
        super(context);
    }

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i, T livingEntity, float f, float g, float h, float j, float k, float l, CallbackInfo info) {
        ItemStack itemStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
        if (itemStack.getItem() == ItemInit.UPGRADED_DRAGON_CHESTPLATE) {
            matrixStack.push();
            matrixStack.translate(0.0D, 0.0D, 0.02D);
            this.getContextModel().copyStateTo(this.dragonElytraModel);
            this.dragonElytraModel.setAngles(livingEntity, f, g, j, k, l);
            VertexConsumer vertexConsumer = ItemRenderer.getArmorGlintConsumer(vertexConsumerProvider, RenderLayer.getArmorCutoutNoCull(DRAGON_ELYTRA_TEXTURE), false, itemStack.hasGlint());
            this.dragonElytraModel.render(matrixStack, vertexConsumer, i, OverlayTexture.DEFAULT_UV, 1.0F, 1.0F, 1.0F, 1.0F);
            matrixStack.pop();
            info.cancel();
        }
    }

}
