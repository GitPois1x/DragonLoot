package net.dragonloot.init;

import net.dragonloot.entity.DragonTridentEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityInit {

    public static final EntityType<DragonTridentEntity> DRAGONTRIDENT_ENTITY = FabricEntityTypeBuilder.<DragonTridentEntity>create(SpawnGroup.MISC, DragonTridentEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build();

    public static void init() {
        Registry.register(Registry.ENTITY_TYPE, new Identifier("dragonloot", "dragon_trident"), DRAGONTRIDENT_ENTITY);
    }

}
