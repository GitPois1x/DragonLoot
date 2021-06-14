package net.dragonloot.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.dragonloot.init.ItemInit;
import net.dragonloot.item.DragonArmor;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.collection.DefaultedList;

@Mixin(PlayerInventory.class)
public class PlayerInventoryMixin {
    @Shadow
    public final PlayerEntity player;
    @Shadow
    @Final
    @Mutable
    public final DefaultedList<ItemStack> armor;

    public PlayerInventoryMixin(PlayerEntity player) {
        this.armor = DefaultedList.ofSize(4, ItemStack.EMPTY);
        this.player = player;
    }

    @Inject(method = "damageArmor", at = @At("HEAD"), cancellable = true)
    public void damageArmorMixin(DamageSource damageSource, float amount, int[] slots, CallbackInfo info) {
        ItemStack item = this.armor.get(2);
        if (amount > 0.0F && item.getItem() instanceof DragonArmor && item.isItemEqualIgnoreDamage(new ItemStack(ItemInit.UPGRADED_DRAGON_CHESTPLATE)) && item.getDamage() == (item.getMaxDamage() - 1)) {
            this.armor.get(2).decrement(1);
            this.armor.set(2, new ItemStack(Items.ELYTRA));
            this.armor.get(2).setDamage(Items.ELYTRA.getMaxDamage());
            if (!this.player.isSilent()) {
                this.player.world.playSound(this.player.getX(), this.player.getY(), this.player.getZ(), SoundEvents.ENTITY_ITEM_BREAK, this.player.getSoundCategory(), 0.8F, 0.8F + this.player.world.random.nextFloat() * 0.4F, false);
            }
        }
    }

}
