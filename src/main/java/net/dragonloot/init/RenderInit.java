package net.dragonloot.init;

import net.dragonloot.entity.model.DragonElytraEntityModel;
import net.dragonloot.entity.render.DragonTridentEntityRenderer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class RenderInit {
    public static final EntityModelLayer DRAGON_ELYTRA_LAYER = new EntityModelLayer(new Identifier("dragonloot:dragon_elytra_render_layer"), "dragon_elytra_render_layer");

    public static void init() {
        EntityRendererRegistry.INSTANCE.register(EntityInit.DRAGONTRIDENT_ENTITY, DragonTridentEntityRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(DRAGON_ELYTRA_LAYER, DragonElytraEntityModel::getTexturedModelData);
    }
}