package net.dragonloot.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.At;

import net.dragonloot.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
  @Shadow
  protected int roll;

  public LivingEntityMixin(EntityType<?> type, World world) {
    super(type, world);
  }

  @ModifyArg(method = "Lnet/minecraft/entity/LivingEntity;initAi()V", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setFlag(IZ)V"), index = 1)
  private boolean initAiMixin(boolean value) {
    boolean bl = this.getFlag(7);
    LivingEntity livingEntity = (LivingEntity) (Object) this;
    ItemStack itemStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
    if (bl && !this.onGround && !this.hasVehicle() && itemStack.getItem() == ItemInit.DRAGON_CHESTPLATE) {
      return true;
    }
    return value;
  }

}