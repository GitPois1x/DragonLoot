package net.dragonloot.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.dragonloot.DragonLootMain;
import net.dragonloot.init.ItemInit;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.client.render.entity.TridentEntityRenderer;
import net.minecraft.entity.projectile.TridentEntity;
import net.minecraft.util.Identifier;

@Mixin(TridentEntityRenderer.class)
public class TridentEntityRendererMixin {

    @Inject(method = "getTexture", at = @At(value = "HEAD"), cancellable = true)
    public void getTextureMixin(TridentEntity entity, CallbackInfoReturnable<Identifier> info) {
        if (FabricLoader.getInstance().isModLoaded("netherite_plus")
                && entity.tridentStack.getItem() == ItemInit.DRAGON_TRIDENT_ITEM) {
            info.setReturnValue(DragonLootMain.ID("textures/entity/dragon_trident.png"));
        }
    }
}
