package net.helinos.btanfc.container.slot;

import java.util.List;

import net.helinos.btanfc.container.inventory.InventoryCarpentry;
import net.helinos.btanfc.container.inventory.InventoryCarpentryResult;
import net.helinos.btanfc.interfaces.mixin.IRecipeRegistry;
import net.helinos.btanfc.recipe.entry.RecipeEntryCarpentry;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.slot.Slot;

public class SlotCarpentryResult extends Slot {
    private final InventoryCarpentry inputSlot;

    public SlotCarpentryResult(InventoryCarpentry inputInventory, InventoryCarpentryResult resultInventory, int id, int x, int y) {
        super(resultInventory, id, x, y);
        this.inputSlot = inputInventory;
    }

    @Override
    public boolean canPutStackInSlot(ItemStack itemStack) {
        return false;
    }

    @Override
    public void onPickupFromSlot(ItemStack itemStack) {
        List<RecipeEntryCarpentry> carpentryRecipes = ((IRecipeRegistry) Registries.RECIPES).getAllCarpentryRecipes();
        for (int index = 0; index < carpentryRecipes.size(); ++index) {
            RecipeEntryCarpentry recipe = carpentryRecipes.get(index);
            if (recipe.matches((InventoryCarpentry) this.inputSlot) != null) {
                ItemStack inputStack = inputSlot.getStackInSlot(0);
                if (inputStack != null) {
                    inputSlot.decrStackSize(0, 1);
                }
            }
        }
    }

    @Override
    public boolean enableDragAndPickup() {
        return false;
    }

    @Override
    public boolean allowItemInteraction() {
        return false;
    }
}
