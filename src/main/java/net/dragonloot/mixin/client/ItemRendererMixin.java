package net.dragonloot.mixin.client;

import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.dragonloot.init.ItemInit;
import net.dragonloot.item.render.DragonTridentItemRenderer;
import net.dragonloot.mixin.access.ItemRendererAccess;
import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
@Mixin(ItemRenderer.class)
public abstract class ItemRendererMixin {
    @Shadow
    private final ItemModels models;

    public ItemRendererMixin(BakedModelManager baker) {
        this.models = new ItemModels(baker);
    }

    @Inject(method = "renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformation$Mode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;IILnet/minecraft/client/render/model/BakedModel;)V", at = @At(value = "HEAD"), cancellable = true)
    private void renderItemMixin(ItemStack stack, ModelTransformation.Mode renderMode, boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay, BakedModel model, CallbackInfo info) {
        if (!stack.isEmpty() && stack.getItem() == ItemInit.DRAGON_TRIDENT_ITEM) {
            matrices.push();
            boolean bl = renderMode == ModelTransformation.Mode.GUI || renderMode == ModelTransformation.Mode.GROUND || renderMode == ModelTransformation.Mode.FIXED;
            if (stack.getItem() == ItemInit.DRAGON_TRIDENT_ITEM && bl) {
                model = ((ItemRendererAccess) this).getModelsInvoker().getModelManager().getModel(new ModelIdentifier("dragonloot" + ":dragon_trident#inventory"));
            }

            model.getTransformation().getTransformation(renderMode).apply(leftHanded, matrices);
            matrices.translate(-0.5D, -0.5D, -0.5D);
            if (model.isBuiltin() || stack.getItem() == ItemInit.DRAGON_TRIDENT_ITEM && !bl) {
                DragonTridentItemRenderer.render(stack, renderMode, matrices, vertexConsumers, light, overlay);
            } else {
                RenderLayer renderLayer = RenderLayers.getItemLayer(stack, true);
                VertexConsumer vertexConsumer4;
                vertexConsumer4 = ItemRenderer.getDirectItemGlintConsumer(vertexConsumers, renderLayer, true, stack.hasGlint());

                ((ItemRendererAccess) this).renderBakedItemModelInvoker(model, stack, light, overlay, matrices, vertexConsumer4);
            }

            matrices.pop();
            info.cancel();
        }
    }

    @Inject(method = "getModel", at = @At(value = "HEAD"), cancellable = true)
    public void getHeldItemModelMixin(ItemStack stack, @Nullable World world, @Nullable LivingEntity entity, int seed, CallbackInfoReturnable<BakedModel> info) {
        Item item = stack.getItem();
        BakedModel bakedModel2;
        if (item == ItemInit.DRAGON_TRIDENT_ITEM) {
            bakedModel2 = this.models.getModelManager().getModel(new ModelIdentifier("minecraft:trident_in_hand#inventory"));
            ClientWorld clientWorld = world instanceof ClientWorld ? (ClientWorld) world : null;
            BakedModel bakedModel3 = bakedModel2.getOverrides().apply(bakedModel2, stack, clientWorld, entity, seed);
            info.setReturnValue(bakedModel3 == null ? this.models.getModelManager().getMissingModel() : bakedModel3);
        }

    }
}
