package com.github.gregtechintergalactical.gtrubber;

import com.github.gregtechintergalactical.gtrubber.tree.BlockEntityRubberSign;
import com.github.gregtechintergalactical.gtrubber.tree.block.*;
import com.github.gregtechintergalactical.gtrubber.tree.item.ItemRubberSign;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.block.BlockBasic;
import muramasa.antimatter.item.ItemBasic;
import muramasa.antimatter.material.Material;
import muramasa.antimatter.texture.Texture;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.WoodType;

import static muramasa.antimatter.material.TextureSet.SHINY;

public class GTRubberData {


    public static final TagKey<Item> RUBBER_LOGS = TagUtils.getItemTag(new ResourceLocation(GTRubber.ID, "rubber_logs"));

    public static Material RUBBER = AntimatterAPI.register(Material.class, new Material(GTRubber.ID, "rubber", 0x000000, SHINY));

    public static WoodType RUBBER_WOOD_TYPE = new WoodType("gtrubber:rubber"){};



    public static final BlockRubberLeaves RUBBER_LEAVES = new BlockRubberLeaves(GTRubber.ID, "rubber_leaves");
    public static final BlockRubberLog RUBBER_LOG = new BlockRubberLog(GTRubber.ID, "rubber_log");
    public static final BlockRubberLog STRIPPED_RUBBER_LOG = new BlockRubberLog(GTRubber.ID, "stripped_rubber_log");
    public static final BlockRubberWood RUBBER_WOOD = new BlockRubberWood(GTRubber.ID, "rubber_wood");
    public static final BlockRubberWood STRIPPED_RUBBER_WOOD = new BlockRubberWood(GTRubber.ID, "stripped_rubber_wood");
    public static final BlockBasic RUBBER_PLANKS = new BlockBasic(GTRubber.ID, "rubber_planks", BlockBehaviour.Properties.of(net.minecraft.world.level.material.Material.WOOD).strength(2.0F).sound(SoundType.WOOD)){
        @Override
        public Texture[] getTextures() {
            return new Texture[]{new Texture(domain, "block/tree/rubber_planks")};
        }
    };

    public static final BlockRubberSign RUBBER_SIGN = new BlockRubberSign();
    public static final BlockRubberWallSign RUBBER_WALL_SIGN = new BlockRubberWallSign();
    public static final BlockRubberSapling RUBBER_SAPLING = new BlockRubberSapling(GTRubber.ID, "rubber_sapling");
    public static final BlockRubberButton RUBBER_BUTTON = new BlockRubberButton();
    public static final BlockRubberPressurePlate RUBBER_PRESSURE_PLATE = new BlockRubberPressurePlate();
    public static final BlockRubberDoor RUBBER_DOOR = new BlockRubberDoor();
    public static final BlockRubberTrapDoor RUBBER_TRAPDOOR = new BlockRubberTrapDoor();

    public static final BlockRubberSlab RUBBER_SLAB = new BlockRubberSlab();
    public static final BlockRubberStairs RUBBER_STAIRS = new BlockRubberStairs();

    public static final BlockRubberFence RUBBER_FENCE = new BlockRubberFence();
    public static final BlockRubberFenceGate RUBBER_FENCE_GATE = new BlockRubberFenceGate();

    public static final BlockEntityType<?> SIGN_BLOCK_ENTITY = BlockEntityType.Builder.of(BlockEntityRubberSign::new, RUBBER_SIGN, RUBBER_WALL_SIGN).build(null);

    public static ItemBasic<?> StickyResin = new ItemBasic<>(GTRubber.ID, "sticky_resin");
    public static void init() {
        AntimatterAPI.register(BlockEntityType.class, "rubber_sign", GTRubber.ID, SIGN_BLOCK_ENTITY);
    }
}
