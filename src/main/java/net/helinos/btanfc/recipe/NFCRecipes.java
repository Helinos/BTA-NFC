package net.helinos.btanfc.recipe;

import net.helinos.btanfc.BTANFC;
import net.minecraft.core.block.Block;
import net.minecraft.core.data.DataLoader;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryFurnace;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class NFCRecipes implements RecipeEntrypoint {
    public static NFCRecipeNamespace RECIPE_NAMESPACE = new NFCRecipeNamespace();
    public static RecipeGroup<RecipeEntryCrafting<?,?>> WORKBENCH;
    public static RecipeGroup<RecipeEntryFurnace> FURNACE;
    
    @Override
    public void onRecipesReady() {
        resetGroups();
        registerNamespaces();
        load();
    }

    @Override
    public void initNamespaces() {
        resetGroups();
        registerNamespaces();
    }

    public void load() {
        DataLoader.loadRecipesFromFile("/assets/btanfc/recipes/workbench.json");
        DataLoader.loadRecipesFromFile("/assets/btanfc/recipes/furnace.json");
    }

    public void resetGroups() {
        RECIPE_NAMESPACE = new NFCRecipeNamespace();
        WORKBENCH = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.workbench)));
        FURNACE = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Block.furnaceStoneIdle)));
    }

    public void registerNamespaces() {
        RECIPE_NAMESPACE.register("workbench", WORKBENCH);
        RECIPE_NAMESPACE.register("furnace", FURNACE);
        Registries.RECIPES.register(BTANFC.MOD_ID, RECIPE_NAMESPACE);
    }
}
