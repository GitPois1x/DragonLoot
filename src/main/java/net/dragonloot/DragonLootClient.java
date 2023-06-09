package net.dragonloot;

import net.dragonloot.init.ModelProviderInit;
import net.dragonloot.init.NetworkInit;
import net.dragonloot.init.RenderInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

@Environment(EnvType.CLIENT)
public class DragonLootClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        RenderInit.init();
        ModelProviderInit.init();
        NetworkInit.init();
    }

}
