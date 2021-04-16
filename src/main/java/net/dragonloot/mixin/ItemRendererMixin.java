package net.dragonloot.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.dragonloot.DragonLootMain;
import net.dragonloot.compat.netheriteplus.NetheritePlusCompat;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.model.TridentEntityModel;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    private static final TridentEntityModel modelTrident = new TridentEntityModel();

    @Inject(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", at = @At(value = "HEAD"), cancellable = true)
    public void renderItem(ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded,
            MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model,
            CallbackInfo info) {
        if (!stack.isEmpty() && FabricLoader.getInstance().isModLoaded("netherite_plus")
                && stack.getItem() == NetheritePlusCompat.DRAGON_TRIDENT_ITEM) {
            matrices.push();
            boolean bl = renderMode == ModelTransformation.Mode.GUI || renderMode == ModelTransformation.Mode.GROUND
                    || renderMode == ModelTransformation.Mode.FIXED;
            if (stack.getItem() == NetheritePlusCompat.DRAGON_TRIDENT_ITEM && bl) {
                model = ((ItemRendererInterface) this).getModelsInvoker().getModelManager()
                        .getModel(new ModelIdentifier("dragonloot" + ":dragon_trident#inventory"));
            }

            model.getTransformation().getTransformation(renderMode).apply(leftHanded, matrices);
            matrices.translate(-0.5D, -0.5D, -0.5D);
            if (model.isBuiltin() || stack.getItem() == NetheritePlusCompat.DRAGON_TRIDENT_ITEM && !bl) {
                matrices.push();
                matrices.scale(1.0F, -1.0F, -1.0F);
                VertexConsumer vertexConsumer2 = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers,
                        modelTrident.getLayer(DragonLootMain.ID("textures/entity/dragon_trident.png")), false,
                        stack.hasGlint());
                modelTrident.render(matrices, vertexConsumer2, light, overlay, 1.0F, 1.0F, 1.0F, 1.0F);
                matrices.pop();
            } else {
                RenderLayer renderLayer = RenderLayers.getItemLayer(stack, true);
                VertexConsumer vertexConsumer4;
                vertexConsumer4 = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, renderLayer, true,
                        stack.hasGlint());

                ((ItemRendererInterface) this).renderBakedItemModelInvoker(model, stack, light, overlay, matrices,
                        vertexConsumer4);
            }

            matrices.pop();
            info.cancel();
        }
    }
}
