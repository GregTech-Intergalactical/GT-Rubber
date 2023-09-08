package io.github.gregtechintergalactical.gtrubber.forge;

import io.github.gregtechintergalactical.gtrubber.GTRubber;
import io.github.gregtechintergalactical.gtrubber.tfc.tfc.BlockTFCRubberLoaves;
import io.github.gregtechintergalactical.gtrubber.tree.block.BlockRubberLeaves;
import muramasa.antimatter.AntimatterAPI;
import net.minecraft.world.level.block.Block;

public class GTRubberDataImpl {
    public static Block createRubberLeaves(){
        if (AntimatterAPI.isModLoaded("tfc")){
            return createTFCLeaves();
        } else {
            return new BlockRubberLeaves(GTRubber.ID, "rubber_leaves");
        }
    }

    private static Block createTFCLeaves(){
        return new BlockTFCRubberLoaves();
    }
}
