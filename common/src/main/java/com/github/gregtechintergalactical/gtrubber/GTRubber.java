package com.github.gregtechintergalactical.gtrubber;

import com.github.gregtechintergalactical.gtrubber.tree.RubberTree;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Data;
import muramasa.antimatter.Ref;
import muramasa.antimatter.datagen.AntimatterDynamics;
import muramasa.antimatter.datagen.providers.AntimatterAdvancementProvider;
import muramasa.antimatter.datagen.providers.AntimatterBlockLootProvider;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterFluidTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemModelProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterLanguageProvider;
import muramasa.antimatter.event.MaterialEvent;
import muramasa.antimatter.event.ProvidersEvent;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.cover.ICover;
import muramasa.antimatter.registration.Side;
import net.devtech.arrp.json.recipe.JIngredient;
import net.devtech.arrp.json.recipe.JRecipe;
import net.devtech.arrp.json.recipe.JResult;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import static muramasa.antimatter.Data.PLATE;
import static muramasa.antimatter.Data.RING;

public class GTRubber extends AntimatterMod {

    public static GTRubber INSTANCE;

    public static final Logger LOGGER = LogManager.getLogger(); // Directly reference a log4j logger.
    public static final String ID = "gtrubber", NAME = "GT Rubber";

    @Override
    public void onRegistrarInit() {
        INSTANCE = this;
        super.onRegistrarInit();
        AntimatterDynamics.clientProvider(ID, () -> new AntimatterBlockStateProvider(ID, NAME + " BlockStates"));
        AntimatterDynamics.clientProvider(ID, () -> new AntimatterItemModelProvider(ID, NAME + " Item Models"));
        AntimatterDynamics.clientProvider(ID, () -> new AntimatterLanguageProvider(ID, NAME + " en_us Localization", "en_us"){
            @Override
            protected void english(String domain, String locale) {
                super.english(domain, locale);
                add(GTRubberData.RUBBER_LEAVES, "Rubber Leaves");
                add(GTRubberData.RUBBER_LOG, "Rubber Log");
                add(GTRubberData.RUBBER_SAPLING, "Rubber Sapling");
            }
        });
    }

    public void onProviders(ProvidersEvent ev) {
        final AntimatterBlockTagProvider[] p = new AntimatterBlockTagProvider[1];
        ev.addProvider(ID, () -> {
            p[0] = new AntimatterBlockTagProvider(ID, NAME.concat(" Block Tags"), false){
                @Override
                protected void processTags(String domain) {
                    super.processTags(domain);
                    this.tag(BlockTags.LOGS).add(GTRubberData.RUBBER_LOG);
                    this.tag(BlockTags.LEAVES).add(GTRubberData.RUBBER_LEAVES);
                    this.tag(BlockTags.SAPLINGS).add(GTRubberData.RUBBER_SAPLING);
                }
            };
            return p[0];
        });

        ev.addProvider(ID,
                () -> new AntimatterBlockLootProvider(ID, NAME.concat(" Loot generator")){
                    @Override
                    protected void loot() {
                        super.loot();
                        tables.put(GTRubberData.RUBBER_LEAVES, b -> createLeavesDrops(GTRubberData.RUBBER_LEAVES, GTRubberData.RUBBER_SAPLING, 0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F));
                        this.add(GTRubberData.RUBBER_LOG);
                        this.add(GTRubberData.RUBBER_SAPLING);
                    }
                });
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {
        switch (event) {
            case DATA_INIT -> {
                GTRubberData.init();
                RubberTree.init();
            }
            case DATA_READY -> {
                if (!AntimatterAPI.isModLoaded("gregtech") && !AntimatterAPI.isModLoaded("gt4r")){
                    AntimatterDynamics.DYNAMIC_RESOURCE_PACK.addRecipe(new ResourceLocation(ID, "resin_to_rubber"), JRecipe.smelting(JIngredient.ingredient().item(GTRubberData.StickyResin), JResult.item(Data.DUST.get(GTRubberData.RUBBER))).cookingTime(200).experience(0.1f));
                }
                AntimatterDynamics.DYNAMIC_RESOURCE_PACK.addRecipe(new ResourceLocation(ID, "rubber_dust_to_rubber_ingot"), JRecipe.smelting(JIngredient.ingredient().item(Data.DUST.get(GTRubberData.RUBBER)), JResult.item(Data.INGOT.get(GTRubberData.RUBBER))).cookingTime(200).experience(0.1f));
            }
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
}
