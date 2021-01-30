package net.dragonloot.init;

import net.dragonloot.DragonLootMain;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ItemGroupInit {

    private ItemGroupInit() {}

    public static final ItemGroup DRAGON_ITEM_GROUP = FabricItemGroupBuilder.create(DragonLootMain.ID("dragonloot")).icon(() -> new ItemStack(ItemInit.DRAGON_SCALE_ITEM)).build();

}
