package net.helinos.btanfc.interfaces.mixin;

import java.util.ArrayList;
import java.util.List;

import net.helinos.btanfc.recipe.entry.RecipeEntryCarpentry;
import net.helinos.btanfc.container.inventory.InventoryCarpentry;
import net.minecraft.core.item.ItemStack;

public interface IRecipeRegistry {
    List<RecipeEntryCarpentry> getAllCarpentryRecipes();
    
    ArrayList<ItemStack> findMatchingRecipe(InventoryCarpentry inventoryCarpentry);
}
