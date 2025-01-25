package net.helinos.btanfc.recipe.entry;

import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.InventoryCrafting;

@FunctionalInterface
public interface RecipeGroupLambda {
    ItemStack newItemStack(InventoryCrafting inventoryCrafting, int stackSize);
}
