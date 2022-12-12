package com.github.gregtechintergalactical.gtrubber.datagen;

import com.github.gregtechintergalactical.gtrubber.GTRubber;
import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;

public class GTRubberBlockTagProvider extends AntimatterBlockTagProvider {

    public GTRubberBlockTagProvider(String providerDomain, String providerName, boolean replace) {
        super(providerDomain, providerName, replace);
    }

    @Override
    protected void processTags(String domain) {
        super.processTags(domain);
        this.tag(BlockTags.LEAVES).add(GTRubberData.RUBBER_LEAVES);
        this.tag(BlockTags.SAPLINGS).add(GTRubberData.RUBBER_SAPLING);
        this.tag(BlockTags.PLANKS).add(GTRubberData.RUBBER_PLANKS);
        this.tag(BlockTags.WOODEN_SLABS).add(GTRubberData.RUBBER_SLAB);
        this.tag(BlockTags.WOODEN_STAIRS).add(GTRubberData.RUBBER_STAIRS);
        this.tag(BlockTags.WOODEN_FENCES).add(GTRubberData.RUBBER_FENCE);
        this.tag(BlockTags.FENCE_GATES).add(GTRubberData.RUBBER_FENCE_GATE);
        this.tag(BlockTags.WOODEN_DOORS).add(GTRubberData.RUBBER_DOOR);
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(GTRubberData.RUBBER_TRAPDOOR);
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(GTRubberData.RUBBER_PRESSURE_PLATE);
        this.tag(BlockTags.WOODEN_BUTTONS).add(GTRubberData.RUBBER_BUTTON);
        this.tag(BlockTags.STANDING_SIGNS).add(GTRubberData.RUBBER_SIGN);
        this.tag(BlockTags.WALL_SIGNS).add(GTRubberData.RUBBER_WALL_SIGN);
        this.tag(TagUtils.getBlockTag(new ResourceLocation(GTRubber.ID, "rubber_logs"))).add(GTRubberData.RUBBER_LOG, GTRubberData.STRIPPED_RUBBER_LOG, GTRubberData.RUBBER_WOOD, GTRubberData.STRIPPED_RUBBER_WOOD);
        this.tag(BlockTags.LOGS_THAT_BURN).addTag(TagUtils.getBlockTag(new ResourceLocation(GTRubber.ID, "rubber_logs")));
    }
}
