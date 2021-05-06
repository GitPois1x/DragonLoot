package net.dragonloot.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.netty.buffer.Unpooled;
import me.shedaniel.architectury.networking.NetworkManager;
import net.dragonloot.DragonLootMain;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.registry.Registry;

@Mixin(PersistentProjectileEntity.class)
public class PersistentProjectileEntityMixin {

    @Inject(method = "createSpawnPacket", at = @At("HEAD"))
    public void createSpawnPacketMixin(CallbackInfoReturnable<Packet<?>> info) {
        PersistentProjectileEntity persistentProjectileEntity = (PersistentProjectileEntity) (Object) this;
        if (persistentProjectileEntity instanceof TridentEntity) {
            PacketByteBuf passedData = new PacketByteBuf(Unpooled.buffer());
            passedData.writeInt(
                    Registry.ITEM.getRawId(((TridentEntity) persistentProjectileEntity).tridentStack.getItem()));
            NetworkManager.sendToPlayers(
                    persistentProjectileEntity.world.getServer().getPlayerManager().getPlayerList(),
                    DragonLootMain.ID("dragon_trident"), passedData);
        }
    }
}
