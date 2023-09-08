package io.github.gregtechintergalactical.gtrubber.tfc;

import com.google.common.collect.ImmutableMap;
import io.github.gregtechintergalactical.gtrubber.GTRubber;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.block.BlockBasic;
import muramasa.antimatter.datagen.providers.AntimatterBlockStateProvider;
import muramasa.antimatter.datagen.providers.AntimatterItemModelProvider;
import muramasa.antimatter.registration.IAntimatterObject;
import muramasa.antimatter.registration.IModelProvider;
import muramasa.antimatter.registration.ITextureProvider;
import muramasa.antimatter.texture.Texture;
import net.dries007.tfc.common.blocks.ExtendedProperties;
import net.dries007.tfc.common.blocks.GroundcoverBlock;
import net.dries007.tfc.common.blocks.TFCMaterials;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

public class BlockRubberTwig extends GroundcoverBlock implements IModelProvider, IAntimatterObject {
    public BlockRubberTwig() {
        super(ExtendedProperties.of(TFCMaterials.GROUNDCOVER).strength(0.05F, 0.0F).sound(SoundType.WOOD).noCollission().flammableLikeWool(), TWIG, null);
        AntimatterAPI.register(BlockRubberTwig.class, this);
    }

    @Override
    public String getId() {
        return "rubber_twig";
    }

    @Override
    public String getDomain() {
        return GTRubber.ID;
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
