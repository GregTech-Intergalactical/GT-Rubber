package com.github.gregtechintergalactical.gtrubber.client;

import com.github.gregtechintergalactical.gtrubber.GTRubber;
import dev.architectury.injectables.annotations.ExpectPlatform;
import muramasa.antimatter.client.AntimatterModelManager;
import net.minecraft.resources.ResourceLocation;

public class BakedModels {
    public static final ResourceLocation LOADER_SAP_BAG = new ResourceLocation(GTRubber.ID, "sap_bag");

    public static void init() {
        AntimatterModelManager.registerStaticConfigMap("sap_bag", () -> SapBagBakedModel.CONFIGS);
        initPlatform();
    }

    @ExpectPlatform
    public static void initPlatform(){
        throw new AssertionError();
    }
}
