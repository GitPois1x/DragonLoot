package net.dragonloot.item;

import net.dragonloot.init.ConfigInit;
import net.dragonloot.init.ItemInit;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.entity.EquipmentSlot;
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
    private static final int[] PROTECTION_AMOUNTS = new int[] { ConfigInit.CONFIG.dragon_armor_protection_boots,
            ConfigInit.CONFIG.dragon_armor_protection_leggings, ConfigInit.CONFIG.dragon_armor_protection_chest,
            ConfigInit.CONFIG.dragon_armor_protection_helmet };

    @Override
    public int getDurability(EquipmentSlot equipmentSlot) {
        return BASE_DURABILITY[equipmentSlot.getEntitySlotId()] * ConfigInit.CONFIG.dragon_armor_durability_multiplier;
    }

    @Override
    public int getProtectionAmount(EquipmentSlot equipmentSlot) {
        return PROTECTION_AMOUNTS[equipmentSlot.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 15;
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
    @Environment(EnvType.CLIENT)
    public String getName() {
        return "dragon_armor";
    }

    @Override
    public float getToughness() {
        return 3.0F;
    }

    @Override
    public float getKnockbackResistance() {
        return 1.0F;
    }

}