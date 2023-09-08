package io.github.gregtechintergalactical.gtrubber.tfc.tfc;

import com.google.common.collect.ImmutableMap;
import io.github.gregtechintergalactical.gtrubber.GTRubber;
import muramasa.antimatter.block.BlockBasic;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemModelProvider;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

public class BlockRubberTwig extends BlockBasic {
    public BlockRubberTwig(Properties properties) {
        super(GTRubber.ID, "rubber_twig", properties);
    }

    @Override
    public void onBlockModelBuild(Block block, AntimatterBlockStateProvider prov) {
        prov.getBuilder(block).model("tfc:block/groundcover/twig", ImmutableMap.of("side", new Texture(GTRubber.ID,"block/tree/rubber_log"), "top", new Texture(GTRubber.ID,"block/tree/rubber_log_end")));
    }

    @Override
    public void onItemModelBuild(ItemLike item, AntimatterItemModelProvider prov) {
        prov.tex(item, new Texture(GTRubber.ID, "item/basic/rubber_twig"));
    }
}
