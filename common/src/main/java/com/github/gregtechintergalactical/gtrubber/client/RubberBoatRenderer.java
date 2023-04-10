package com.github.gregtechintergalactical.gtrubber.client;

import com.github.gregtechintergalactical.gtrubber.GTRubber;
import com.github.gregtechintergalactical.gtrubber.entity.RubberBoatEntity;
import com.mojang.datafixers.util.Pair;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

public class RubberBoatRenderer extends BoatRenderer {
    final BoatModel model;
    public static final ModelLayerLocation LAYER = new ModelLayerLocation(new ResourceLocation(GTRubber.ID, "boat/rubber"), "main");
    public RubberBoatRenderer(EntityRendererProvider.Context context) {
        super(context);
        model = new BoatModel(context.bakeLayer(LAYER));
    }

    @Override
    public ResourceLocation getTextureLocation(Boat entity) {
        if (entity instanceof RubberBoatEntity){
            return new ResourceLocation(GTRubber.ID, "textures/entity/boat/rubber.png");
        }
        return super.getTextureLocation(entity);
    }

    public Pair<ResourceLocation, BoatModel> getModelWithLocation(Boat boat) {
        return Pair.of(getTextureLocation(boat), model);
    }
}
