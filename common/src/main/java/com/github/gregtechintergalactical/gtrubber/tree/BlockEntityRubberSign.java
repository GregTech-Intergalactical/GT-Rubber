package com.github.gregtechintergalactical.gtrubber.tree;

import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class BlockEntityRubberSign extends SignBlockEntity {
    public BlockEntityRubberSign(BlockPos blockPos, BlockState blockState) {
        super(blockPos, blockState);
    }

    @Override
    public BlockEntityType<BlockEntityRubberSign> getType() {
        return GTRubberData.SIGN_BLOCK_ENTITY;
    }
}
