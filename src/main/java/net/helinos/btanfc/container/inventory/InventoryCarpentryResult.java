package net.helinos.btanfc.container.inventory;

import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.IInventory;

public class InventoryCarpentryResult implements IInventory {
    private ItemStack[] resultStacks = new ItemStack[12];


    @Override
    public boolean canInteractWith(EntityPlayer entityPlayer) {
        return true;
    }

    @Override
    public ItemStack decrStackSize(int index, int decreaseBy) {
        if (this.resultStacks[index] != null) {
            ItemStack itemstack = this.resultStacks[index];
            this.resultStacks[index] = null;
            return itemstack;
        }
        return null;
    }

    @Override
    public String getInvName() {
        return "Result";
    }

    @Override
    public int getInventoryStackLimit() {
        return 64;
    }

    @Override
    public int getSizeInventory() {
        return 12;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return this.resultStacks[index];
    }

    @Override
    public void onInventoryChanged() {
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack itemStack) {
        this.resultStacks[index] = itemStack;
    }

    @Override
    public void sortInventory() {
    }
}
