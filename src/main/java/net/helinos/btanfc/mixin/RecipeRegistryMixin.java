package net.helinos.btanfc.mixin;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.spongepowered.asm.mixin.Mixin;

import net.helinos.btanfc.recipe.entry.RecipeEntryCarpentry;
import net.helinos.btanfc.container.inventory.InventoryCarpentry;
import net.helinos.btanfc.interfaces.mixin.IRecipeRegistry;
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
        Iterator recipeIterator = this.iterator();

        while(recipeIterator.hasNext()) {
            RecipeNamespace recipeNamespace = (RecipeNamespace) recipeIterator.next();
            Iterator namespaceIterator = recipeNamespace.iterator();

            while (namespaceIterator.hasNext()) {
                RecipeGroup recipeGroup = (RecipeGroup) namespaceIterator.next();
                Iterator groupIterator = recipeGroup.iterator();

                while(groupIterator.hasNext()) {
                    RecipeEntryBase<?, ?, ?> recipeEntry = (RecipeEntryBase) groupIterator.next();
                    if (recipeEntry instanceof RecipeEntryCarpentry) {
                        recipes.add((RecipeEntryCarpentry) recipeEntry);
                    }
                }
            }
        }

        return Collections.unmodifiableList(recipes);
    }
    
    @Override
    public ArrayList<ItemStack> findMatchingRecipe(InventoryCarpentry inventoryCarpentry) {
        for(int index = 0; index < this.getAllCarpentryRecipes().size(); ++index) {
            RecipeEntryCarpentry recipe = this.getAllCarpentryRecipes().get(index);
            if (recipe.matches(inventoryCarpentry)) {
                return recipe.getResult(inventoryCarpentry);
            }
        }

        return null;
    }
}
