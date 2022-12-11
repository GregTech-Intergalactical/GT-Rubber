package com.github.gregtechintergalactical.gtrubber;

import com.google.common.collect.ImmutableMap;
import muramasa.antimatter.AntimatterAPI;
import muramasa.antimatter.datagen.builder.AntimatterCookingRecipeBuilder;
import muramasa.antimatter.datagen.providers.AntimatterRecipeProvider;
import muramasa.antimatter.event.CraftingEvent;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

import static muramasa.antimatter.data.AntimatterMaterialTypes.DUST;

public class GTRubberRecipes {
    public static void onCraftingEvent(CraftingEvent event){
        event.addLoader(GTRubberRecipes::addRecipes);
    }

    public static void addRecipes(Consumer<FinishedRecipe> consumer, AntimatterRecipeProvider provider){
        if (!AntimatterAPI.isModLoaded("gregtech") && !AntimatterAPI.isModLoaded("gt4r")) {
            AntimatterCookingRecipeBuilder.smeltingRecipe(Ingredient.of(GTRubberData.StickyResin), DUST.get(GTRubberData.RUBBER, 1), 0.1f, 200).addCriterion("has_resin", provider.hasSafeItem(GTRubberData.StickyResin)).build(consumer, "resin_to_rubber");
        }
        if (!AntimatterAPI.isModLoaded("gt4r")){
            provider.shapeless(consumer, GTRubber.ID, "rubber_planks", "has_rubber_logs", provider.hasSafeItem(GTRubberData.RUBBER_LOGS), new ItemStack(GTRubberData.RUBBER_PLANKS, 4), GTRubberData.RUBBER_LOGS);
        }
        provider.addStackRecipe(consumer, GTRubber.ID, "", "rubber_wood", "has_rubber_log", provider.hasSafeItem(GTRubberData.RUBBER_LOG), new ItemStack(GTRubberData.RUBBER_WOOD, 3), ImmutableMap.of('R', GTRubberData.RUBBER_LOG), "RR", "RR");
        provider.addStackRecipe(consumer, GTRubber.ID, "", "rubber_wood", "has_stripped_rubber_log", provider.hasSafeItem(GTRubberData.STRIPPED_RUBBER_LOG), new ItemStack(GTRubberData.STRIPPED_RUBBER_WOOD, 3), ImmutableMap.of('R', GTRubberData.STRIPPED_RUBBER_LOG), "RR", "RR");
    }

}
