package net.helinos.btanfc.recipe.entry;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import net.helinos.btanfc.inventory.container.ContainerCarpentry;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.data.registry.recipe.RecipeEntryBase;
import net.minecraft.core.data.registry.recipe.RecipeGroup;
import net.minecraft.core.data.registry.recipe.RecipeNamespace;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.SearchQuery;
import net.minecraft.core.item.ItemStack;

public class RecipeEntryCarpentry extends RecipeEntryBase<RecipeSymbol[], ItemStack[], Object> {
    public RecipeEntryCarpentry(RecipeSymbol[] input, ItemStack[] outputs) {
        super(input, outputs, (Object) null);
    }

    public ArrayList<ItemStack> matches(ContainerCarpentry inputInventory) {
        ArrayList<RecipeSymbol> symbols = new ArrayList<>();
        symbols.add(this.getInput()[0]);
        for (ItemStack output : this.getOutput()) {
            if (output.stackSize == 1) {
                symbols.add(new RecipeSymbol(output));
            }
        }

        ItemStack inputItem = inputInventory.getItem(0);

        for (RecipeSymbol symbol : symbols) {
            if (inputItem == null || symbol == null) {
                continue;
            }

            if(symbol.matches(inputItem)) {
                symbols.remove(symbol);

                return (ArrayList<ItemStack>) symbols.stream().map(s -> s.getStack()).collect(Collectors.toList());
            }
        }

        return null;
    }

    public ArrayList<ItemStack> getResult(ContainerCarpentry inventoryCarpentry) {
        ArrayList<ItemStack> results = new ArrayList<ItemStack>();
        ItemStack input = inventoryCarpentry.getItem(0);

        if (input == null) {
            return results;
        }

        for (ItemStack itemStack : this.getOutput()) {
            if (input.itemID == itemStack.itemID && input.getMetadata() == itemStack.getMetadata()) {
                results.add(this.getInput()[0].getStack().copy());
            }
        }

        for (ItemStack itemStack : this.getOutput()) {
            if (input.itemID != itemStack.itemID || input.getMetadata() != itemStack.getMetadata()) {
                results.add(itemStack.copy());
            }
        }

        return results;
    }
    
    public boolean matchesQueryIgnoreExceptions(SearchQuery searchQuery) {
        try {
            return this.matchesQuery(searchQuery);
        } catch (IllegalArgumentException | NullPointerException e) {
            return false;
        }
    }

    public boolean matchesQuery(SearchQuery searchQuery) {
        switch (searchQuery.mode) {
            case ALL:
                return (this.matchesRecipe(searchQuery) || this.matchesUsage(searchQuery)) && this.matchesScope(searchQuery);
            case RECIPE:
                return this.matchesRecipe(searchQuery) && this.matchesScope(searchQuery);
            case USAGE:
                return this.matchesUsage(searchQuery) && this.matchesScope(searchQuery);
        }

        return false;
    }

    public boolean matchesScope(SearchQuery searchQuery) {
        switch (searchQuery.scope.getLeft()) {
            case NONE:
                return true;
            case NAMESPACE:
                RecipeNamespace namespace = Registries.RECIPES.getItem(searchQuery.scope.getRight());
                return namespace == this.parent.getParent();
            case NAMESPACE_GROUP:
                RecipeGroup<?> group;
                try {
                    group = Registries.RECIPES.getGroupFromKey(searchQuery.scope.getRight());
                } catch (IllegalArgumentException e) {
                    group = null;
                }

                return group == this.parent;
        }

        return false;
   }

    public boolean matchesRecipe(SearchQuery searchQuery) {
        switch (searchQuery.query.getLeft()) {
            case NAME:
                for (ItemStack outputItem : this.getOutput()) {
                    if(searchQuery.strict) {
                        if (outputItem.getDisplayName().equalsIgnoreCase(searchQuery.query.getRight()))
                            return true;
                    } else {
                        if (outputItem.getDisplayName().toLowerCase().contains(((String)searchQuery.query.getRight()).toLowerCase()))
                            return true;
                    }
                }
            case GROUP:
                if (searchQuery.query.getRight() == "")
                    return false;
                ArrayList<ItemStack> groupStacks = new ArrayList<ItemStack>();
                for (ItemStack itemStack : (new RecipeSymbol(searchQuery.query.getRight())).resolve()) {
                    groupStacks.add(itemStack);
                }
                if (groupStacks.isEmpty()) {
                   return false;
                }
       
                boolean contains = false;
                for (ItemStack itemStack : this.getOutput()) {
                    contains |= groupStacks.contains(itemStack);
                }
                return contains;
        }

        return false;
    }

    public boolean matchesUsage(SearchQuery searchQuery) {
        RecipeSymbol[] symbols = this.getInput();
        RecipeSymbol symbol = symbols[0];

        if (symbol == null)
            return false;
        
        List<ItemStack> stacks = symbol.resolve();
        switch (searchQuery.query.getLeft()) {
            case NAME:
                ItemStack stack = stacks.get(0);
                if (searchQuery.strict) {
                    if (stack.getDisplayName().equalsIgnoreCase(searchQuery.query.getRight()))
                        return true;
                } else {
                    if (stack.getDisplayName().toLowerCase().contains((searchQuery.query.getRight()).toLowerCase()))
                        return true;
                }
            case GROUP:
                if (searchQuery.query.getRight() == "") {
                    return false;
                }
                
                List<ItemStack> groupStacks = (new RecipeSymbol(searchQuery.query.getRight())).resolve();
                if (groupStacks == null) {
                    return false;
                }

                if (stacks.stream().anyMatch(groupStacks::contains))
                    return true;
        }

        return false;
    }
}
