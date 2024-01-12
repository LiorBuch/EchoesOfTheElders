package com.bajookie.echoes_of_the_elders.world;

import com.bajookie.echoes_of_the_elders.EOTE;
import com.bajookie.echoes_of_the_elders.block.ModBlocks;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ModPlacedFeatures {
    public static final RegistryKey<PlacedFeature> RELIC_CONTAINER_PLACED_KEY = registerKey("relic_container_placed");
    public static final RegistryKey<PlacedFeature> EXPLORER_FRUIT_PLACED_KEY = registerKey("explorer_fruit");
    public static final RegistryKey<PlacedFeature> MINERS_SHROOM_PLACED_KEY = registerKey("miners_shroom");
    public static final RegistryKey<PlacedFeature> ANCIENT_TREE_PLACED_KEY = registerKey("ancient_tree_place");
    public static final RegistryKey<PlacedFeature> SAKURA_TREE_PLACED_KEY = registerKey("sakura_tree_place");
    public static final RegistryKey<PlacedFeature> SPIRITAL_GRASS_PLACED_KEY = registerKey("spirital_grass_place");
    public static final RegistryKey<PlacedFeature> NETHER_FRUIT_PLACED_KEY = registerKey("nether_fruit_place");
    public static final RegistryKey<PlacedFeature> LESS_CHERRY_FLOWER_PLACED_KEY = registerKey("less_cherry_place");

    public static void bootstrap(Registerable<PlacedFeature> context){
        var configuredFeatureRegistryEntryLookup = context.getRegistryLookup(RegistryKeys.CONFIGURED_FEATURE);
        register(context,RELIC_CONTAINER_PLACED_KEY,configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.RELIC_CONTAINER_KEY),
                ModOrePlacement.modifiersWithCount(12, //veins per chunk
                        HeightRangePlacementModifier.uniform(YOffset.fixed(-80),YOffset.fixed(90)//where to place
                        )));
        register(context,EXPLORER_FRUIT_PLACED_KEY,configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.EXPLORERS_FRUIT_KEY),
                List.of(RarityFilterPlacementModifier.of(8) ,SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
        register(context,NETHER_FRUIT_PLACED_KEY,configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.NETHER_FRUIT_KEY),
                List.of(CountPlacementModifier.of(1), SquarePlacementModifier.of(), PlacedFeatures.BOTTOM_TO_TOP_RANGE, BiomePlacementModifier.of()));
        register(context,MINERS_SHROOM_PLACED_KEY,configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.MINERS_SHROOM_KEY),
                List.of(CountPlacementModifier.of(UniformIntProvider.create(10, 15)), PlacedFeatures.BOTTOM_TO_120_RANGE, SquarePlacementModifier.of(), SurfaceThresholdFilterPlacementModifier.of(Heightmap.Type.OCEAN_FLOOR_WG, Integer.MIN_VALUE, -13), BiomePlacementModifier.of()));
        register(context,ANCIENT_TREE_PLACED_KEY,configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.ANCIENT_TREE_KEY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(0,0.01f,1),
                        ModBlocks.ANCIENT_TREE_SAPLING));
        register(context,SAKURA_TREE_PLACED_KEY,configuredFeatureRegistryEntryLookup.getOrThrow(TreeConfiguredFeatures.CHERRY),
                VegetationPlacedFeatures.treeModifiersWithWouldSurvive(PlacedFeatures.createCountExtraModifier(1,0.1f,2),
                        Blocks.CHERRY_SAPLING));
        register(context,SPIRITAL_GRASS_PLACED_KEY,configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.SPIRITAL_GRASS_KEY),
                List.of(NoiseThresholdCountPlacementModifier.of(-0.8, 5, 10), SquarePlacementModifier.of(), PlacedFeatures.WORLD_SURFACE_WG_HEIGHTMAP, BiomePlacementModifier.of()));
        register(context,LESS_CHERRY_FLOWER_PLACED_KEY,configuredFeatureRegistryEntryLookup.getOrThrow(ModConfiguredFeatures.LESS_CHERRY_KEY),
                List.of(NoiseThresholdCountPlacementModifier.of(-0.8, 5, 10), SquarePlacementModifier.of(), PlacedFeatures.MOTION_BLOCKING_HEIGHTMAP, BiomePlacementModifier.of()));
    }
    public static RegistryKey<PlacedFeature> registerKey(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier(EOTE.MOD_ID, name));
    }

    private static void register(Registerable<PlacedFeature> context, RegistryKey<PlacedFeature> key, RegistryEntry<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
