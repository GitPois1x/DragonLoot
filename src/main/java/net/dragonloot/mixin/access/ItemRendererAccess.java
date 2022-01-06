package net.dragonloot.mixin.access;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.item.ItemModels;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;

@Environment(EnvType.CLIENT)
@Mixin(ItemRenderer.class)
public interface ItemRendererAccess {
    @Invoker("getModels")
    ItemModels getModelsInvoker();

    @Invoker("renderBakedItemModel")
    void renderBakedItemModelInvoker(BakedModel model, ItemStack stack, int light, int overlay, MatrixStack matrices, VertexConsumer vertexConsumer4);
}