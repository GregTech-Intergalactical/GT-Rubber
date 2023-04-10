package com.github.gregtechintergalactical.gtrubber.forge;

import com.github.gregtechintergalactical.gtrubber.GTRubber;
import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import com.github.gregtechintergalactical.gtrubber.GTRubberRecipes;
import com.github.gregtechintergalactical.gtrubber.client.ClientHandler;
import com.github.gregtechintergalactical.gtrubber.client.RubberBoatRenderer;
import com.github.gregtechintergalactical.gtrubber.tree.RubberFoliagePlacer;
import muramasa.antimatter.Ref;
import muramasa.antimatter.event.forge.AntimatterCraftingEvent;
import muramasa.antimatter.event.forge.AntimatterProvidersEvent;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(GTRubber.ID)
public class GTRubberForge extends GTRubber {
    public GTRubberForge(){
        super();
        DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onEntityRendererRegister);
            FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onRegisterModelLayers);
        });

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onProvidersEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCraftingEvent);
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(FoliagePlacerType.class, this::onRegistration);
        FMLJavaModLoadingContext.get().getModEventBus().addGenericListener(EntityType.class, this::onRegister);
    }

    @OnlyIn(Dist.CLIENT)
    private void clientSetup(FMLClientSetupEvent event){
        ClientHandler.init();
    }

    @OnlyIn(Dist.CLIENT)
    private void onEntityRendererRegister(EntityRenderersEvent.RegisterRenderers event){
        event.registerBlockEntityRenderer((BlockEntityType<? extends SignBlockEntity>) GTRubberData.SIGN_BLOCK_ENTITY, SignRenderer::new);
        event.registerEntityRenderer(GTRubberData.RUBBER_BOAT_ENTITY, RubberBoatRenderer::new);
    }

    public void onRegisterModelLayers(EntityRenderersEvent.RegisterLayerDefinitions event){
        event.registerLayerDefinition(RubberBoatRenderer.LAYER, BoatModel::createBodyModel);
    }

    private void onRegister(RegistryEvent.Register<EntityType<?>> event){
        GTRubberData.RUBBER_BOAT_ENTITY.setRegistryName(GTRubber.ID + ":rubber_boat");
        event.getRegistry().register(GTRubberData.RUBBER_BOAT_ENTITY);
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
