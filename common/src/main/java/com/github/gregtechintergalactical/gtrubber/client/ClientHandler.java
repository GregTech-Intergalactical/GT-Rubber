package com.github.gregtechintergalactical.gtrubber.client;

import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.client.ModelUtils;
import net.minecraft.client.renderer.RenderType;

public class ClientHandler {
    public static void init() {
        ModelUtils.setRenderLayer(GTRubberData.RUBBER_SAPLING, RenderType.cutout());
        ModelUtils.setRenderLayer(GTRubberData.RUBBER_LEAVES, RenderType.cutout());
    }
}
