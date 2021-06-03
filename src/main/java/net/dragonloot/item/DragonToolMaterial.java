package net.dragonloot.item;

import net.dragonloot.init.ConfigInit;
import net.dragonloot.init.ItemInit;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class DragonToolMaterial implements ToolMaterial {

    private DragonToolMaterial() {
    }

    private static DragonToolMaterial INSTANCE = null;

    public static DragonToolMaterial getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DragonToolMaterial();
        }
        return INSTANCE;
    }

    @Override
    public int getDurability() {
        return 67 * ConfigInit.CONFIG.dragon_item_durability_multiplier;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 12;
    }

    @Override
    public float getAttackDamage() {
        return ConfigInit.CONFIG.dragon_item_base_damage;
    }

    @Override
    public int getMiningLevel() {
        return 5;
    }

    @Override
    public int getEnchantability() {
        return 20;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ItemInit.DRAGON_SCALE_ITEM);
    }
}
