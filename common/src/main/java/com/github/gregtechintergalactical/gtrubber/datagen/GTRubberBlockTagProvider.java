package com.github.gregtechintergalactical.gtrubber.datagen;

import com.github.gregtechintergalactical.gtrubber.GTRubber;
import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class GTRubberBlockTagProvider extends AntimatterBlockTagProvider {

    public GTRubberBlockTagProvider(String providerDomain, String providerName, boolean replace) {
        super(providerDomain, providerName, replace);
    }

    @Override
    protected void processTags(String domain) {
        super.processTags(domain);
        this.tag(BlockTags.LOGS).add(GTRubberData.RUBBER_LOG);
        this.tag(BlockTags.LEAVES).add(GTRubberData.RUBBER_LEAVES);
        this.tag(BlockTags.SAPLINGS).add(GTRubberData.RUBBER_SAPLING);
        this.tag(BlockTags.PLANKS).add(GTRubberData.RUBBER_PLANKS);
        this.tag(TagUtils.getBlockTag(new ResourceLocation(GTRubber.ID, "rubber_logs"))).add(GTRubberData.RUBBER_LOG, GTRubberData.STRIPPED_RUBBER_LOG, GTRubberData.RUBBER_WOOD, GTRubberData.STRIPPED_RUBBER_WOOD);
        this.tag(TagUtils.getBlockTag(new ResourceLocation("minecraft", "logs_that_burn"))).addTag(TagUtils.getBlockTag(new ResourceLocation(GTRubber.ID, "rubber_logs")));
    }
}
