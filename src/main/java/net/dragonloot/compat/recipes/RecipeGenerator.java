package net.dragonloot.compat.recipes;

import java.util.HashMap;

import com.google.gson.JsonObject;

import net.dragonloot.DragonLootMain;
import net.minecraft.util.Identifier;

public class RecipeGenerator {

    public static HashMap<String, RecipeMaterial> SMITHING_RECIPES = new HashMap<>();
    public static HashMap<Identifier, JsonObject> RECIPES = new HashMap<>();

    public static JsonObject generateJson(Identifier base, Identifier addition, String baseType, String additionType, Identifier output) {
        JsonObject json = new JsonObject();
        json.addProperty("type", "minecraft:smithing");

        JsonObject obj = new JsonObject();
        obj.addProperty(baseType, base.toString());
        json.add("base", obj);

        obj = new JsonObject();
        obj.addProperty(additionType, addition.toString());
        json.add("addition", obj);

        obj = new JsonObject();
        obj.addProperty("item", output.toString());
        json.add("result", obj);

        return json;
    }

    public static void addRecipes() {
        for (String key : SMITHING_RECIPES.keySet()) {
            RecipeMaterial material = SMITHING_RECIPES.get(key);
            RECIPES.put(DragonLootMain.ID(key), generateJson(material.baseItem, material.additionItem, material.baseType, material.additionType, material.output));
        }
    }
}
