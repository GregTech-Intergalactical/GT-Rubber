package com.github.gregtechintergalactical.gtrubber.datagen;

import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.datagen.providers.AntimatterBlockLootProvider;
import net.minecraft.data.loot.BlockLoot;

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
        this.add(GTRubberData.RUBBER_FENCE);
        this.add(GTRubberData.RUBBER_FENCE_GATE);
        this.add(GTRubberData.RUBBER_PRESSURE_PLATE);
        this.add(GTRubberData.RUBBER_BUTTON);
        this.add(GTRubberData.RUBBER_STAIRS);
        tables.put(GTRubberData.RUBBER_SLAB, BlockLoot::createSlabItemTable);
        tables.put(GTRubberData.RUBBER_DOOR, BlockLoot::createDoorTable);
        this.add(GTRubberData.RUBBER_TRAPDOOR);
        tables.put(GTRubberData.RUBBER_SIGN, b -> BlockLoot.createSingleItemTable(GTRubberData.RUBBER_SIGN.asItem()));
        tables.put(GTRubberData.RUBBER_WALL_SIGN, b -> BlockLoot.createSingleItemTable(GTRubberData.RUBBER_SIGN.asItem()));

    }
}
