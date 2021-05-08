package net.dragonloot;

import net.dragonloot.init.ModelProviderInit;
import net.dragonloot.init.NetworkInit;
import net.dragonloot.init.RenderInit;
import net.fabricmc.api.ClientModInitializer;

public class DragonLootClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        RenderInit.init();
        ModelProviderInit.init();
        NetworkInit.init();
    }

}
