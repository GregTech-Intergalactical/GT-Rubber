package io.github.gregtechintergalactical.gtrubber.datagen;

import io.github.gregtechintergalactical.gtrubber.GTRubber;
import io.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemTagProvider;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

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
        if (AntimatterAPI.isModLoaded("tfc")){
            this.tag(ItemTags.WOODEN_FENCES).add(AntimatterAPI.get(Item.class, "rubber_log_fence", GTRubber.ID));
            this.tag(TagUtils.getItemTag(new ResourceLocation("tfc", "lumber"))).add(AntimatterAPI.get(Item.class, "rubber_lumber", GTRubber.ID));
            this.tag(TagUtils.getItemTag(new ResourceLocation("tfc", "twigs"))).add(AntimatterAPI.get(Item.class, "rubber_twig", GTRubber.ID));
            this.tag(TagUtils.getItemTag(new ResourceLocation("tfc", "fallen_leaves"))).add(AntimatterAPI.get(Item.class, "rubber_fallen_leaves", GTRubber.ID));
            this.tag(TagUtils.getItemTag(new ResourceLocation("tfc", "firepit_fuel"))).addTag(GTRubberData.RUBBER_LOGS);
            this.tag(TagUtils.getItemTag(new ResourceLocation("tfc", "firepit_logs"))).addTag(GTRubberData.RUBBER_LOGS);
            this.tag(TagUtils.getItemTag(new ResourceLocation("tfc", "firepit_fuel"))).addTag(GTRubberData.RUBBER_LOGS);
            this.tag(TagUtils.getItemTag(new ResourceLocation("tfc", "pit_kiln_logs"))).addTag(GTRubberData.RUBBER_LOGS);
            this.tag(TagUtils.getItemTag(new ResourceLocation("tfc", "log_pile_logs"))).addTag(GTRubberData.RUBBER_LOGS);

        }
    }
}
