package net.dragonloot.network;

import net.dragonloot.access.DragonAnvilInterface;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.util.Identifier;

public class SyncPacket {
    public static final Identifier ANVIL_SYNC_PACKET = new Identifier("dragonloot", "dragon_anvil_sync");

    public static void init() {
        ClientPlayNetworking.registerGlobalReceiver(ANVIL_SYNC_PACKET, (client, handler, buffer, responseSender) -> {
            int entityId = buffer.readInt();
            String blockString = buffer.readString();
            client.execute(() -> {
                if (client.player.world.getEntityById(entityId) != null) {
                    PlayerEntity player = (PlayerEntity) client.player.world.getEntityById(entityId);
                    if (player.currentScreenHandler instanceof AnvilScreenHandler) {
                        ((DragonAnvilInterface) player.currentScreenHandler).setDragonAnvil(blockString);
                    }

                }
            });
        });

    }

}
