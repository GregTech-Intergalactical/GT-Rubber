package com.github.gregtechintergalactical.gtrubber.forge;

import com.github.gregtechintergalactical.gtrubber.GTRubber;
import com.github.gregtechintergalactical.gtrubber.GTRubberRecipes;
import com.github.gregtechintergalactical.gtrubber.client.ClientHandler;
import com.github.gregtechintergalactical.gtrubber.tree.RubberFoliagePlacer;
import muramasa.antimatter.Ref;
import muramasa.antimatter.event.forge.AntimatterCraftingEvent;
import muramasa.antimatter.event.forge.AntimatterProvidersEvent;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.event.RegistryEvent;
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
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCraftingEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(FoliagePlacerType.class, this::onRegistration);
    }

    private void clientSetup(FMLClientSetupEvent event){
        ClientHandler.init();
    }

    private void onProvidersEvent(AntimatterProvidersEvent event){
        onProviders(event.event);
    }

    private void onCraftingEvent(AntimatterCraftingEvent event){
        GTRubberRecipes.onCraftingEvent(event.getEvent());
    }

    private void onRegistration(final RegistryEvent.Register<FoliagePlacerType<?>> e){
        e.getRegistry().register(RubberFoliagePlacer.RUBBER.setRegistryName(Ref.ID, "rubber_foilage_placer"));
    }
}
