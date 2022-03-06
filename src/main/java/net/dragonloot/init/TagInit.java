package net.dragonloot.init;

import net.minecraft.item.Item;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class TagInit {
    public static final TagKey<Item> NOT_DESTROYED_BY_EXPLOSION = TagKey.of(Registry.ITEM_KEY, new Identifier("dragonloot", "explosion_resistant"));

    public static void init() {

    }

}
