package net.dragonloot.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.fabricmc.api.Environment;
import net.dragonloot.init.ItemInit;
import net.dragonloot.item.DragonCrossbowItem;
import net.fabricmc.api.EnvType;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Arm;
import net.minecraft.util.Hand;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

    @Shadow
    private final MinecraftClient client;

    public HeldItemRendererMixin(MinecraftClient client) {
        this.client = client;
    }

    @ModifyVariable(method = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lnet/minecraft/client/network/ClientPlayerEntity;I)V", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getActiveItem()Lnet/minecraft/item/ItemStack;"), ordinal = 0)
    private boolean renderItemMixinOne(boolean original) {
        if (client.player.getActiveItem().getItem() == ItemInit.DRAGON_CROSSBOW_ITEM) {
            return false;
        } else
            return original;
    }

    @ModifyVariable(method = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(FLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider$Immediate;Lnet/minecraft/client/network/ClientPlayerEntity;I)V", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/client/network/ClientPlayerEntity;getMainHandStack()Lnet/minecraft/item/ItemStack;"), ordinal = 0)
    private boolean renderItemMixinTwo(boolean original) {
        if (client.player.getOffHandStack().getItem() == ItemInit.DRAGON_CROSSBOW_ITEM
                && DragonCrossbowItem.isCharged(client.player.getOffHandStack())) {
            return !client.player.getMainHandStack().isEmpty();
        } else
            return original;
    }

    @Inject(method = "renderFirstPersonItem", at = @At(value = "HEAD"), cancellable = true)
    private void renderFirstPersonItemMixin(AbstractClientPlayerEntity player, float tickDelta, float pitch, Hand hand,
            float swingProgress, ItemStack item, float equipProgress, MatrixStack matrices,
            VertexConsumerProvider vertexConsumers, int light, CallbackInfo info) {
        if (item.getItem() == ItemInit.DRAGON_CROSSBOW_ITEM) {
            matrices.push();
            boolean bl = hand == Hand.MAIN_HAND;
            Arm arm = bl ? player.getMainArm() : player.getMainArm().getOpposite();
            boolean bl4 = CrossbowItem.isCharged(item);
            boolean bl3 = arm == Arm.RIGHT;
            int i = bl3 ? 1 : -1;
            float x;
            float y;
            float w;
            float v;
            if (player.isUsingItem() && player.getItemUseTimeLeft() > 0 && player.getActiveHand() == hand) {
                this.applyEquipOffset(matrices, arm, equipProgress);
                matrices.translate((double) ((float) i * -0.4785682F), -0.0943870022892952D, 0.05731530860066414D);
                matrices.multiply(Vector3f.POSITIVE_X.getDegreesQuaternion(-11.935F));
                matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((float) i * 65.3F));
                matrices.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion((float) i * -9.785F));
                v = (float) item.getMaxUseTime() - ((float) this.client.player.getItemUseTimeLeft() - tickDelta + 1.0F);
                w = v / (float) CrossbowItem.getPullTime(item);

                if (w > 1.0F) {
                    w = 1.0F;
                }

                if (w > 0.1F) {
                    x = MathHelper.sin((v - 0.1F) * 1.3F);
                    y = w - 0.1F;
                    float k = x * y;
                    matrices.translate((double) (k * 0.0F), (double) (k * 0.004F), (double) (k * 0.0F));
                }

                matrices.translate((double) (w * 0.0F), (double) (w * 0.0F), (double) (w * 0.04F));
                matrices.scale(1.0F, 1.0F, 1.0F + w * 0.2F);
                matrices.multiply(Vector3f.NEGATIVE_Y.getDegreesQuaternion((float) i * 45.0F));
            } else {
                v = -0.4F * MathHelper.sin(MathHelper.sqrt(swingProgress) * 3.1415927F);
                w = 0.2F * MathHelper.sin(MathHelper.sqrt(swingProgress) * 6.2831855F);
                x = -0.2F * MathHelper.sin(swingProgress * 3.1415927F);
                matrices.translate((double) ((float) i * v), (double) w, (double) x);
                this.applyEquipOffset(matrices, arm, equipProgress);
                this.applySwingOffset(matrices, arm, swingProgress);
                if (bl4 && swingProgress < 0.001F) {
                    matrices.translate((double) ((float) i * -0.641864F), 0.0D, 0.0D);
                    matrices.multiply(Vector3f.POSITIVE_Y.getDegreesQuaternion((float) i * 10.0F));
                }
            }

            this.renderItem(player, item, bl3 ? ModelTransformation.Mode.FIRST_PERSON_RIGHT_HAND
                    : ModelTransformation.Mode.FIRST_PERSON_LEFT_HAND, !bl3, matrices, vertexConsumers, light);
            matrices.pop();
            info.cancel();
        }
    }

    @Shadow
    public void renderItem(LivingEntity entity, ItemStack stack, ModelTransformation.Mode renderMode,
            boolean leftHanded, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
    }

    @Shadow
    private void applyEquipOffset(MatrixStack matrices, Arm arm, float equipProgress) {
    }

    @Shadow
    private void applySwingOffset(MatrixStack matrices, Arm arm, float swingProgress) {
    }

}
