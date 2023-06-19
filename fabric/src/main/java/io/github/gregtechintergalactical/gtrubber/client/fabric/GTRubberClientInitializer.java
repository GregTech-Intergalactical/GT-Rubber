package io.github.gregtechintergalactical.gtrubber.client.fabric;

import io.github.gregtechintergalactical.gtrubber.GTRubberData;
import io.github.gregtechintergalactical.gtrubber.client.ClientHandler;
import io.github.gregtechintergalactical.gtrubber.client.RubberBoatRenderer;
import muramasa.antimatter.client.fabric.IAntimatterClientInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;

public class GTRubberClientInitializer implements IAntimatterClientInitializer {
    @Override
    public void onInitializeClient() {
        ClientHandler.init();
        BlockEntityRendererRegistry.register((BlockEntityType<SignBlockEntity>) GTRubberData.SIGN_BLOCK_ENTITY, SignRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(RubberBoatRenderer.LAYER, BoatModel::createBodyModel);
        EntityRendererRegistry.register(GTRubberData.RUBBER_BOAT_ENTITY, RubberBoatRenderer::new);
    }
}
