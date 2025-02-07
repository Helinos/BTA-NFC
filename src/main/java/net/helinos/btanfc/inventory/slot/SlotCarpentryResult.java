package net.helinos.btanfc.inventory.slot;

import java.util.List;

import net.helinos.btanfc.interfaces.mixin.IRecipeRegistry;
import net.helinos.btanfc.inventory.container.ContainerCarpentry;
import net.helinos.btanfc.inventory.container.ContainerCarpentryResult;
import net.helinos.btanfc.recipe.entry.RecipeEntryCarpentry;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.slot.Slot;

public class SlotCarpentryResult extends Slot {
    private final ContainerCarpentry inputSlot;

    public SlotCarpentryResult(ContainerCarpentry inputInventory, ContainerCarpentryResult resultInventory, int id, int x, int y) {
        super(resultInventory, id, x, y);
        this.inputSlot = inputInventory;
    }

    @Override
    public boolean mayPlace(ItemStack itemStack) {
        return false;
    }

    @Override
    public void onTake(ItemStack itemStack) {
        List<RecipeEntryCarpentry> carpentryRecipes = ((IRecipeRegistry) Registries.RECIPES).getAllCarpentryRecipes();
        for (int index = 0; index < carpentryRecipes.size(); ++index) {
            RecipeEntryCarpentry recipe = carpentryRecipes.get(index);
            if (recipe.matches((ContainerCarpentry) this.inputSlot) != null) {
                ItemStack inputStack = inputSlot.getItem(0);
                if (inputStack != null) {
                    inputSlot.removeItem(0, 1);
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
