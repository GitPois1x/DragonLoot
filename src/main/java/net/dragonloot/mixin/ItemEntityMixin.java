package net.dragonloot.mixin;

import net.minecraft.registry.tag.DamageTypeTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.dragonloot.init.TagInit;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.damage.DamageSource;

@Mixin(ItemEntity.class)
public abstract class ItemEntityMixin {

    @Inject(method = "damage", at = @At("HEAD"), cancellable = true)
    private void damageMixin(DamageSource source, float amount, CallbackInfoReturnable<Boolean> info) {
        if ((Object) this instanceof ItemEntity) {
            if (((ItemEntity) (Object) this).getStack().isIn(TagInit.NOT_DESTROYED_BY_EXPLOSION)
                    && source.isIn(DamageTypeTags.IS_EXPLOSION)) {
                info.setReturnValue(false);
            }
        }
    }
}
