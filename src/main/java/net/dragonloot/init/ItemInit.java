package net.dragonloot.init;

import net.dragonloot.DragonLootMain;
import net.dragonloot.compat.recipes.CompatRecipes;
import net.dragonloot.compat.recipes.RecipeGenerator;
import net.dragonloot.item.*;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;

public class ItemInit {
    // Items
    public static final DragonScaleItem DRAGON_SCALE_ITEM = new DragonScaleItem(new Item.Settings().fireproof().group(ItemGroupInit.DRAGON_ITEM_GROUP));
    public static final HorseArmorItem DRAGON_HORSE_ARMOR_ITEM = new HorseArmorItem(18, "dragon", new Item.Settings().maxCount(1).fireproof().group(ItemGroupInit.DRAGON_ITEM_GROUP));
    // Armor
    public static final ArmorMaterial DRAGON_ARMOR_MATERIAL = DragonArmorMaterial.getInstance();

    public static final Item DRAGON_HELMET = new DragonArmor(DRAGON_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().fireproof().group(ItemGroupInit.DRAGON_ITEM_GROUP));
    public static final Item DRAGON_CHESTPLATE = new DragonArmor(DRAGON_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().fireproof().group(ItemGroupInit.DRAGON_ITEM_GROUP));
    public static final Item DRAGON_LEGGINGS = new DragonArmor(DRAGON_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().fireproof().group(ItemGroupInit.DRAGON_ITEM_GROUP));
    public static final Item DRAGON_BOOTS = new DragonArmor(DRAGON_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().fireproof().group(ItemGroupInit.DRAGON_ITEM_GROUP));
    public static final Item UPGRADED_DRAGON_CHESTPLATE = new DragonArmor(DRAGON_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().fireproof().group(ItemGroupInit.DRAGON_ITEM_GROUP));

    // Tools
    public static final DragonPickaxeItem DRAGON_PICKAXE_ITEM = new DragonPickaxeItem(new Item.Settings().fireproof().group(ItemGroupInit.DRAGON_ITEM_GROUP));
    public static final DragonAxeItem DRAGON_AXE_ITEM = new DragonAxeItem(new Item.Settings().fireproof().group(ItemGroupInit.DRAGON_ITEM_GROUP));
    public static final DragonShovelItem DRAGON_SHOVEL_ITEM = new DragonShovelItem(new Item.Settings().fireproof().group(ItemGroupInit.DRAGON_ITEM_GROUP));
    public static final DragonHoeItem DRAGON_HOE_ITEM = new DragonHoeItem(new Item.Settings().fireproof().group(ItemGroupInit.DRAGON_ITEM_GROUP));

    // Weapons
    public static final DragonSwordItem DRAGON_SWORD_ITEM = new DragonSwordItem(new Item.Settings().fireproof().group(ItemGroupInit.DRAGON_ITEM_GROUP));
    public static final DragonBowItem DRAGON_BOW_ITEM = new DragonBowItem(new Item.Settings().fireproof().group(ItemGroupInit.DRAGON_ITEM_GROUP).maxDamage(DragonToolMaterial.getInstance().getDurability()));
    public static final DragonCrossbowItem DRAGON_CROSSBOW_ITEM = new DragonCrossbowItem(new Item.Settings().fireproof().group(ItemGroupInit.DRAGON_ITEM_GROUP).maxDamage(DragonToolMaterial.getInstance().getDurability()));
    public static final DragonTridentItem DRAGON_TRIDENT_ITEM = new DragonTridentItem(new Item.Settings().maxDamage(DragonToolMaterial.getInstance().getDurability()).fireproof().group(ItemGroupInit.DRAGON_ITEM_GROUP));

    public static void init() {
        // Items
        Registry.register(Registry.ITEM, DragonLootMain.ID("dragon_scale"), DRAGON_SCALE_ITEM);
        Registry.register(Registry.ITEM, DragonLootMain.ID("dragon_horse_armor"), DRAGON_HORSE_ARMOR_ITEM);
        // Armor
        Registry.register(Registry.ITEM, DragonLootMain.ID("dragon_helmet"), DRAGON_HELMET);
        Registry.register(Registry.ITEM, DragonLootMain.ID("dragon_chestplate"), DRAGON_CHESTPLATE);
        Registry.register(Registry.ITEM, DragonLootMain.ID("dragon_leggings"), DRAGON_LEGGINGS);
        Registry.register(Registry.ITEM, DragonLootMain.ID("dragon_boots"), DRAGON_BOOTS);
        Registry.register(Registry.ITEM, DragonLootMain.ID("upgraded_dragon_chestplate"), UPGRADED_DRAGON_CHESTPLATE);
        // Tools
        Registry.register(Registry.ITEM, DragonLootMain.ID("dragon_pickaxe"), DRAGON_PICKAXE_ITEM);
        Registry.register(Registry.ITEM, DragonLootMain.ID("dragon_shovel"), DRAGON_SHOVEL_ITEM);
        Registry.register(Registry.ITEM, DragonLootMain.ID("dragon_axe"), DRAGON_AXE_ITEM);
        Registry.register(Registry.ITEM, DragonLootMain.ID("dragon_hoe"), DRAGON_HOE_ITEM);
        // Weapons
        Registry.register(Registry.ITEM, DragonLootMain.ID("dragon_sword"), DRAGON_SWORD_ITEM);
        Registry.register(Registry.ITEM, DragonLootMain.ID("dragon_bow"), DRAGON_BOW_ITEM);
        Registry.register(Registry.ITEM, DragonLootMain.ID("dragon_crossbow"), DRAGON_CROSSBOW_ITEM);
        Registry.register(Registry.ITEM, DragonLootMain.ID("dragon_trident"), DRAGON_TRIDENT_ITEM);

        CompatRecipes.loadRecipes();
        RecipeGenerator.addRecipes();
    }
}
