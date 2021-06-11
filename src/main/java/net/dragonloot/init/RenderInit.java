package net.dragonloot.init;

import net.dragonloot.entity.render.DragonTridentEntityRenderer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

public class RenderInit {

    public static void init() {
        EntityRendererRegistry.INSTANCE.register(EntityInit.DRAGONTRIDENT_ENTITY, DragonTridentEntityRenderer::new);
    }

}