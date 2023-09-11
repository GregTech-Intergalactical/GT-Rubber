package io.github.gregtechintergalactical.gtrubber;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import io.github.gregtechintergalactical.gtrubber.client.BakedModels;
import io.github.gregtechintergalactical.gtrubber.datagen.GTRubberBlockLootProvider;
import io.github.gregtechintergalactical.gtrubber.datagen.GTRubberBlockTagProvider;
import io.github.gregtechintergalactical.gtrubber.datagen.GTRubberItemTagProvider;
import io.github.gregtechintergalactical.gtrubber.datagen.GTRubberLanguageProvider;
import io.github.gregtechintergalactical.gtrubber.tree.RubberTree;
import io.github.gregtechintergalactical.gtrubber.tree.RubberTreeWorldGen;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.AntimatterDynamics;
import muramasa.antimatter.datagen.builder.AntimatterTagBuilder;
import muramasa.antimatter.datagen.providers.*;
import muramasa.antimatter.event.MaterialEvent;
import muramasa.antimatter.event.ProvidersEvent;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.registration.Side;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.block.state.properties.WoodType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static muramasa.antimatter.data.AntimatterMaterialTypes.PLATE;
import static muramasa.antimatter.data.AntimatterMaterialTypes.RING;

public class GTRubber extends AntimatterMod {

    public static final Logger LOGGER = LogManager.getLogger(); // Directly reference a log4j logger.
    public static final String ID = "gtrubber", NAME = "GT Rubber";

    @Override
    public void onRegistrarInit() {
        super.onRegistrarInit();
        AntimatterDynamics.clientProvider(ID, () -> new AntimatterBlockStateProvider(ID, NAME + " BlockStates"));
        AntimatterDynamics.clientProvider(ID, () -> new AntimatterItemModelProvider(ID, NAME + " Item Models"));
        AntimatterDynamics.clientProvider(ID, () -> new GTRubberLanguageProvider(ID, NAME + " en_us Localization", "en_us"));
    }

    public static void onProviders(ProvidersEvent ev) {
        final AntimatterBlockTagProvider[] p = new AntimatterBlockTagProvider[1];
        ev.addProvider(ID, () -> {
            p[0] = new GTRubberBlockTagProvider(ID, NAME.concat(" Block Tags"), false);
            return p[0];
        });

        ev.addProvider(ID, () -> new AntimatterTagProvider<>(BuiltinRegistries.BIOME, ID, NAME.concat(" Biome Tags"), "worldgen/biome") {
            @Override
            protected void processTags(String domain) {
                AntimatterTagBuilder<Biome> tags = this.tag(TagUtils.getBiomeTag(new ResourceLocation(ID, "is_invalid_rubber"))).addTag(BiomeTags.IS_TAIGA).addTag(BiomeTags.IS_MOUNTAIN).addTag(BiomeTags.IS_OCEAN).addTag(BiomeTags.IS_DEEP_OCEAN).addTag(BiomeTags.IS_NETHER).addTag(TagUtils.getBiomeTag(new ResourceLocation("is_desert"))).addTag(TagUtils.getBiomeTag(new ResourceLocation("is_plains")));
                boolean forge = AntimatterPlatformUtils.isForge();
                String d = forge ? "forge" : "c";
                String end = forge ? "is_end" : "in_the_end";
                tags.addTag(TagUtils.getBiomeTag(new ResourceLocation(d, end)));
                tags.addTag(TagUtils.getBiomeTag(new ResourceLocation(d, forge ? "is_snowy" : "snowy")));
            }
        });
        ev.addProvider(ID, () -> new AntimatterTagProvider<>(BuiltinRegistries.CONFIGURED_FEATURE, ID, NAME.concat(" Configured Feature Tags"), "worldgen/configured_feature") {
            @Override
            protected void processTags(String domain) {
                if (AntimatterAPI.isModLoaded("tfc")){
                    this.tag(TagKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, new ResourceLocation("tfc", "forest_trees"))).add(ResourceKey.create(Registry.CONFIGURED_FEATURE_REGISTRY, new ResourceLocation(GTRubber.ID, "tree/rubber_entry")));
                }
            }
        });
        ev.addProvider(ID, () -> new AntimatterWorldgenProvider(ID, NAME.concat(" Configured Features"), "configured_feature"){
            @Override
            public void run() {
                if (!AntimatterAPI.isModLoaded("tfc")) return;
                JsonObject object = new JsonObject();
                object.addProperty("type", "tfc:random_tree");
                JsonObject config = new JsonObject();
                JsonArray structures = new JsonArray();
                structures.add("gtrubber:rubber_dead/1");
                structures.add("gtrubber:rubber_dead/2");
                structures.add("gtrubber:rubber_dead/3");
                structures.add("gtrubber:rubber_dead/4");
                config.add("structures", structures);
                config.addProperty("radius", 1);
                JsonObject placement = new JsonObject();
                placement.addProperty("width", 1);
                placement.addProperty("height", 9);
                placement.addProperty("allow_submerged", true);
                placement.addProperty("allow_deeply_submerged", false);
                config.add("placement", placement);
                object.add("config", config);
                addJsonObject(new ResourceLocation(ID, "tree/rubber_dead"), object);

                object = new JsonObject();
                object.addProperty("type", "tfc:forest_entry");
                config = new JsonObject();
                config.addProperty("min_rain", 250);
                config.addProperty("max_rain", 400);
                config.addProperty("min_temp", 15.0);
                config.addProperty("max_temp", 40.0);
                JsonArray groundCover = new JsonArray();
                JsonObject block = new JsonObject();
                block.addProperty("block", "gtrubber:rubber_twig");
                groundCover.add(block);
                block = new JsonObject();
                block.addProperty("block", "gtrubber:rubber_fallen_leaves");
                groundCover.add(block);
                config.add("groundcover", groundCover);
                config.addProperty("normal_tree", "gtrubber:rubber_tree_normal");
                config.addProperty("dead_tree", "gtrubber:tree/rubber_dead");
                config.addProperty("fallen_log", "greubber:rubber_log");
                object.add("config", config);
                addJsonObject(new ResourceLocation(ID, "tree/rubber_entry"), object);

            }
        });
        ev.addProvider(ID, () -> new GTRubberItemTagProvider(ID, NAME.concat(" Item Tags"), false, p[0]));

        ev.addProvider(ID, () -> new GTRubberBlockLootProvider(ID, NAME.concat(" Loot generator")));
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {
        if (event == RegistrationEvent.DATA_INIT) {
            GTRubberData.init();
            RubberTree.init();
            RubberTreeWorldGen.init();
        } else if (event == RegistrationEvent.DATA_READY){
            WoodType.register(GTRubberData.RUBBER_WOOD_TYPE);
        } else if (event == RegistrationEvent.CLIENT_DATA_INIT){
            BakedModels.init();
        }
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void onMaterialEvent(MaterialEvent event) {
        event.setMaterial(GTRubberData.RUBBER).asSolid(295, PLATE, RING)
                .addHandleStat(11, 0.4F);
    }

    @Override
    public int getPriority() {
        return 50000;
    }
}
