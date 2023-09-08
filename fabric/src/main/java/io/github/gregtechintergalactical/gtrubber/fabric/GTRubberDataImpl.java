package io.github.gregtechintergalactical.gtrubber.fabric;

import io.github.gregtechintergalactical.gtrubber.GTRubber;
import io.github.gregtechintergalactical.gtrubber.tree.block.BlockRubberLeaves;
import net.minecraft.world.level.block.Block;

public class GTRubberDataImpl {
    public static Block createRubberLeaves(){
        return new BlockRubberLeaves(GTRubber.ID, "rubber_leaves");
    }
}
