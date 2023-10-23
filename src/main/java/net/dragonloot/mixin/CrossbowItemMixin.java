package net.dragonloot.mixin;

import net.dragonloot.init.ItemInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.item.ArrowItem;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

@Mixin(CrossbowItem.class)
public class CrossbowItemMixin {

    @Inject(method = "getSpeed", at = @At("HEAD"), cancellable = true)
    private static void getSpeedMixin(ItemStack stack, CallbackInfoReturnable<Float> info) {
        if (stack.getItem() == ItemInit.DRAGON_CROSSBOW_ITEM) {
            if (hasProjectile(stack, Items.FIREWORK_ROCKET)) {
                info.setReturnValue(1.6F);
            } else {
                info.setReturnValue(3.15F);
            }
        }
    }

    @Shadow
    public static boolean hasProjectile(ItemStack crossbow, Item projectile) {
        return false;
    }
}