package com.github.gregtechintergalactical.gtrubber.forge;

import com.github.gregtechintergalactical.gtrubber.GTRubber;
import com.github.gregtechintergalactical.gtrubber.client.ClientHandler;
import muramasa.antimatter.event.forge.AntimatterProvidersEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GTRubber.ID)
public class GTRubberForge extends GTRubber {
    public GTRubberForge(){
        super();
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onProvidersEvent);
    }

    private void clientSetup(FMLClientSetupEvent event){
        ClientHandler.init();
    }

    private void onProvidersEvent(AntimatterProvidersEvent event){
        onProviders(event.event);
    }
}
