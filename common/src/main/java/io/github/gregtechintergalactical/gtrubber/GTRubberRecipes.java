package io.github.gregtechintergalactical.gtrubber;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.data.ForgeCTags;
import muramasa.antimatter.datagen.builder.AntimatterCookingRecipeBuilder;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.event.CraftingEvent;
import muramasa.antimatter.util.TagUtils;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

import static io.github.gregtechintergalactical.gtrubber.GTRubberData.SAP_BAG;
import static com.google.common.collect.ImmutableMap.of;
import static muramasa.antimatter.data.AntimatterDefaultTools.SAW;
import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;

public class GTRubberRecipes {
    public static void onCraftingEvent(CraftingEvent event){
        event.addLoader(GTRubberRecipes::addRecipes);
    }

    public static void addRecipes(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider){
        if (!AntimatterAPI.isModLoaded("gti") && !AntimatterAPI.isModLoaded("gt4r")) {
            AntimatterCookingRecipeBuilder.smeltingRecipe(Ingredient.of(GTRubberData.StickyResin), DUST.get(GTRubberData.RUBBER, 1), 0.1f, 200).addCriterion("has_resin", provider.hasSafeItem(GTRubberData.StickyResin)).build(consumer, "resin_to_rubber");
        }
        Item lumber = AntimatterAPI.isModLoaded("tfc") ? AntimatterAPI.get(Item.class, "rubber_lumber", GTRubber.ID) : GTRubberData.RUBBER_PLANKS.asItem();
        if (!AntimatterAPI.isModLoaded("gt4r") && !AntimatterAPI.isModLoaded("tfc")){
            provider.shapeless(consumer, GTRubber.ID, "", "rubber_wood", "has_rubber_logs", provider.hasSafeItem(GTRubberData.RUBBER_LOGS), new ItemStack(GTRubberData.RUBBER_PLANKS, 4), GTRubberData.RUBBER_LOGS);
        } else if (AntimatterAPI.isModLoaded("tfc")){
            provider.shapeless(consumer, GTRubber.ID, "", "rubber_wood", "has_rubber_logs", provider.hasSafeItem(GTRubberData.RUBBER_LOGS), new ItemStack(lumber, 8), GTRubberData.RUBBER_LOGS, TagUtils.getItemTag(new ResourceLocation("tfc", "saws")));
            provider.addItemRecipe(consumer, "rubber_wood", "has_rubber_lumber", provider.hasSafeItem(lumber), GTRubberData.RUBBER_PLANKS, of('R', lumber), "RR", "RR");
            provider.shapeless(consumer, GTRubber.ID, "rubber_lumber_from_planks", "rubber_wood", "has_rubber_planks", provider.hasSafeItem(GTRubberData.RUBBER_PLANKS), new ItemStack(lumber, 4), GTRubberData.RUBBER_PLANKS, TagUtils.getItemTag(new ResourceLocation("tfc", "saws")));
            provider.addStackRecipe(consumer, GTRubber.ID, "", "rubber_wood", "has_rubber_log", provider.hasSafeItem(GTRubberData.RUBBER_LOG), new ItemStack(AntimatterAPI.get(Item.class, "rubber_log_fence", GTRubber.ID), 8), of('R', GTRubberData.RUBBER_LOG, 'L', lumber), "RLR", "RLR");
        }
        provider.addStackRecipe(consumer, GTRubber.ID, "", "rubber_wood", "has_rubber_log", provider.hasSafeItem(GTRubberData.RUBBER_LOG), new ItemStack(GTRubberData.RUBBER_WOOD, 3), ImmutableMap.of('R', GTRubberData.RUBBER_LOG), "RR", "RR");
        provider.addStackRecipe(consumer, GTRubber.ID, "", "rubber_wood", "has_stripped_rubber_log", provider.hasSafeItem(GTRubberData.STRIPPED_RUBBER_LOG), new ItemStack(GTRubberData.STRIPPED_RUBBER_WOOD, 3), ImmutableMap.of('R', GTRubberData.STRIPPED_RUBBER_LOG), "RR", "RR");
        provider.addStackRecipe(consumer, GTRubber.ID, "", "rubber_wood", "has_rubber_planks", provider.hasSafeItem(GTRubberData.RUBBER_WOOD), new ItemStack(GTRubberData.RUBBER_SIGN, 3), ImmutableMap.of('R', lumber, 'S', TagUtils.getForgelikeItemTag("rods/wooden")), "RRR", "RRR", " S ");
        int fenceAmount = AntimatterAPI.isModLoaded("tfc") ? 8 : 3, fenceGateAmount = AntimatterAPI.isModLoaded("tfc") ? 2 : 1;
        Object stick = AntimatterAPI.isModLoaded("tfc") ? lumber : TagUtils.getForgelikeItemTag("rods/wooden");
        provider.addStackRecipe(consumer, GTRubber.ID, "", "rubber_wood", "has_rubber_planks", provider.hasSafeItem(GTRubberData.RUBBER_WOOD), new ItemStack(GTRubberData.RUBBER_FENCE, fenceAmount), ImmutableMap.of('R', GTRubberData.RUBBER_PLANKS, 'S', stick), "RSR", "RSR");
        provider.addStackRecipe(consumer, GTRubber.ID, "", "rubber_wood", "has_rubber_planks", provider.hasSafeItem(GTRubberData.RUBBER_WOOD), new ItemStack(GTRubberData.RUBBER_FENCE_GATE, fenceGateAmount), ImmutableMap.of('R', GTRubberData.RUBBER_PLANKS, 'S', stick), "SRS", "SRS");
        provider.addStackRecipe(consumer, GTRubber.ID, "", "rubber_wood", "has_rubber_planks", provider.hasSafeItem(GTRubberData.RUBBER_WOOD), new ItemStack(GTRubberData.RUBBER_PRESSURE_PLATE, 1), ImmutableMap.of('R', lumber), "RR");
        provider.addStackRecipe(consumer, GTRubber.ID, "", "rubber_wood", "has_rubber_planks", provider.hasSafeItem(GTRubberData.RUBBER_WOOD), new ItemStack(GTRubberData.RUBBER_SLAB, 6), ImmutableMap.of('R', GTRubberData.RUBBER_PLANKS), "RRR");
        provider.addStackRecipe(consumer, GTRubber.ID, "", "rubber_wood", "has_rubber_planks", provider.hasSafeItem(GTRubberData.RUBBER_WOOD), new ItemStack(GTRubberData.RUBBER_STAIRS, 4), ImmutableMap.of('R', GTRubberData.RUBBER_PLANKS), "R  ", "RR ", "RRR");
        int doorAmount = AntimatterAPI.isModLoaded("tfc") ? 2 : 3, trapdoorAmount = AntimatterAPI.isModLoaded("tfc") ? 3 : 2;
        provider.addStackRecipe(consumer, GTRubber.ID, "", "rubber_wood", "has_rubber_planks", provider.hasSafeItem(GTRubberData.RUBBER_WOOD), new ItemStack(GTRubberData.RUBBER_DOOR, doorAmount), ImmutableMap.of('R', lumber), "RR", "RR", "RR");
        provider.addStackRecipe(consumer, GTRubber.ID, "", "rubber_wood", "has_rubber_planks", provider.hasSafeItem(GTRubberData.RUBBER_WOOD), new ItemStack(GTRubberData.RUBBER_TRAPDOOR, trapdoorAmount), ImmutableMap.of('R', lumber), "RRR", "RRR");
        provider.addStackRecipe(consumer, GTRubber.ID, "", "rubber_wood", "has_rubber_planks", provider.hasSafeItem(GTRubberData.RUBBER_WOOD), new ItemStack(GTRubberData.RubberBoat), ImmutableMap.of('R', GTRubberData.RUBBER_PLANKS), "R R", "RRR");
        provider.shapeless(consumer, GTRubber.ID, "", "rubber_wood", "has_rubber_planks", provider.hasSafeItem(GTRubberData.RUBBER_PLANKS), new ItemStack(GTRubberData.RUBBER_BUTTON), GTRubberData.RUBBER_PLANKS);
        provider.addStackRecipe(consumer, GTRubber.ID, "sapbag", "blocks", "has_saw", provider.hasSafeItem(SAW.getTag()),
                new ItemStack(SAP_BAG), of('L', ForgeCTags.LEATHER, 'S', SAW.getTag(), 's', Items.STICK), "sss", "LSL", "LLL");
        provider.addStackRecipe(consumer, GTRubber.ID, "torch", "torches", "has_sticky_resin", provider.hasSafeItem(GTRubberData.StickyResin), new ItemStack(Items.TORCH, 4), of('S', GTRubberData.StickyResin, 'R', Items.STICK), "S", "R");
    }

}
