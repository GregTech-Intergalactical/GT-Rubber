package com.github.gregtechintergalactical.gtrubber;

import com.github.gregtechintergalactical.gtrubber.datagen.GTRubberBlockLootProvider;
import com.github.gregtechintergalactical.gtrubber.datagen.GTRubberBlockTagProvider;
import com.github.gregtechintergalactical.gtrubber.datagen.GTRubberItemTagProvider;
import com.github.gregtechintergalactical.gtrubber.datagen.GTRubberLanguageProvider;
import com.github.gregtechintergalactical.gtrubber.tree.RubberTree;
import com.github.gregtechintergalactical.gtrubber.tree.RubberTreeWorldGen;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Data;
import muramasa.antimatter.datagen.AntimatterDynamics;
import muramasa.antimatter.datagen.providers.*;
import muramasa.antimatter.event.MaterialEvent;
import muramasa.antimatter.event.ProvidersEvent;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.registration.Side;
import muramasa.antimatter.util.AntimatterPlatformUtils;
import muramasa.antimatter.util.TagUtils;
import net.devtech.arrp.json.recipe.JIngredient;
import net.devtech.arrp.json.recipe.JRecipe;
import net.devtech.arrp.json.recipe.JResult;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.biome.Biome;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;
import static muramasa.antimatter.data.AntimatterMaterialTypes.INGOT;
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
                TagsProvider.TagAppender<Biome> tags = this.tag(TagUtils.getBiomeTag(new ResourceLocation(ID, "is_invalid_rubber"))).addTag(BiomeTags.IS_TAIGA).addTag(BiomeTags.IS_MOUNTAIN).addTag(BiomeTags.IS_OCEAN).addTag(BiomeTags.IS_DEEP_OCEAN).addTag(BiomeTags.IS_NETHER).addTag(TagUtils.getBiomeTag(new ResourceLocation("is_desert"))).addTag(TagUtils.getBiomeTag(new ResourceLocation("is_plains")));
                boolean forge = AntimatterPlatformUtils.isForge();
                String d = forge ? "forge" : "c";
                String end = forge ? "is_end" : "in_the_end";
                tags.addTag(TagUtils.getBiomeTag(new ResourceLocation(d, end)));
                tags.addTag(TagUtils.getBiomeTag(new ResourceLocation(d, forge ? "is_snowy" : "snowy")));
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
        }
    }

    @Override
    public String getId() {
        return ID;
    }

    @Override
    public void onMaterialEvent(MaterialEvent event) {
        event.setMaterial(GTRubberData.RUBBER).asSolid(295, 0, PLATE, RING)
                .addHandleStat(11, 0.4F);
    }

    @Override
    public int getPriority() {
        return 50000;
    }
}
