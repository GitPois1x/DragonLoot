package net.dragonloot.item;

import net.dragonloot.init.ConfigInit;
import net.dragonloot.init.ItemInit;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class DragonArmorMaterial implements ArmorMaterial {

    private DragonArmorMaterial() {
    }

    private static DragonArmorMaterial INSTANCE = null;

    public static DragonArmorMaterial getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DragonArmorMaterial();
        }
        return INSTANCE;
    }

    private static final int[] BASE_DURABILITY = new int[] { 28, 32, 35, 26 };
    private static final int[] PROTECTION_AMOUNTS = new int[] { ConfigInit.CONFIG.dragon_armor_protection_boots, ConfigInit.CONFIG.dragon_armor_protection_leggings, ConfigInit.CONFIG.dragon_armor_protection_chest, ConfigInit.CONFIG.dragon_armor_protection_helmet };

    @Override
    public int getDurability(ArmorItem.Type type) {
        return BASE_DURABILITY[type.getEquipmentSlot().getEntitySlotId()] * ConfigInit.CONFIG.dragon_armor_durability_multiplier;
    }

    @Override
    public int getProtection(ArmorItem.Type type) {
        return PROTECTION_AMOUNTS[type.getEquipmentSlot().getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return ConfigInit.CONFIG.dragon_armor_enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return SoundEvents.ITEM_ARMOR_EQUIP_CHAIN;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(ItemInit.DRAGON_SCALE_ITEM);
    }

    @Override
    public String getName() {
        return "dragon";
    }

    @Override
    public float getToughness() {
        return ConfigInit.CONFIG.dragon_armor_toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return ConfigInit.CONFIG.dragon_armor_knockback_resistance;
    }

}