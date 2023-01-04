package net.dragonloot.init;

import net.dragonloot.entity.DragonTridentEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class EntityInit {

    public static final EntityType<DragonTridentEntity> DRAGONTRIDENT_ENTITY = FabricEntityTypeBuilder.<DragonTridentEntity>create(SpawnGroup.MISC, DragonTridentEntity::new).dimensions(EntityDimensions.fixed(0.5F, 0.5F)).build();

    public static void init() {
        Registry.register(Registries.ENTITY_TYPE, new Identifier("dragonloot", "dragon_trident"), DRAGONTRIDENT_ENTITY);
    }

}
