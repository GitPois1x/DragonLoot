package net.dragonloot.item;

import net.minecraft.item.BowItem;
import net.projectile_damage.api.IProjectileWeapon;

public class DragonBowItem extends BowItem {

    public DragonBowItem(Settings settings) {
        super(settings);
        ((IProjectileWeapon) this).setProjectileDamage(8.5f);
    }

}
