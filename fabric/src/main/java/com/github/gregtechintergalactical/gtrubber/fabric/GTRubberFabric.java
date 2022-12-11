package com.github.gregtechintergalactical.gtrubber.fabric;

import com.github.gregtechintergalactical.gtrubber.GTRubber;
import com.github.gregtechintergalactical.gtrubber.GTRubberRecipes;
import muramasa.antimatter.event.fabric.CraftingEvents;
import muramasa.antimatter.event.fabric.ProviderEvents;
import net.fabricmc.api.ModInitializer;

public class GTRubberFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ProviderEvents.PROVIDERS.register(GTRubber::onProviders);
        CraftingEvents.CRAFTING.register(GTRubberRecipes::onCraftingEvent);
    }
}
