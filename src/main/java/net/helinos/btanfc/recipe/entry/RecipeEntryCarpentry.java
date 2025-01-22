package net.helinos.btanfc.recipe.entry;

import java.util.ArrayList;

import net.helinos.btanfc.container.inventory.InventoryCarpentry;
import net.minecraft.core.data.registry.recipe.RecipeEntryBase;
import net.minecraft.core.data.registry.recipe.RecipeSymbol;
import net.minecraft.core.item.ItemStack;

public class RecipeEntryCarpentry extends RecipeEntryBase<RecipeSymbol[], ItemStack[], Object> {
    public RecipeEntryCarpentry(RecipeSymbol[] input, ItemStack[] outputs) {
        super(input, outputs, (Object) null);
    }

    public boolean matches(InventoryCarpentry inputInventory) {
        RecipeSymbol symbol = this.getInput()[0];
        ItemStack inputItem = inputInventory.getStackInSlot(0);

        if (inputItem == null || symbol == null) {
            return false;
        }

        if(!symbol.matches(inputItem)) {
            return false;
        }

        return true;
    }

    public ArrayList<ItemStack> getResult(InventoryCarpentry inventoryCarpentry) {
        ArrayList<ItemStack> results = new ArrayList<ItemStack>();
        for (ItemStack itemStack : this.getOutput()) {
            results.add(itemStack.copy());
        }
        return results;
    }
}
