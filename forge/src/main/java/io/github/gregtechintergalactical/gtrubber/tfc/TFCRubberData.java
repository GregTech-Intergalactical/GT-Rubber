package io.github.gregtechintergalactical.gtrubber.tfc;

import io.github.gregtechintergalactical.gtrubber.GTRubberData;
import net.minecraft.world.level.block.Block;

public class TFCRubberData {

    public static final Block RUBBER_TWIG = new BlockRubberTwig();

    public static void init(){
        GTRubberData.RUBBER_LEAVES = new BlockTFCRubberLoaves();
        GTRubberData.RUBBER_SAPLING = new BlockTFCRubberSapling();
    }
}
