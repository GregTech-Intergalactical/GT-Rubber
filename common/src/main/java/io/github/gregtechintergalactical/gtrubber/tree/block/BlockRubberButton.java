package io.github.gregtechintergalactical.gtrubber.tree.block;

import io.github.gregtechintergalactical.gtrubber.GTRubber;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemModelProvider;
import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.registration.IModelProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.WoodButtonBlock;
import net.minecraft.world.level.material.Material;

public class BlockRubberButton extends WoodButtonBlock implements IAntimatterObject, IModelProvider {
    public BlockRubberButton() {
        super(Properties.of(Material.DECORATION).noCollission().strength(0.5f).sound(SoundType.WOOD));
        AntimatterAPI.register(BlockRubberButton.class, this);
    }

    @Override
    public String getDomain() {
        return GTRubber.ID;
    }

    @Override
    public String getId() {
        return "rubber_button";
    }

    @Override
    public void onItemModelBuild(ItemLike item, AntimatterItemModelProvider prov) {
        prov.getBuilder(item).parent(new ResourceLocation("block/button_inventory")).texture("texture", new ResourceLocation(GTRubber.ID, "block/tree/rubber_planks"));
    }

    @Override
    public void onBlockModelBuild(Block block, AntimatterBlockStateProvider prov) {
        prov.buttonBlock(this, new ResourceLocation(GTRubber.ID, "block/tree/rubber_planks"));
    }
}
