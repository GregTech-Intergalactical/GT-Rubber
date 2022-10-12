package com.github.gregtechintergalactical.gtrubber.fabric;

import com.github.gregtechintergalactical.gtrubber.GTRubber;
import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.AntimatterMod;
import muramasa.antimatter.registration.RegistrationEvent;
import muramasa.antimatter.registration.Side;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;

public class GTRubberFabricRegistrar extends AntimatterMod {

    @Override
    public String getId() {
        return GTRubber.ID + "_fabric";
    }

    @Override
    public void onRegistrationEvent(RegistrationEvent event, Side side) {
        if (event == RegistrationEvent.DATA_READY){
            FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();
            flammableRegistry.add(GTRubberData.RUBBER_LOG, 5, 5);
            flammableRegistry.add(GTRubberData.RUBBER_LEAVES, 60, 30);
        }
    }

    @Override
    public int getPriority() {
        return 850;
    }
}
