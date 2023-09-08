package io.github.gregtechintergalactical.gtrubber.tree.block;

import io.github.gregtechintergalactical.gtrubber.GTRubber;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.block.BlockPropertiesHelper;
import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.registration.IModelProvider;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import net.minecraft.world.level.block.LeavesBlock;

public class BlockRubberLeaves extends LeavesBlock implements IAntimatterObject, IModelProvider, ITextureProvider {

    public BlockRubberLeaves() {
        super(BlockPropertiesHelper.leaves());
        AntimatterAPI.register(BlockRubberLeaves.class, this);
    }

    @Override
    public String getDomain() {
        return GTRubber.ID;
    }

    @Override
    public String getId() {
        return "rubber_leaves";
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[] { new Texture(getDomain(), "block/tree/" + getId()) };
    }
}
