package net.dragonloot.compat.recipes;

import net.minecraft.util.Identifier;

public class RecipeMaterial {

    public Identifier baseItem;
    public Identifier additionItem;
    public String baseType;
    public String additionType;
    public Identifier output;
    public Identifier template;

    public RecipeMaterial(Identifier baseItem, Identifier additionItem, String baseType, String additionType, Identifier output, Identifier template) {
        this.baseItem = baseItem;
        this.additionItem = additionItem;
        this.baseType = baseType;
        this.additionType = additionType;
        this.output = output;
        this.template = template;
    }

}
