package net.helinos.btanfc.gui.guidebook.carpentry;

import java.util.ArrayList;
import java.util.Objects;

import net.helinos.btanfc.block.NFCBlocks;
import net.helinos.btanfc.interfaces.mixin.IRecipeRegistry;
import net.helinos.btanfc.recipe.entry.RecipeEntryCarpentry;
import net.minecraft.client.gui.guidebook.GuidebookPage;
import net.minecraft.client.gui.guidebook.SearchableGuidebookSection;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.SearchQuery;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.collection.Pair;
import net.minecraft.core.util.helper.MathHelper;

public class GuidebookSectionCarpentry extends SearchableGuidebookSection {
    private final ArrayList<GuidebookPage> pages = new ArrayList<GuidebookPage>();
    private Pair<String, ArrayList<GuidebookPage>> filteredPages = null;
    
    public GuidebookSectionCarpentry() {
        super("guidebook.section.carpentry", new ItemStack(NFCBlocks.CARPENTRY_WORKSTATION), 0xbc9862, 0xffd700);
        this.reloadRecipes();
    }

    public void reloadRecipes() {
      this.pages.clear();
      ArrayList<RecipeEntryCarpentry> allRecipes = new ArrayList<RecipeEntryCarpentry>(((IRecipeRegistry)Registries.RECIPES).getAllCarpentryRecipes());
      int totalRecipes = allRecipes.size();
      int totalPages = MathHelper.ceilInt(totalRecipes, 3);

      for(int page = 0; page < totalPages; ++page) {
         int recipeIndex = page * 3;
         ArrayList<RecipeEntryCarpentry> recipes = new ArrayList<RecipeEntryCarpentry>(allRecipes.subList(Math.min(recipeIndex, totalRecipes), Math.min(recipeIndex + 3, totalRecipes)));
         this.pages.add(new RecipePageCarpentry(this, recipes));
      }
   }

    @Override
    public ArrayList<GuidebookPage> searchPages(SearchQuery searchQuery) {
        if (this.filteredPages != null && Objects.equals(this.filteredPages.getLeft(), searchQuery.rawQuery))
            return this.filteredPages.getRight();

        ArrayList<RecipeEntryCarpentry> filteredRecipes = new ArrayList<>();
        ArrayList<RecipeEntryCarpentry> allRecipes = new ArrayList<RecipeEntryCarpentry>(((IRecipeRegistry)Registries.RECIPES).getAllCarpentryRecipes());

        for (RecipeEntryCarpentry recipe : allRecipes) {
            if (recipe.matchesQueryIgnoreExceptions(searchQuery)) {
                filteredRecipes.add(recipe);
            }
        }

        ArrayList<GuidebookPage> filteredPages = new ArrayList<GuidebookPage>();
        int filteredRecipeSize = filteredRecipes.size();
        int filteredPageCount = MathHelper.ceilInt(filteredRecipeSize, 3);

        for (int page = 0; page < filteredPageCount; ++page) {
            int recipeIndex = page * 3;
            ArrayList<RecipeEntryCarpentry> recipes = new ArrayList<>(filteredRecipes.subList(Math.min(recipeIndex, filteredRecipeSize), Math.min(recipeIndex + 3, filteredRecipeSize)));
            if (!recipes.isEmpty()) {
                filteredPages.add(new RecipePageCarpentry(this, recipes));
            }
        }

        this.filteredPages = Pair.of(searchQuery.rawQuery, filteredPages);
        return filteredPages;
    }

    @Override
    public ArrayList<Index> getIndices() {
        return null;
    }

    @Override
    public ArrayList<GuidebookPage> getPages() {
        return this.pages;
    }
}
