package net.helinos.btanfc.inventory.container;

import javax.annotation.Nullable;

import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.container.Container;

public class ContainerCarpentryResult implements Container {
    private ItemStack[] resultStacks = new ItemStack[12];

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public ItemStack removeItem(int index, int decreaseBy) {
        if (this.resultStacks[index] != null) {
            ItemStack itemstack = this.resultStacks[index];
            this.resultStacks[index] = null;
            return itemstack;
        }
        return null;
    }

    @Override
    public String getNameTranslationKey() {
        return "container.carpentry.result.name";
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }

    @Override
    public int getContainerSize() {
        return 12;
    }

    @Override
    public @Nullable ItemStack getItem(int index) {
        return this.resultStacks[index];
    }

    @Override
    public void setChanged() {
    }

    @Override
    public void setItem(int index, @Nullable ItemStack itemStack) {
        this.resultStacks[index] = itemStack;
    }

    @Override
    public void sortContainer() {
    }
}
