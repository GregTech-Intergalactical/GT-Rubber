package io.github.gregtechintergalactical.gtrubber.tree.item;

import io.github.gregtechintergalactical.gtrubber.GTRubber;
import io.github.gregtechintergalactical.gtrubber.GTRubberData;
import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.registration.IModelProvider;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SignItem;

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
