package net.dragonloot.compat.netheriteplus;

import net.dragonloot.DragonLootMain;
import net.dragonloot.compat.recipes.RecipeGenerator;
import net.dragonloot.compat.recipes.RecipeMaterial;
import net.dragonloot.init.ItemGroupInit;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.item.HorseArmorItem;

public class NetheritePlusCompat {

        public static final HorseArmorItem DRAGON_HORSE_ARMOR_ITEM = new HorseArmorItem(18, "dragon",
                        new Item.Settings().fireproof().group(ItemGroupInit.DRAGON_ITEM_GROUP));
        public static final DragonTridentItem DRAGON_TRIDENT_ITEM = new DragonTridentItem(
                        new Item.Settings().fireproof().group(ItemGroupInit.DRAGON_ITEM_GROUP));

        public static void loadItems() {
                Registry.register(Registry.ITEM, DragonLootMain.ID("dragon_horse_armor"), DRAGON_HORSE_ARMOR_ITEM);
                Registry.register(Registry.ITEM, DragonLootMain.ID("dragon_trident"), DRAGON_TRIDENT_ITEM);
        }

        public static void loadRecipes() {
                RecipeGenerator.SMITHING_RECIPES.put("dragon_horse_armor",
                                new RecipeMaterial(new Identifier("netherite_plus", "netherite_horse_armor"),
                                                DragonLootMain.ID("dragon_scale"), "item", "item",
                                                DragonLootMain.ID("dragon_horse_armor")));
                RecipeGenerator.SMITHING_RECIPES.put("dragon_trident",
                                new RecipeMaterial(new Identifier("netherite_plus", "netherite_trident"),
                                                DragonLootMain.ID("dragon_scale"), "item", "item",
                                                DragonLootMain.ID("dragon_trident")));
        }

}
