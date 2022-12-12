package com.github.gregtechintergalactical.gtrubber.tree.item;

import com.github.gregtechintergalactical.gtrubber.GTRubber;
import com.github.gregtechintergalactical.gtrubber.GTRubberData;
import com.github.gregtechintergalactical.gtrubber.tree.block.BlockRubberSign;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.registration.IModelProvider;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.SignItem;
import net.minecraft.world.level.block.Block;

public class ItemRubberSign extends SignItem implements IAntimatterObject, ITextureProvider, IModelProvider {
    public ItemRubberSign() {
        super(new Item.Properties().stacksTo(16).tab(CreativeModeTab.TAB_DECORATIONS), GTRubberData.RUBBER_SIGN, GTRubberData.RUBBER_WALL_SIGN);
    }

    @Override
    public String getDomain() {
        return GTRubber.ID;
    }

    @Override
    public String getId() {
        return "rubber_sign";
    }

    @Override
    public Texture[] getTextures() {
        return new Texture[]{new Texture(GTRubber.ID, "item/basic/rubber_sign")};
    }
}
