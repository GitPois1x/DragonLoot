package net.dragonloot.item;

import net.minecraft.item.ShovelItem;

public class DragonShovelItem extends ShovelItem {

    public DragonShovelItem(Settings settings) {
        super(DragonToolMaterial.getInstance(), 1.5f, -3f, settings);
    }

}
