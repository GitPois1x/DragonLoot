package net.dragonloot.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.dragonloot.init.TagInit;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.World;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin extends Entity {

    public ItemEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    public void damageMixin(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
        if ((Object) this instanceof ItemEntity) {
            ItemEntity itemStack = (ItemEntity) (Object) this;
            if (itemStack.getStack().getItem().isIn(TagInit.NOT_DESTROYED_BY_EXPLOSION) && source.isExplosive()) {
                info.setReturnValue(false);
            }
        }
    }
}
