package com.github.gregtechintergalactical.gtrubber.client.forge;

import com.github.gregtechintergalactical.gtrubber.client.SapBagBakedModel;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonObject;
import muramasa.antimatter.client.model.loader.forge.AntimatterModelLoader;
import muramasa.antimatter.dynamic.DynamicModel;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraftforge.client.model.IModelConfiguration;

import java.util.function.Function;

import static com.github.gregtechintergalactical.gtrubber.client.BakedModels.LOADER_SAP_BAG;

public class BakedModelsImpl {
    public static void initPlatform(){
        new AntimatterModelLoader.DynamicModelLoader(LOADER_SAP_BAG) {
            @Override
            public DynamicModel read(JsonDeserializationContext context, JsonObject json) {
                return new DynamicModel((DynamicModel) super.read(context, json)) {
                    @Override
                    public BakedModel bakeModel(IModelConfiguration owner, ModelBakery bakery, Function<Material, TextureAtlasSprite> getter, ModelState transform, ItemOverrides overrides, ResourceLocation loc) {
                        return new SapBagBakedModel(getter.apply(new Material(InventoryMenu.BLOCK_ATLAS, particle)), getBakedConfigs(owner, bakery, getter, transform, overrides, loc));
                    }
                };
            }
        };
    }
}
