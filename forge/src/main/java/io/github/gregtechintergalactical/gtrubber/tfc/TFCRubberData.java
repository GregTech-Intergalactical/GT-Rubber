package io.github.gregtechintergalactical.gtrubber.tfc;

import io.github.gregtechintergalactical.gtrubber.GTRubber;
import io.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.item.ItemBasic;
import net.minecraft.world.level.block.Block;

public class TFCRubberData {

    public static final Block RUBBER_TWIG = new BlockRubberTwig();
    public static final Block RUBBER_FALLEN_LEAVES = new BlockFallenRubberLeaves();
    public static final ItemBasic<?> RUBBER_LUMBER = new ItemBasic<>(GTRubber.ID, "rubber_lumber");

    public static void init(){
        GTRubberData.RUBBER_LEAVES = new BlockTFCRubberLoaves();
        GTRubberData.RUBBER_SAPLING = new BlockTFCRubberSapling();
    }
}
