package net.dragonloot.init;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class TagInit {
    public static final TagKey<Item> NOT_DESTROYED_BY_EXPLOSION = TagKey.of(RegistryKeys.ITEM, new Identifier("dragonloot", "explosion_resistant"));

    public static void init() {

    }

}
