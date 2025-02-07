package net.helinos.btanfc.recipe.entry;

import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.container.ContainerCrafting;

@FunctionalInterface
public interface RecipeGroupLambda {
    ItemStack newItemStack(ContainerCrafting containerCrafting, int stackSize);
}
