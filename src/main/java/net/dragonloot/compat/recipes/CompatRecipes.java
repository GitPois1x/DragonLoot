package net.dragonloot.compat.recipes;

import net.dragonloot.DragonLootMain;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Identifier;

public class CompatRecipes {
    public static void loadRecipes() {
        if (FabricLoader.getInstance().isModLoaded("netherite_plus")) {
            RecipeGenerator.SMITHING_RECIPES.put("dragon_horse_armor", new RecipeMaterial(new Identifier("netherite_plus", "netherite_horse_armor"), DragonLootMain.ID("dragon_scale"), "item", "item", DragonLootMain.ID("dragon_horse_armor")));
            RecipeGenerator.SMITHING_RECIPES.put("dragon_trident", new RecipeMaterial(new Identifier("netherite_plus", "netherite_trident"), DragonLootMain.ID("dragon_scale"), "item", "item", DragonLootMain.ID("dragon_trident")));
            RecipeGenerator.SMITHING_RECIPES.put("dragon_bow", new RecipeMaterial(new Identifier("netherite_plus", "netherite_bow"), DragonLootMain.ID("dragon_scale"), "item", "item", DragonLootMain.ID("dragon_bow")));
            RecipeGenerator.SMITHING_RECIPES.put("dragon_crossbow", new RecipeMaterial(new Identifier("netherite_plus", "netherite_crossbow"), DragonLootMain.ID("dragon_scale"), "item", "item", DragonLootMain.ID("dragon_crossbow")));
            RecipeGenerator.SMITHING_RECIPES.put("upgraded_dragon_chestplate", new RecipeMaterial(new Identifier("dragonloot", "dragon_chestplate"), new Identifier("netherite_plus", "netherite_elytra"), "item", "item", DragonLootMain.ID("upgraded_dragon_chestplate")));
        } else {
            RecipeGenerator.SMITHING_RECIPES.put("dragon_horse_armor", new RecipeMaterial(new Identifier("minecraft", "diamond_horse_armor"), DragonLootMain.ID("dragon_scale"), "item", "item", DragonLootMain.ID("dragon_horse_armor")));
            RecipeGenerator.SMITHING_RECIPES.put("dragon_trident", new RecipeMaterial(new Identifier("minecraft", "trident"), DragonLootMain.ID("dragon_scale"), "item", "item", DragonLootMain.ID("dragon_trident")));
            RecipeGenerator.SMITHING_RECIPES.put("dragon_bow", new RecipeMaterial(new Identifier("minecraft", "bow"), DragonLootMain.ID("dragon_scale"), "item", "item", DragonLootMain.ID("dragon_bow")));
            RecipeGenerator.SMITHING_RECIPES.put("dragon_crossbow", new RecipeMaterial(new Identifier("minecraft", "crossbow"), DragonLootMain.ID("dragon_scale"), "item", "item", DragonLootMain.ID("dragon_crossbow")));
            RecipeGenerator.SMITHING_RECIPES.put("upgraded_dragon_chestplate", new RecipeMaterial(new Identifier("dragonloot", "dragon_chestplate"), new Identifier("minecraft", "elytra"), "item", "item", DragonLootMain.ID("upgraded_dragon_chestplate")));

        }

    }
}

// {"type":"minecraft:smithing","base":{"item":"dragonloot:dragon_chestplate"},"addition":{"item":"minecraft:elytra"},"result":{"item":"dragonloot:upgraded_dragon_chestplate"}}
