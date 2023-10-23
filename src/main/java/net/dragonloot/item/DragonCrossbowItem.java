package net.dragonloot.item;

import net.dragonloot.init.ItemInit;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.ItemStack;
import net.projectile_damage.api.IProjectileWeapon;

public class DragonCrossbowItem extends CrossbowItem {

    public DragonCrossbowItem(Settings settings) {
        super(settings);
        ((IProjectileWeapon) this).setProjectileDamage(12.25f);
    }

    @Override
    public boolean isUsedOnRelease(ItemStack stack) {
        return stack.getItem() == ItemInit.DRAGON_CROSSBOW_ITEM;
    }
}
