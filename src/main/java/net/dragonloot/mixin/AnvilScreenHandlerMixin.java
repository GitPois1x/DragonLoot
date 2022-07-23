package net.dragonloot.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import io.netty.buffer.Unpooled;

import org.spongepowered.asm.mixin.injection.At;

import net.dragonloot.access.DragonAnvilInterface;
import net.dragonloot.init.BlockInit;
import net.dragonloot.init.ConfigInit;
import net.dragonloot.network.SyncPacket;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.screen.AnvilScreenHandler;
import net.minecraft.screen.ForgingScreenHandler;
import net.minecraft.screen.Property;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.server.network.ServerPlayerEntity;

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler implements DragonAnvilInterface {
    @Shadow
    private final Property levelCost;
    public boolean isDragonAnvil = false;

    public AnvilScreenHandlerMixin(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory, ScreenHandlerContext context) {
        super(type, syncId, playerInventory, context);
        this.levelCost = Property.create();
    }

    @Inject(method = "canUse", at = @At(value = "HEAD"))
    private void canUseMixin(BlockState state, CallbackInfoReturnable<Boolean> info) {
        if (state.isOf(BlockInit.DRAGON_ANVIL_BLOCK)) {
            isDragonAnvil = true;
            PacketByteBuf data = new PacketByteBuf(Unpooled.buffer());
            data.writeInt(player.getId());
            data.writeString(state.getBlock().toString());
            ServerPlayNetworking.send((ServerPlayerEntity) player, SyncPacket.ANVIL_SYNC_PACKET, data);
        }
    }

    @Inject(method = "onTakeOutput", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/Property;set(I)V"), cancellable = true)
    private void onTakeOutputMixin(PlayerEntity player, ItemStack stack, CallbackInfo info) {
        if (isDragonAnvil == true) {
            this.context.run((world, blockPos) -> {
                this.levelCost.set(0);
                world.syncWorldEvent(1030, blockPos, 0);
                info.cancel();
            });
        }
    }

    @Inject(method = "Lnet/minecraft/screen/AnvilScreenHandler;updateResult()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/Property;set(I)V", shift = At.Shift.AFTER))
    private void updateResultMixin(CallbackInfo info) {
        if (this.levelCost.get() > 30 && isDragonAnvil == true && ConfigInit.CONFIG.dragon_anvil_no_cap) {
            this.levelCost.set(30);
        }
    }

    @Inject(method = "getLevelCost", at = @At(value = "HEAD"), cancellable = true)
    private void getLevelCostMixin(CallbackInfoReturnable<Integer> info) {
        if (this.levelCost.get() > 30 && isDragonAnvil == true && ConfigInit.CONFIG.dragon_anvil_no_cap) {
            info.setReturnValue(30);
        }
    }

    @Override
    public void setDragonAnvil(String string) {
        if (string.equals(BlockInit.DRAGON_ANVIL_BLOCK.toString())) {
            isDragonAnvil = true;
        }
    }

}
