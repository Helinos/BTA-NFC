package net.helinos.btanfc.recipe.entry;

import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.data.registry.recipe.entry.RecipeEntryCraftingShaped;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.container.ContainerCrafting;

public class RecipeEntryCraftingGroup extends RecipeEntryCraftingShaped{
    RecipeGroupLambda lambda;

    public RecipeEntryCraftingGroup(int recipeWidth, int recipeHeight, RecipeSymbol[] input, ItemStack output, RecipeGroupLambda lambda) {
        super(recipeWidth, recipeHeight, input, output);
        this.lambda = lambda;
    }

    public RecipeEntryCraftingGroup(int recipeWidth, int recipeHeight, RecipeSymbol[] input, ItemStack output, boolean consumeContainerItem, RecipeGroupLambda lambda) {
        super(recipeWidth, recipeHeight, input, output, consumeContainerItem);
        this.lambda = lambda;
    }
  
    public RecipeEntryCraftingGroup() {
        super();
    }

    @Override
    public ItemStack getCraftingResult(ContainerCrafting containerCrafting) {
        ItemStack outputItemStack = super.getOutput().copy();
        return lambda.newItemStack(containerCrafting, outputItemStack.stackSize);
    }
}
