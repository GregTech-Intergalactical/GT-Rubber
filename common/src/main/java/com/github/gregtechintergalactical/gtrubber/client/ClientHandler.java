package com.github.gregtechintergalactical.gtrubber.client;

import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.client.ModelUtils;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.resources.model.Material;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.state.properties.WoodType;

public class ClientHandler {
    public static void init() {
        ModelUtils.setRenderLayer(GTRubberData.RUBBER_SAPLING, RenderType.cutout());
        ModelUtils.setRenderLayer(GTRubberData.RUBBER_LEAVES, RenderType.cutout());
        Sheets.SIGN_MATERIALS.put(GTRubberData.RUBBER_WOOD_TYPE, createSignMaterial(GTRubberData.RUBBER_WOOD_TYPE));
    }

    private static Material createSignMaterial(WoodType woodType) {
        ResourceLocation location = new ResourceLocation(woodType.name());
        return new Material(Sheets.SIGN_SHEET, new ResourceLocation(location.getNamespace(), "entity/signs/" + location.getPath()));
    }
}
