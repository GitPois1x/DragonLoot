package net.dragonloot.init;

import net.dragonloot.DragonLootMain;
import net.dragonloot.compat.recipes.CompatRecipes;
import net.dragonloot.compat.recipes.RecipeGenerator;
import net.dragonloot.item.*;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.HorseArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ItemInit {

    // ItemGroup
    public static ItemGroup DRAGON_ITEM_GROUP = FabricItemGroup.builder(DragonLootMain.ID("dragonloot")).displayName(Text.literal("DragonLoot")).icon(() -> new ItemStack(ItemInit.DRAGON_SCALE_ITEM)).entries((enabledFeatures, entries, operatorEnabled) -> {
        entries.add(BlockInit.DRAGON_ANVIL_BLOCK);
    }).build();

    // Items
    public static final Item DRAGON_SCALE_ITEM = register("dragon_scale", new DragonScaleItem(new Item.Settings().fireproof()));
    public static final Item DRAGON_HORSE_ARMOR_ITEM = register("dragon_horse_armor", new HorseArmorItem(18, "dragon", new Item.Settings().maxCount(1).fireproof()));
    // Armor
    public static final ArmorMaterial DRAGON_ARMOR_MATERIAL = DragonArmorMaterial.getInstance();

    public static final Item DRAGON_HELMET = register("dragon_helmet", new DragonArmor(DRAGON_ARMOR_MATERIAL, EquipmentSlot.HEAD, new Item.Settings().fireproof()));
    public static final Item DRAGON_CHESTPLATE = register("dragon_chestplate", new DragonArmor(DRAGON_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().fireproof()));
    public static final Item DRAGON_LEGGINGS = register("dragon_leggings", new DragonArmor(DRAGON_ARMOR_MATERIAL, EquipmentSlot.LEGS, new Item.Settings().fireproof()));
    public static final Item DRAGON_BOOTS = register("dragon_boots", new DragonArmor(DRAGON_ARMOR_MATERIAL, EquipmentSlot.FEET, new Item.Settings().fireproof()));
    public static final Item UPGRADED_DRAGON_CHESTPLATE = register("upgraded_dragon_chestplate", new DragonArmor(DRAGON_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().fireproof()));

    // Tools
    public static final Item DRAGON_PICKAXE_ITEM = register("dragon_pickaxe", new DragonPickaxeItem(DragonToolMaterial.getInstance(), 1, -2.8f, new Item.Settings().fireproof()));
    public static final Item DRAGON_AXE_ITEM = register("dragon_axe", new DragonAxeItem(DragonToolMaterial.getInstance(), 5, -3f, new Item.Settings().fireproof()));
    public static final Item DRAGON_SHOVEL_ITEM = register("dragon_shovel", new DragonShovelItem(DragonToolMaterial.getInstance(), 1.5f, -3f, new Item.Settings().fireproof()));
    public static final Item DRAGON_HOE_ITEM = register("dragon_hoe", new DragonHoeItem(DragonToolMaterial.getInstance(), -4, -2f, new Item.Settings().fireproof()));

    // Weapons
    public static final Item DRAGON_SWORD_ITEM = register("dragon_sword", new DragonSwordItem(DragonToolMaterial.getInstance(), 3, -2.4f, new Item.Settings().fireproof()));
    public static final Item DRAGON_BOW_ITEM = register("dragon_bow", new DragonBowItem(new Item.Settings().fireproof().maxDamage(DragonToolMaterial.getInstance().getDurability())));
    public static final Item DRAGON_CROSSBOW_ITEM = register("dragon_crossbow", new DragonCrossbowItem(new Item.Settings().fireproof().maxDamage(DragonToolMaterial.getInstance().getDurability())));
    public static final Item DRAGON_TRIDENT_ITEM = register("dragon_trident", new DragonTridentItem(new Item.Settings().maxDamage(DragonToolMaterial.getInstance().getDurability()).fireproof()));

    private static Item register(String id, Item item) {
        return register(DragonLootMain.ID(id), item);
    }

    private static Item register(Identifier id, Item item) {
        ItemGroupEvents.modifyEntriesEvent(DRAGON_ITEM_GROUP).register(entries -> entries.add(item));
        return Registry.register(Registries.ITEM, id, item);
    }

    public static void init() {
        CompatRecipes.loadRecipes();
        RecipeGenerator.addRecipes();
    }
}
