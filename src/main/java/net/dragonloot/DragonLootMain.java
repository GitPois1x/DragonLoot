package net.dragonloot;

import net.dragonloot.init.*;
import net.fabricmc.api.ModInitializer;

public class DragonLootMain implements ModInitializer {

    @Override
    public void onInitialize() {
        BlockInit.init();
        ItemInit.init();
        TagInit.init();
    }

}
