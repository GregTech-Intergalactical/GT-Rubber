package io.github.gregtechintergalactical.gtrubber.tree.block;

import io.github.gregtechintergalactical.gtrubber.GTRubber;
import io.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.registration.IModelProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FenceGateBlock;

public class BlockRubberFenceGate extends FenceGateBlock implements IAntimatterObject, IModelProvider {
    public BlockRubberFenceGate() {
        super(Properties.copy(GTRubberData.RUBBER_SLAB));
        AntimatterAPI.register(BlockRubberFenceGate.class, this);
    }

    @Override
    public String getId() {
        return "rubber_fence_gate";
    }

    @Override
    public String getDomain() {
        return GTRubber.ID;
    }

    @Override
    public void onBlockModelBuild(Block block, AntimatterBlockStateProvider prov) {
        prov.fenceGateBlock(this, new ResourceLocation(GTRubber.ID, "block/tree/rubber_planks"));
    }
}
