package com.github.gregtechintergalactical.gtrubber.datagen;

import com.github.gregtechintergalactical.gtrubber.GTRubber;
import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.datagen.providers.AntimatterBlockTagProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemTagProvider;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.resources.ResourceLocation;

public class GTRubberItemTagProvider extends AntimatterItemTagProvider {
    public GTRubberItemTagProvider(String providerDomain, String providerName, boolean replace, AntimatterBlockTagProvider p) {
        super(providerDomain, providerName, replace, p);
    }

    @Override
    protected void processTags(String domain) {
        super.processTags(domain);
        this.copy(TagUtils.getBlockTag(new ResourceLocation(GTRubber.ID, "rubber_logs")), GTRubberData.RUBBER_LOGS);
    }
}
