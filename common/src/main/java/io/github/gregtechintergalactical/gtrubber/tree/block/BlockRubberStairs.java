package io.github.gregtechintergalactical.gtrubber.tree.block;

import io.github.gregtechintergalactical.gtrubber.GTRubber;
import io.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.registration.IModelProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.StairBlock;

public class BlockRubberStairs extends StairBlock implements IAntimatterObject, IModelProvider {
    public BlockRubberStairs() {
        super(GTRubberData.RUBBER_PLANKS.defaultBlockState(), Properties.copy(GTRubberData.RUBBER_PLANKS));
        AntimatterAPI.register(BlockRubberStairs.class, this);
    }

    @Override
    public String getDomain() {
        return GTRubber.ID;
    }

    @Override
    public String getId() {
        return "rubber_stairs";
    }

    @Override
    public void onBlockModelBuild(Block block, AntimatterBlockStateProvider prov) {
        prov.stairsBlock(this, new ResourceLocation(GTRubber.ID, "block/tree/rubber_planks"));
    }
}
