package com.github.gregtechintergalactical.gtrubber.datagen;

import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.datagen.providers.AntimatterLanguageProvider;

public class GTRubberLanguageProvider extends AntimatterLanguageProvider {
    public GTRubberLanguageProvider(String providerDomain, String providerName, String locale) {
        super(providerDomain, providerName, locale);
    }

    @Override
    protected void english(String domain, String locale) {
        super.english(domain, locale);
        add(GTRubberData.RUBBER_LEAVES, "Rubber Leaves");
        add(GTRubberData.RUBBER_LOG, "Rubber Log");
        add(GTRubberData.STRIPPED_RUBBER_LOG, "Stripped Rubber Log");
        add(GTRubberData.RUBBER_WOOD, "Rubber Wood");
        add(GTRubberData.STRIPPED_RUBBER_WOOD, "Stripped Rubber Wood");
        add(GTRubberData.RUBBER_PLANKS, "Rubber Planks");
        add(GTRubberData.RUBBER_SAPLING, "Rubber Sapling");
        add(GTRubberData.RUBBER_SIGN, "Rubber Sign");
        add(GTRubberData.RUBBER_DOOR, "Rubber Door");
        add(GTRubberData.RUBBER_TRAPDOOR, "Rubber Trapdoor");
        add(GTRubberData.RUBBER_BUTTON, "Rubber Button");
        add(GTRubberData.RUBBER_PRESSURE_PLATE, "Rubber Pressure Plate");
        add(GTRubberData.RUBBER_SLAB, "Rubber Slab");
        add(GTRubberData.RUBBER_STAIRS, "Rubber Stairs");
        add(GTRubberData.RUBBER_FENCE, "Rubber Fence");
        add(GTRubberData.RUBBER_FENCE_GATE, "Rubber Fence Gate");
    }
}
