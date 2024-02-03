package com.bajookie.echoes_of_the_elders.item;

import com.bajookie.echoes_of_the_elders.EOTE;
import com.bajookie.echoes_of_the_elders.block.ModBlocks;
import com.bajookie.echoes_of_the_elders.system.ItemStack.Tier;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import static com.bajookie.echoes_of_the_elders.EOTE.MOD_ID;

@SuppressWarnings("unused")
public class ModItemGroups {
    public static final ItemGroup MOD_ITEM_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "mod_item_group"),
            FabricItemGroup.builder().displayName(Text.translatable("itemgroup.echoes_of_the_elders")).icon(() -> new ItemStack(ModBlocks.MINERS_FRUIT_BLOCK)).entries((displayContext, entries) -> {
                entries.add(ModItems.EXPLORER_FRUIT);
                // blocks
                // entries.add(ModBlocks.BEAR_TRAP_BLOCK);
                // flowers
                entries.add(ModBlocks.EXPLORER_FRUIT_BLOCK);
                entries.add(ModBlocks.MINERS_FRUIT_BLOCK);
                entries.add(ModBlocks.NETHER_FRUIT_BLOCK);
                entries.add(ModBlocks.ARTIFACT_VAULT);
                // entries.add(ModBlocks.SUN_RUNE_BLOCK);
                // entries.add(ModBlocks.CHISELED_MOSSY_STONE);
                // entries.add(ModBlocks.ELDER_LILY_FLOWER);
                // trees
                // entries.add(ModBlocks.ANCIENT_TREE_LEAVES);
                // entries.add(ModBlocks.ANCIENT_TREE_LOG);
                // entries.add(ModBlocks.ANCIENT_TREE_WOOD);
                // entries.add(ModBlocks.ANCIENT_TREE_PLANKS);
                // entries.add(ModBlocks.STRIPPED_ANCIENT_TREE_LOG);
                // entries.add(ModBlocks.STRIPPED_ANCIENT_TREE_WOOD);
                entries.add(ModBlocks.ANCIENT_TREE_SAPLING);
                entries.add(ModItems.OLD_KEY);
                entries.add(ModItems.CORRUPTED_KEY);
                entries.add(ModBlocks.SPIRITAL_GRASS);
                entries.add(ModItems.RAID_DEBUG_ITEM);
                entries.add(ModBlocks.SPIRIT_PETALS);
                entries.add(ModBlocks.WOOL_TENT_DOOR);
                entries.add(ModItems.SPIRIT_SPAWN_EGG);
                entries.add(ModItems.RAID_TOTEM_EGG);
                entries.add(ModItems.ELDERMAN_SPAWN_EGG);
                entries.add(ModItems.ZOMBEE_SPAWN_EGG);
                entries.add(Tier.set(new ItemStack(ModItems.OLD_KEY), 1), ItemGroup.StackVisibility.SEARCH_TAB_ONLY);
                entries.add(Tier.set(new ItemStack(ModItems.OLD_KEY), 5), ItemGroup.StackVisibility.SEARCH_TAB_ONLY);
                entries.add(Tier.set(new ItemStack(ModItems.OLD_KEY), 10), ItemGroup.StackVisibility.SEARCH_TAB_ONLY);
                entries.add(Tier.set(new ItemStack(ModItems.OLD_KEY), 28), ItemGroup.StackVisibility.SEARCH_TAB_ONLY);
                entries.add(Tier.set(new ItemStack(ModItems.OLD_KEY), 100), ItemGroup.StackVisibility.SEARCH_TAB_ONLY);
            }).build());

    public static final ItemGroup MOD_ARTIFACT_GROUP = Registry.register(Registries.ITEM_GROUP, new Identifier(MOD_ID, "mod_artifact_group"),
            FabricItemGroup.builder().displayName(Text.translatable("artifact-group.echoes_of_the_elders")).icon(() -> new ItemStack(ModItems.VITALITY_PUMP)).entries((displayContext, entries) -> {
                entries.add(ModItems.RADIANT_LOTUS);
                entries.add(ModItems.VITALITY_PUMP);
                entries.add(ModItems.PORTAL_RING);
                entries.add(ModItems.MIDAS_HAMMER);
                entries.add(ModItems.GALE_CORE);
                entries.add(ModItems.SCORCHERS_MITTS);
                entries.add(ModItems.DOOMSTICK_ITEM);
                entries.add(ModItems.POTION_MIRAGE);
                entries.add(ModItems.WITHER_SCALES_ITEM);
                entries.add(ModItems.QUICKENING_BAND);
                // entries.add(ModItems.SECOND_SUN_ITEM);
                entries.add(ModItems.CHAIN_LIGHTNING_ITEM);
                entries.add(ModItems.REALITY_PICK);
                entries.add(ModItems.GODSLAYER);
                entries.add(ModItems.GUNHEELS);
                entries.add(ModItems.ELDER_PRISM);
                entries.add(ModItems.STAT_FRUIT_HP);
                entries.add(ModItems.TIME_TOKEN);
                entries.add(ModItems.WTF_RELIC);
                entries.add(ModItems.ARC_LIGHTNING);
                entries.add(ModItems.AIR_SWEEPER);
                entries.add(ModItems.VACUUM_RELIC);
                entries.add(ModItems.MOLTEN_CHAMBER);
                entries.add(ModItems.ECHOING_SWORD);
                entries.add(ModItems.TV_ARROW);
                entries.add(ModItems.STARFALL_BOW);
                entries.add(ModItems.EARTH_SPIKE_RELIC);
                entries.add(ModItems.ICICLE_RELIC);
                // disabled until we think this one out
                // entries.add(ModItems.SILENT_FIRE);
            }).build());

    public static void registerGroups() {
        EOTE.LOGGER.info("Registering Item Groups for ---> " + MOD_ID);
    }
}
