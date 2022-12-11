package com.github.gregtechintergalactical.gtrubber.datagen;

import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.datagen.providers.AntimatterBlockLootProvider;

public class GTRubberBlockLootProvider extends AntimatterBlockLootProvider {
    public GTRubberBlockLootProvider(String providerDomain, String providerName) {
        super(providerDomain, providerName);
    }

    @Override
    protected void loot() {
        super.loot();
        tables.put(GTRubberData.RUBBER_LEAVES, b -> createLeavesDrops(GTRubberData.RUBBER_LEAVES, GTRubberData.RUBBER_SAPLING, 0.025F, 0.027777778F, 0.03125F, 0.041666668F, 0.1F));
        this.add(GTRubberData.RUBBER_LOG);
        this.add(GTRubberData.RUBBER_SAPLING);
        this.add(GTRubberData.STRIPPED_RUBBER_LOG);
        this.add(GTRubberData.RUBBER_WOOD);
        this.add(GTRubberData.STRIPPED_RUBBER_WOOD);
        this.add(GTRubberData.RUBBER_PLANKS);
    }
}
