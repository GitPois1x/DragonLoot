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
import net.dragonloot.network.SyncPacket;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.network.ServerSidePacketRegistry;
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

@Mixin(AnvilScreenHandler.class)
public abstract class AnvilScreenHandlerMixin extends ForgingScreenHandler implements DragonAnvilInterface {
  @Shadow
  private final Property levelCost;
  public boolean isDragonAnvil = false;

  public AnvilScreenHandlerMixin(ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory,
      ScreenHandlerContext context) {
    super(type, syncId, playerInventory, context);
    this.levelCost = Property.create();
  }

  @Inject(method = "canUse", at = @At(value = "HEAD"))
  public void canUseMixin(BlockState state, CallbackInfoReturnable<Boolean> info) {
    if (state.isOf(BlockInit.DRAGON_ANVIL_BLOCK)) {
      isDragonAnvil = true;
      PacketByteBuf data = new PacketByteBuf(Unpooled.buffer());
      data.writeInt(player.getEntityId());
      data.writeString(state.getBlock().toString());
      ServerSidePacketRegistry.INSTANCE.sendToPlayer(player, SyncPacket.ANVIL_SYNC_PACKET, data);
    }
  }

  @Inject(method = "Lnet/minecraft/screen/AnvilScreenHandler;onTakeOutput(Lnet/minecraft/entity/player/PlayerEntity;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/Property;set(I)V"), cancellable = true)
  public void onTakeOutputMixin(PlayerEntity player, ItemStack stack, CallbackInfoReturnable<ItemStack> info) {
    if (isDragonAnvil == true) {
      this.context.run((world, blockPos) -> {
        this.levelCost.set(0);
        world.syncWorldEvent(1030, blockPos, 0);
        info.setReturnValue(stack);
      });
    }
  }

  @Inject(method = "Lnet/minecraft/screen/AnvilScreenHandler;updateResult()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/screen/Property;set(I)V", shift = At.Shift.AFTER))
  public void updateResultMixin(CallbackInfo info) {
    if (this.levelCost.get() > 30 && isDragonAnvil == true) {
      this.levelCost.set(30);
    }
  }

  @Inject(method = "getLevelCost", at = @At(value = "HEAD"))
  @Environment(EnvType.CLIENT)
  public void getLevelCostMixin(CallbackInfoReturnable<Integer> info) {
    if (this.levelCost.get() > 30 && isDragonAnvil == true) {
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
