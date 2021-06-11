package net.dragonloot.init;

import net.dragonloot.block.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class BlockInit {

    public static final DragonAnvilBlock DRAGON_ANVIL_BLOCK = new DragonAnvilBlock(
            FabricBlockSettings.copy(Blocks.ANVIL));

    public static void init() {
        Registry.register(Registry.ITEM, new Identifier("dragonloot", "dragon_anvil"),
                new BlockItem(DRAGON_ANVIL_BLOCK, new Item.Settings().group(ItemGroupInit.DRAGON_ITEM_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier("dragonloot", "dragon_anvil"), DRAGON_ANVIL_BLOCK);

    }

}
