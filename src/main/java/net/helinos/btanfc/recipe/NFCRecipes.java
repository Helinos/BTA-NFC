package net.helinos.btanfc.recipe;

import net.helinos.btanfc.BTANFC;
import net.helinos.btanfc.block.NFCBlocks;
import net.helinos.btanfc.recipe.entry.RecipeEntryCarpentry;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.data.DataLoader;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCrafting;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryFurnace;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryTrommel;
import net.minecraft.core.item.ItemStack;
import turniplabs.halplibe.util.RecipeEntrypoint;

public class NFCRecipes implements RecipeEntrypoint {
    public static NFCRecipeNamespace RECIPE_NAMESPACE = new NFCRecipeNamespace();
    public static RecipeGroup<RecipeEntryCrafting<?,?>> WORKBENCH;
    public static RecipeGroup<RecipeEntryFurnace> FURNACE;
    public static RecipeGroup<RecipeEntryTrommel> TROMMEL;
    public static RecipeGroup<RecipeEntryCarpentry> CARPENTRY;
    
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
        DataLoader.loadRecipesFromFile("/assets/btanfc/recipes/trommel.json");
        new CarpentryRecipes().addRecipes(CARPENTRY);
        new GroupRecipes().addRecipes(WORKBENCH);
    }

    public void resetGroups() {
        RECIPE_NAMESPACE = new NFCRecipeNamespace();
        WORKBENCH = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Blocks.WORKBENCH)));
        FURNACE = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Blocks.FURNACE_STONE_IDLE)));
        TROMMEL = new RecipeGroup<>(new RecipeSymbol(new ItemStack(Blocks.TROMMEL_ACTIVE)));
        CARPENTRY = new RecipeGroup<>(new RecipeSymbol(new ItemStack(NFCBlocks.CARPENTRY_WORKSTATION)));
    }

    public void registerNamespaces() {
        RECIPE_NAMESPACE.register("workbench", WORKBENCH);
        RECIPE_NAMESPACE.register("furnace", FURNACE);
        RECIPE_NAMESPACE.register("carpentry", CARPENTRY);
        RECIPE_NAMESPACE.register("trommel", TROMMEL);
        Registries.RECIPES.register(BTANFC.MOD_ID, RECIPE_NAMESPACE);
    }
}
