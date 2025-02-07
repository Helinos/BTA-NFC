package net.helinos.btanfc.mixin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;

import net.helinos.btanfc.recipe.entry.RecipeEntryCarpentry;
import net.helinos.btanfc.interfaces.mixin.IRecipeRegistry;
import net.helinos.btanfc.inventory.container.ContainerCarpentry;
import net.minecraft.core.data.registry.Registry;
import net.minecraft.core.data.registry.recipe.RecipeEntryBase;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeNamespace;
import net.minecraft.core.data.registry.recipe.RecipeRegistry;
import net.minecraft.core.item.ItemStack;

@Mixin(value = RecipeRegistry.class, remap = false)
public abstract class RecipeRegistryMixin extends Registry<RecipeNamespace> implements IRecipeRegistry {    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List<RecipeEntryCarpentry> getAllCarpentryRecipes() {
        ArrayList<RecipeEntryCarpentry> recipes = new ArrayList();

        for(RecipeNamespace recipeNamespace : this) {
            for(RecipeGroup<? extends RecipeEntryBase<?, ?, ?>> recipeGroup : recipeNamespace) {
                for (RecipeEntryBase<?, ?, ?> recipeEntry : recipeGroup) {
                    if (recipeEntry instanceof RecipeEntryCarpentry)
                        recipes.add((RecipeEntryCarpentry) recipeEntry);
                }
            }
        }

        return Collections.unmodifiableList(recipes);
    }
    
    @Override
    public ArrayList<ItemStack> findMatchingRecipe(ContainerCarpentry inventoryCarpentry) {
        for(int index = 0; index < this.getAllCarpentryRecipes().size(); ++index) {
            RecipeEntryCarpentry recipe = this.getAllCarpentryRecipes().get(index);
            ArrayList<ItemStack> results = recipe.matches(inventoryCarpentry);
            if (results != null) {
                return recipe.getResult(inventoryCarpentry);
            }
        }

        return null;
    }
}
