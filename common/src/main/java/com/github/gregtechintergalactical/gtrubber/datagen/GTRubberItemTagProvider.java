package com.github.gregtechintergalactical.gtrubber.datagen;

import com.github.gregtechintergalactical.gtrubber.GTRubber;
import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemTagProvider;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;

public class GTRubberItemTagProvider extends AntimatterItemTagProvider {
    public GTRubberItemTagProvider(String providerDomain, String providerName, boolean replace, AntimatterBlockTagProvider p) {
        super(providerDomain, providerName, replace, p);
    }

    @Override
    protected void processTags(String domain) {
        super.processTags(domain);
        this.copy(TagUtils.getBlockTag(new ResourceLocation(GTRubber.ID, "rubber_logs")), GTRubberData.RUBBER_LOGS);
        this.tag(ItemTags.LEAVES).add(GTRubberData.RUBBER_LEAVES.asItem());
        this.tag(ItemTags.PLANKS).add(GTRubberData.RUBBER_PLANKS.asItem());
        this.tag(ItemTags.SLABS).add(GTRubberData.RUBBER_SLAB.asItem());
        this.tag(ItemTags.STAIRS).add(GTRubberData.RUBBER_STAIRS.asItem());
        this.tag(ItemTags.SIGNS).add(GTRubberData.RUBBER_SIGN.asItem());
        this.tag(ItemTags.WOODEN_BUTTONS).add(GTRubberData.RUBBER_BUTTON.asItem());
        this.tag(ItemTags.WOODEN_DOORS).add(GTRubberData.RUBBER_DOOR.asItem());
        this.tag(ItemTags.WOODEN_FENCES).add(GTRubberData.RUBBER_FENCE.asItem());
        this.tag(ItemTags.WOODEN_PRESSURE_PLATES).add(GTRubberData.RUBBER_PRESSURE_PLATE.asItem());
        this.tag(ItemTags.WOODEN_SLABS).add(GTRubberData.RUBBER_SLAB.asItem());
        this.tag(ItemTags.WOODEN_STAIRS).add(GTRubberData.RUBBER_STAIRS.asItem());
        this.tag(ItemTags.WOODEN_TRAPDOORS).add(GTRubberData.RUBBER_TRAPDOOR.asItem());
    }
}
