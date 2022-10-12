package com.github.gregtechintergalactical.gtrubber;

import com.github.gregtechintergalactical.gtrubber.tree.BlockRubberLeaves;
import com.github.gregtechintergalactical.gtrubber.tree.BlockRubberLog;
import com.github.gregtechintergalactical.gtrubber.tree.BlockRubberSapling;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.Ref;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;

import static muramasa.antimatter.material.TextureSet.SHINY;

public class GTRubberData {


    public static Material RUBBER = AntimatterAPI.register(Material.class, new Material(GTRubber.ID, "rubber", 0x000000, SHINY));

    public static ItemBasic<?> StickyResin = new ItemBasic<>(GTRubber.ID, "sticky_resin");

    public static final BlockRubberLeaves RUBBER_LEAVES = new BlockRubberLeaves(GTRubber.ID, "rubber_leaves");
    public static final BlockRubberLog RUBBER_LOG = new BlockRubberLog(GTRubber.ID, "rubber_log");
    public static final BlockRubberSapling RUBBER_SAPLING = new BlockRubberSapling(GTRubber.ID, "rubber_sapling");

    public static void init() {

    }
}
