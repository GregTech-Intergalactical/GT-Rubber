package com.github.gregtechintergalactical.gtrubber.fabric;

import com.github.gregtechintergalactical.gtrubber.GTRubber;
import muramasa.antimatter.event.fabric.ProviderEvents;
import net.fabricmc.api.ModInitializer;

public class GTRubberFabric implements ModInitializer {
    @Override
    public void onInitialize() {
        ProviderEvents.PROVIDERS.register(GTRubber.INSTANCE::onProviders);
    }
}
