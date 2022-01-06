package net.dragonloot.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;

import net.dragonloot.init.ItemInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {
    @Shadow
    protected int roll;

    public LivingEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "tickFallFlying", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;setFlag(IZ)V", ordinal = 0), cancellable = true)
    private void tickFallFlyingMixin(CallbackInfo info) {
        boolean bl = this.getFlag(7);
        if (bl && !this.onGround && !this.hasVehicle() && ((LivingEntity) (Object) this).getEquippedStack(EquipmentSlot.CHEST).getItem() == ItemInit.UPGRADED_DRAGON_CHESTPLATE) {
            this.setFlag(7, true);
            info.cancel();
        }

    }

}