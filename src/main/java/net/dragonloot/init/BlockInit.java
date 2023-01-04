package net.dragonloot.init;

import net.dragonloot.block.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class BlockInit {

    public static final DragonAnvilBlock DRAGON_ANVIL_BLOCK = new DragonAnvilBlock(FabricBlockSettings.copy(Blocks.ANVIL));

    public static void init() {
        Registry.register(Registries.ITEM, new Identifier("dragonloot", "dragon_anvil"), new BlockItem(DRAGON_ANVIL_BLOCK, new Item.Settings()));
        Registry.register(Registries.BLOCK, new Identifier("dragonloot", "dragon_anvil"), DRAGON_ANVIL_BLOCK);
    }

}
