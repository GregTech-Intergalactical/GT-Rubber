package io.github.gregtechintergalactical.gtrubber.client;

import io.github.gregtechintergalactical.gtrubber.GTRubber;
import dev.architectury.injectables.annotations.ExpectPlatform;
import muramasa.antimatter.client.AntimatterModelManager;
import muramasa.antimatter.client.model.loader.DynamicModelLoader;
import muramasa.antimatter.dynamic.DynamicModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;

import java.util.function.Function;

public class BakedModels {
    public static final ResourceLocation LOADER_SAP_BAG = new ResourceLocation(GTRubber.ID, "sap_bag");

    public static void init() {
        AntimatterModelManager.registerStaticConfigMap("sap_bag", () -> SapBagBakedModel.CONFIGS);
        new DynamicModelLoader(LOADER_SAP_BAG) {
            @Override
            public DynamicModel readModel(JsonDeserializationContext context, JsonObject json) {
                return new DynamicModel(super.readModel(context, json)) {
                    @Override
                    public BakedModel bakeModel(ModelBakery bakery, Function<Material, TextureAtlasSprite> getter, ModelState transform, ResourceLocation loc) {
                        return new SapBagBakedModel(getter.apply(new Material(InventoryMenu.BLOCK_ATLAS, particle)), getBakedConfigs(bakery, getter, transform, loc));
                    }
                };
            }
        };
    }

    @ExpectPlatform
    public static void initPlatform(){
        throw new AssertionError();
    }
}
