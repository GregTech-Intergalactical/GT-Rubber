package com.github.gregtechintergalactical.gtrubber.tree.block;

import com.github.gregtechintergalactical.gtrubber.GTRubber;
import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.registration.IItemBlockProvider;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WallSignBlock;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.Material;

public class BlockRubberWallSign extends WallSignBlock implements IAntimatterObject, IItemBlockProvider {
    public BlockRubberWallSign() {
        super(Properties.of(Material.WOOD).noCollission().strength(1.0F).sound(SoundType.WOOD), GTRubberData.RUBBER_WOOD_TYPE);
    }

    @Override
    public String getDomain() {
        return GTRubber.ID;
    }

    @Override
    public String getId() {
        return "rubber_wall_sign";
    }

    @Override
    public boolean generateItemBlock() {
        return false;
    }
}
