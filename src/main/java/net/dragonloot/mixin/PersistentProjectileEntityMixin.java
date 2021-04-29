package net.dragonloot.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.dragonloot.compat.netheriteplus.NetheritePlusCompat;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.projectile.PersistentProjectileEntity;
import net.minecraft.network.Packet;

@Mixin(PersistentProjectileEntity.class)
public class PersistentProjectileEntityMixin {

    @Inject(method = "createSpawnPacket", at = @At("HEAD"))
    public void createSpawnPacketMixin(CallbackInfoReturnable<Packet<?>> info) {
        if (FabricLoader.getInstance().isModLoaded("netherite_plus")) {
            NetheritePlusCompat.createSpawnPacket((PersistentProjectileEntity) (Object) this);
        }
    }
}
