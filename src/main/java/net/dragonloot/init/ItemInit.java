package net.dragonloot.init;

import net.dragonloot.DragonLootMain;
import net.dragonloot.compat.recipes.CompatRecipes;
import net.dragonloot.compat.recipes.RecipeGenerator;
import net.dragonloot.item.*;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ItemInit {

    public static final RegistryKey<ItemGroup> DRAGON_ITEM_GROUP = RegistryKey.of(RegistryKeys.ITEM_GROUP, DragonLootMain.ID("dragonloot"));

    // Items
    public static final Item DRAGON_SCALE_ITEM = register("dragon_scale", new DragonScaleItem(new Item.Settings().fireproof()));
    public static final Item DRAGON_HORSE_ARMOR_ITEM = register("dragon_horse_armor", new HorseArmorItem(18, "dragon", new Item.Settings().maxCount(1).fireproof()));
    // Armor
    public static final ArmorMaterial DRAGON_ARMOR_MATERIAL = DragonArmorMaterial.getInstance();

    public static final Item DRAGON_HELMET = register("dragon_helmet", new DragonArmor(DRAGON_ARMOR_MATERIAL, ArmorItem.Type.HELMET, new Item.Settings().fireproof()));
    public static final Item DRAGON_CHESTPLATE = register("dragon_chestplate", new DragonArmor(DRAGON_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings().fireproof()));
    public static final Item DRAGON_LEGGINGS = register("dragon_leggings", new DragonArmor(DRAGON_ARMOR_MATERIAL, ArmorItem.Type.LEGGINGS, new Item.Settings().fireproof()));
    public static final Item DRAGON_BOOTS = register("dragon_boots", new DragonArmor(DRAGON_ARMOR_MATERIAL, ArmorItem.Type.BOOTS, new Item.Settings().fireproof()));
    public static final Item UPGRADED_DRAGON_CHESTPLATE = register("upgraded_dragon_chestplate", new DragonArmor(DRAGON_ARMOR_MATERIAL, ArmorItem.Type.CHESTPLATE, new Item.Settings().fireproof()));

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
        Registry.register(Registries.ITEM_GROUP, DRAGON_ITEM_GROUP, FabricItemGroup.builder().entries((context, entries) -> entries.add(BlockInit.DRAGON_ANVIL_BLOCK)).icon(() -> new ItemStack(ItemInit.DRAGON_SCALE_ITEM)).displayName(Text.translatable("itemGroup.dragonloot.dragonloot")).build());

        CompatRecipes.loadRecipes();
        RecipeGenerator.addRecipes();
    }
}
