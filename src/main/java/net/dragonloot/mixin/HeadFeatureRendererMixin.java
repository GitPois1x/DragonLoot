package net.dragonloot.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.dragonloot.init.ItemInit;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.ZombieVillagerEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.client.render.entity.model.ModelWithHead;
import net.minecraft.client.render.model.json.ModelTransformation;

@Mixin(HeadFeatureRenderer.class)
public class HeadFeatureRendererMixin {
  @Shadow
  private float field_24474;
  @Shadow
  private float field_24475;
  @Shadow
  private float field_24476;

  @Inject(method = "render", at = @At("HEAD"), cancellable = true)
  public void render(MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i,
      LivingEntity livingEntity, float f, float g, float h, float j, float k, float l, CallbackInfo info) {
    ItemStack itemStack = livingEntity.getEquippedStack(EquipmentSlot.HEAD);
    if (!(itemStack.isEmpty()) && (itemStack.getItem() == ItemInit.DRAGON_HELMET)) {
      matrixStack.push();
      matrixStack.scale(this.field_24474, this.field_24475, this.field_24476);
      boolean bl = livingEntity instanceof VillagerEntity || livingEntity instanceof ZombieVillagerEntity;
      ((ModelWithHead) ((HeadFeatureRenderer) (Object) this).getContextModel()).getHead().rotate(matrixStack);
      matrixStack.translate(0.0D, -0.25D, 0.0D);
      matrixStack.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion(180.0F));
      matrixStack.scale(0.625F, -0.625F, -0.625F);
      if (bl) {
        matrixStack.translate(0.0D, 0.1875D, 0.0D);
      }

      MinecraftClient.getInstance().getHeldItemRenderer().renderItem(livingEntity, itemStack,
          ModelTransformation.Mode.HEAD, false, matrixStack, vertexConsumerProvider, i);
      matrixStack.pop();
      info.cancel();
    }
  }
}
