package net.helinos.btanfc.container.inventory;

import net.helinos.btanfc.container.ContainerCarpentryWorkstation;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;

public class InventoryCarpentry implements IInventory {
    private ItemStack inputStack = null;
    private ContainerCarpentryWorkstation container;

    public InventoryCarpentry(ContainerCarpentryWorkstation container) {
        this.container = container;
    }

    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public ItemStack decrStackSize(int index, int decreaseBy) {
        if (this.inputStack != null) {
            ItemStack newItemStack;
            if (this.inputStack.stackSize <= decreaseBy) {
                newItemStack = this.inputStack;
                this.inputStack = null;
                this.container.onCraftMatrixChanged(this);
                return newItemStack;
            } else {
                newItemStack = this.inputStack.splitStack(decreaseBy);
                if (this.inputStack.stackSize <= 0) {
                    this.inputStack = null;
                }

                this.container.onCraftMatrixChanged(this);
                return newItemStack;
            }
        } else {
            return null;
        }
    }

    @Override
    public String getInvName() {
        return "Carpentry Workbench";
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public int getSizeInventory() {
        return 1;
    }

    @Override
    public ItemStack getStackInSlot(int i) {
        return this.inputStack;
    }

    @Override
    public void onInventoryChanged() {
        this.container.onCraftMatrixChanged(this);
    }

    @Override
    public void setInventorySlotContents(int i, ItemStack itemStack) {
        this.inputStack = itemStack;
        this.container.onCraftMatrixChanged(this);
    }

    @Override
    public void sortInventory() {
    }
}
