package com.github.gregtechintergalactical.gtrubber.tree.block;

import com.github.gregtechintergalactical.gtrubber.GTRubber;
import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.registration.IItemBlockProvider;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.StandingSignBlock;
import net.minecraft.world.level.material.Material;

public class BlockRubberSign extends StandingSignBlock implements IAntimatterObject, IItemBlockProvider {

    public BlockRubberSign() {
        super(Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), GTRubberData.RUBBER_WOOD_TYPE);
        AntimatterAPI.register(BlockRubberSign.class, this);
    }

    @Override
    public String getId() {
        return "rubber_sign.json";
    }
    @Override
    public String getDomain() {
        return GTRubber.ID;
    }

    @Override
    public boolean generateItemBlock() {
        return false;
    }
}