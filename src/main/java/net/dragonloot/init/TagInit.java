package net.dragonloot.init;

import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.Item;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;

public class TagInit {
    public static final Tag<Item> NOT_DESTROYED_BY_EXPLOSION = TagRegistry
            .item(new Identifier("dragonloot", "explosion_resistant"));

    public static void init() {

    }

}
