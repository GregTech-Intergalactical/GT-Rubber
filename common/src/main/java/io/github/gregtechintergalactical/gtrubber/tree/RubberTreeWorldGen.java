package io.github.gregtechintergalactical.gtrubber.tree;

import io.github.gregtechintergalactical.gtrubber.GTRubber;
import io.github.gregtechintergalactical.gtrubber.GTRubberData;
import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.objects.ObjectOpenHashSet;
import muramasa.antimatter.mixin.BiomeAccessor;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.TagUtils;
import muramasa.antimatter.worldgen.AntimatterWorldGenerator;
import muramasa.antimatter.worldgen.object.WorldGenBase;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.LeaveVineDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementContext;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;
import net.minecraft.world.level.levelgen.placement.PlacementModifierType;
import net.minecraft.world.level.levelgen.placement.SurfaceWaterDepthFilter;

import java.util.Random;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class RubberTreeWorldGen extends WorldGenBase<RubberTreeWorldGen> {

    public static final RubberTreeWorldGen INSTANCE = new RubberTreeWorldGen();
    public static Holder<PlacedFeature> TREE;
    public static Holder<PlacedFeature> TREE_SWAMP;
    public static Holder<PlacedFeature> TREE_JUNGLE;
    public static Holder<ConfiguredFeature<TreeConfiguration, ?>> TREE_FEATURE_CONFIG;
    public static Holder<ConfiguredFeature<TreeConfiguration, ?>> TREE_FEATURE_SWAMP_CONFIG;
    public static Holder<ConfiguredFeature<TreeConfiguration, ?>> TREE_FEATURE_JUNGLE_CONFIG;

    /*@Override
    public Predicate<Biome> getValidBiomes() {
        return getValidBiomesStatic();
    }*/

    public static Predicate<Biome.BiomeCategory> getValidBiomesStatic() {
        final Set<Biome.BiomeCategory> blacklist = new ObjectOpenHashSet<>();
        blacklist.add(Biome.BiomeCategory.DESERT);
        blacklist.add(Biome.BiomeCategory.TAIGA);
        blacklist.add(Biome.BiomeCategory.EXTREME_HILLS);
        blacklist.add(Biome.BiomeCategory.ICY);
        blacklist.add(Biome.BiomeCategory.THEEND);
        blacklist.add(Biome.BiomeCategory.OCEAN);
        blacklist.add(Biome.BiomeCategory.NETHER);
        blacklist.add(Biome.BiomeCategory.PLAINS);
        return b -> !blacklist.contains(b);
    }

    
    final static TreeConfiguration RUBBER_TREE_CONFIG_SWAMP =
            (new TreeConfiguration.TreeConfigurationBuilder(RubberTree.TRUNK_BLOCKS, new StraightTrunkPlacer(5, 2, 2), BlockStateProvider.simple(GTRubberData.RUBBER_LEAVES.defaultBlockState()),
                    new RubberFoliagePlacer(),  new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().decorators(ImmutableList.of(new LeaveVineDecorator())).build();

    final static TreeConfiguration RUBBER_TREE_CONFIG_JUNGLE =
            (new TreeConfiguration.TreeConfigurationBuilder(RubberTree.TRUNK_BLOCKS, new StraightTrunkPlacer(7, 2, 2), BlockStateProvider.simple(GTRubberData.RUBBER_LEAVES.defaultBlockState()),
                    new RubberFoliagePlacer(),  new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().decorators(ImmutableList.of(new LeaveVineDecorator())).build();

    final static TreeConfiguration RUBBER_TREE_CONFIG_NORMAL =
            (new TreeConfiguration.TreeConfigurationBuilder(RubberTree.TRUNK_BLOCKS, new StraightTrunkPlacer(5, 2, 2),BlockStateProvider.simple(GTRubberData.RUBBER_LEAVES.defaultBlockState()),
                    new RubberFoliagePlacer(),  new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build();

    public RubberTreeWorldGen(){
        super("rubber_tree", RubberTreeWorldGen.class, Level.OVERWORLD);
        AntimatterWorldGenerator.register(this.toRegister, this);
    }

    public static final PlacementModifierType<RubberTreePlacementModifier> RUBBER_TREE_PLACEMENT_MODIFIER  = () -> RubberTreePlacementModifier.CODEC;

    public static void init(){
        TREE_FEATURE_CONFIG = FeatureUtils.register("gtrubber:rubber_tree_normal", RubberTree.TREE_FEATURE, RubberTreeWorldGen.RUBBER_TREE_CONFIG_NORMAL);
        TREE_FEATURE_SWAMP_CONFIG = FeatureUtils.register("gtrubber:rubber_tree_jungle", RubberTree.TREE_FEATURE, RubberTreeWorldGen.RUBBER_TREE_CONFIG_JUNGLE);
        TREE_FEATURE_JUNGLE_CONFIG = FeatureUtils.register("gtrubber:rubber_tree_swamp", RubberTree.TREE_FEATURE, RubberTreeWorldGen.RUBBER_TREE_CONFIG_SWAMP);
        TREE = PlacementUtils.register("gtrubber:rubber", RubberTreeWorldGen.TREE_FEATURE_CONFIG, new RubberTreePlacementModifier(), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(GTRubberData.RUBBER_SAPLING.defaultBlockState(), BlockPos.ZERO)));
        TREE_JUNGLE = PlacementUtils.register("gtrubber:rubber_jungle", RubberTreeWorldGen.TREE_FEATURE_JUNGLE_CONFIG, new RubberTreePlacementModifier(), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(0), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(GTRubberData.RUBBER_SAPLING.defaultBlockState(), BlockPos.ZERO)));
        TREE_SWAMP = PlacementUtils.register("gtrubber:rubber_swamp", RubberTreeWorldGen.TREE_FEATURE_SWAMP_CONFIG, new RubberTreePlacementModifier(), InSquarePlacement.spread(), SurfaceWaterDepthFilter.forMaxDepth(2), PlacementUtils.HEIGHTMAP_OCEAN_FLOOR, BiomeFilter.biome(), BlockPredicateFilter.forPredicate(BlockPredicate.wouldSurvive(GTRubberData.RUBBER_SAPLING.defaultBlockState(), BlockPos.ZERO)));
        if (AntimatterPlatformUtils.isFabric()){
            Registry.register(Registry.FOLIAGE_PLACER_TYPES, new ResourceLocation(GTRubber.ID, "rubber_foilage_placer"), RubberFoliagePlacer.RUBBER);
        }
        Registry.register(Registry.PLACEMENT_MODIFIERS, new ResourceLocation(GTRubber.ID, "rubber_tree_placement_modifier"), RUBBER_TREE_PLACEMENT_MODIFIER);
    }

    public static class RubberTreePlacementModifier extends PlacementModifier {
        public static final Codec<RubberTreePlacementModifier> CODEC = Codec.unit(RubberTreePlacementModifier::new);


        @Override
        public Stream<BlockPos> getPositions(PlacementContext context, Random random, BlockPos pos) {
            BiomeAccessor biome = ((BiomeAccessor)(Object)context.getLevel().getBiome(pos).value());
            if (context.getLevel().getBiome(pos).is(TagUtils.getBiomeTag(new ResourceLocation(GTRubber.ID, "is_invalid_rubber")))) return Stream.empty();
            float p = 0.15F;
            if (biome.getClimateSettings().temperature > 0.8f) {
                p = 0.04F;
                if (biome.getClimateSettings().precipitation == Biome.Precipitation.RAIN)
                    p += 0.04F;
            }
            float finalp = p;
            int i = 0;
            if (random.nextDouble() < finalp) {
                i = random.nextInt(1) + 1;
            }
            return IntStream.range(0, i).mapToObj((ix) -> {
                int j = random.nextInt(16) + pos.getX();
                int k = random.nextInt(16) + pos.getZ();
                return new BlockPos(j, context.getHeight(Heightmap.Types.MOTION_BLOCKING, j, k), k);
            });
        }

        @Override
        public PlacementModifierType<?> type() {
            return RUBBER_TREE_PLACEMENT_MODIFIER;
        }
    }
}
