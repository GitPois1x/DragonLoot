package net.dragonloot.init;

import net.dragonloot.item.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ItemInit {
        // Items
        public static final DragonScaleItem DRAGON_SCALE_ITEM = new DragonScaleItem(
                        new Item.Settings().group(ItemGroup.MISC).fireproof());
        // Armor
        public static final ArmorMaterial DRAGON_ARMOR_MATERIAL = new DragonArmorMaterial();
        public static final Item DRAGON_HELMET = new DragonArmor(DRAGON_ARMOR_MATERIAL, EquipmentSlot.HEAD,
                        new Item.Settings().group(ItemGroup.COMBAT).fireproof());
        public static final Item DRAGON_CHESTPLATE = new DragonArmor(DRAGON_ARMOR_MATERIAL, EquipmentSlot.CHEST,
                        new Item.Settings().group(ItemGroup.COMBAT).fireproof());
        public static final Item DRAGON_LEGGINGS = new DragonArmor(DRAGON_ARMOR_MATERIAL, EquipmentSlot.LEGS,
                        new Item.Settings().group(ItemGroup.COMBAT).fireproof());
        public static final Item DRAGON_BOOTS = new DragonArmor(DRAGON_ARMOR_MATERIAL, EquipmentSlot.FEET,
                        new Item.Settings().group(ItemGroup.COMBAT).fireproof());
        public static final Item UPGRADED_DRAGON_CHESTPLATE = new DragonArmor(DRAGON_ARMOR_MATERIAL,
                        EquipmentSlot.CHEST, new Item.Settings().group(ItemGroup.COMBAT).fireproof());

        public static void init() {
                // Items
                Registry.register(Registry.ITEM, new Identifier("dragonloot", "dragon_scale"), DRAGON_SCALE_ITEM);
                // Armor
                Registry.register(Registry.ITEM, new Identifier("dragonloot", "dragon_helmet"), DRAGON_HELMET);
                Registry.register(Registry.ITEM, new Identifier("dragonloot", "dragon_chestplate"), DRAGON_CHESTPLATE);
                Registry.register(Registry.ITEM, new Identifier("dragonloot", "dragon_leggings"), DRAGON_LEGGINGS);
                Registry.register(Registry.ITEM, new Identifier("dragonloot", "dragon_boots"), DRAGON_BOOTS);
                Registry.register(Registry.ITEM, new Identifier("dragonloot", "upgraded_dragon_chestplate"),
                                UPGRADED_DRAGON_CHESTPLATE);
        }
}
