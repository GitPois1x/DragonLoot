package net.dragonloot.init;

import net.dragonloot.network.EntitySpawnPacket;
import net.dragonloot.network.SyncPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;

public class NetworkInit {

    public static void init() {
        SyncPacket.init();
        ClientPlayNetworking.registerGlobalReceiver(EntitySpawnPacket.ID, EntitySpawnPacket::onPacket);
    }

}
