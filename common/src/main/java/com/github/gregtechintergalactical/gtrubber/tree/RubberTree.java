package com.github.gregtechintergalactical.gtrubber.tree;

import com.github.gregtechintergalactical.gtrubber.GTRubber;
import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.worldgen.feature.IAntimatterFeature;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;

import java.util.Random;

public class RubberTree extends AbstractTreeGrower {

    public static final TreeFeature TREE_FEATURE = (TreeFeature) AntimatterAPI.register(IAntimatterFeature.class, new RubberTreeFeature()).asFeature();
    public static final WeightedStateProvider TRUNK_BLOCKS;
    public static Holder<PlacedFeature> TREE;
    public static Holder<PlacedFeature> TREE_SWAMP;
    public static Holder<PlacedFeature> TREE_JUNGLE;

    public static Holder<ConfiguredFeature<TreeConfiguration, ?>> TREE_FEATURE_CONFIG;
    public static Holder<ConfiguredFeature<TreeConfiguration, ?>> TREE_FEATURE_SWAMP_CONFIG;
    public static Holder<ConfiguredFeature<TreeConfiguration, ?>> TREE_FEATURE_JUNGLE_CONFIG;
    static {
        SimpleWeightedRandomList.Builder<BlockState> st = SimpleWeightedRandomList.<BlockState>builder();
        BlockStateProperties.HORIZONTAL_FACING.getPossibleValues().forEach(d -> {
            st.add(GTRubberData.RUBBER_LOG.defaultBlockState()
                    .setValue(ResinState.INSTANCE, ResinState.FILLED)
                    .setValue(BlockRubberLog.RESIN_FACING, d), 1);
            st.add(GTRubberData.RUBBER_LOG.defaultBlockState()
                    .setValue(ResinState.INSTANCE, ResinState.EMPTY)
                    .setValue(BlockRubberLog.RESIN_FACING, d), 1);
        });
        st.add(GTRubberData.RUBBER_LOG.defaultBlockState()
                .setValue(ResinState.INSTANCE, ResinState.NONE), 20);
        TRUNK_BLOCKS = new WeightedStateProvider(st.build());
}

    public static void init() {


        TREE_FEATURE_CONFIG = FeatureUtils.register("gtrubber:rubber_tree_normal", TREE_FEATURE, RubberTreeWorldGen.RUBBER_TREE_CONFIG_NORMAL);
        TREE_FEATURE_SWAMP_CONFIG = FeatureUtils.register("gtrubber:rubber_tree_jungle", TREE_FEATURE, RubberTreeWorldGen.RUBBER_TREE_CONFIG_JUNGLE);
        TREE_FEATURE_JUNGLE_CONFIG = FeatureUtils.register("gtrubber:rubber_tree_swamp", TREE_FEATURE, RubberTreeWorldGen.RUBBER_TREE_CONFIG_SWAMP);
        TREE = PlacementUtils.register("gtrubber:rubber",TREE_FEATURE_CONFIG, PlacementUtils.countExtra(2, 0.1F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(GTRubberData.RUBBER_SAPLING.defaultBlockState(), BlockPos.ZERO)));
        TREE_JUNGLE = PlacementUtils.register("gtrubber:rubber_jungle", TREE_FEATURE_JUNGLE_CONFIG, PlacementUtils.countExtra(5, 0.1F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(GTRubberData.RUBBER_SAPLING.defaultBlockState(), BlockPos.ZERO)));
        TREE_SWAMP = PlacementUtils.register("gtrubber:rubber_swamp", TREE_FEATURE_SWAMP_CONFIG, PlacementUtils.countExtra(2, 0.1F, 1), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(2), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(GTRubberData.RUBBER_SAPLING.defaultBlockState(), BlockPos.ZERO)));
        if (AntimatterPlatformUtils.isFabric()){
            Registry.register(Registry.FOLIAGE_PLACER_TYPES, new ResourceLocation(GTRubber.ID, "rubber_foilage_placer"), RubberFoliagePlacer.RUBBER);
        }
    }


    public RubberTree() {

    }


    @Override
    public boolean growTree(ServerLevel world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random rand) {
        Holder<Biome> biome = world.getBiome(pos);
        ConfiguredFeature<TreeConfiguration, ?> configuredFeature;
        if (biome.is(Biomes.JUNGLE)) {
            configuredFeature = TREE_FEATURE_JUNGLE_CONFIG.value();
        } else if (biome.is(Biomes.SWAMP)) {
            configuredFeature = TREE_FEATURE_SWAMP_CONFIG.value();
        } else {
            configuredFeature = TREE_FEATURE_CONFIG.value();
        }
        world.setBlock(pos, Blocks.AIR.defaultBlockState(), 4);
        if (!configuredFeature.place(world, chunkGenerator, rand, pos)) {
            world.setBlock(pos, state, 4);
            return false;
        } else {
            return true;
        }
    }

    @Override
    protected Holder<? extends ConfiguredFeature<?, ?>> getConfiguredFeature(Random rand,
            boolean b) {
        return TREE_FEATURE_CONFIG;
    }
}
