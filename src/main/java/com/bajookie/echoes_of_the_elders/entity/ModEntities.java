package com.bajookie.echoes_of_the_elders.entity;

import com.bajookie.echoes_of_the_elders.entity.custom.FlowerDefenseEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import static com.bajookie.echoes_of_the_elders.EOTE.MOD_ID;

public class ModEntities {
    public static final EntityType<FlowerDefenseEntity> FLOWER_DEFENSE_ENTITY = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(MOD_ID,"flower_defense_entity"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE,FlowerDefenseEntity::new)
                    .dimensions(EntityDimensions.fixed(1f,1f)).build());

    /**
     * Register Mob Attributes here:
     */
    public static void registerMobAttributes(){
        FabricDefaultAttributeRegistry.register(ModEntities.FLOWER_DEFENSE_ENTITY,FlowerDefenseEntity.createFlowerDefenseAttributes());
    }
}