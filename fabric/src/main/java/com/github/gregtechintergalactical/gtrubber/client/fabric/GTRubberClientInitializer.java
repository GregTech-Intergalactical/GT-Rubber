package com.github.gregtechintergalactical.gtrubber.client.fabric;

import com.github.gregtechintergalactical.gtrubber.client.ClientHandler;
import muramasa.antimatter.client.fabric.IAntimatterClientInitializer;

public class GTRubberClientInitializer implements IAntimatterClientInitializer {
    @Override
    public void onInitializeClient() {
        ClientHandler.init();
    }
}
