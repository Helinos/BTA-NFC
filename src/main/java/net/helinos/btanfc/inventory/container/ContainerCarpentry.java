package net.helinos.btanfc.inventory.container;

import javax.annotation.Nullable;

import net.helinos.btanfc.inventory.menu.MenuCarpentry;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.container.Container;

public class ContainerCarpentry implements Container {
    private ItemStack inputStack = null;
    private MenuCarpentry menu;

    public ContainerCarpentry(MenuCarpentry container) {
        this.menu = container;
    }

    @Override
    public int getContainerSize() {
        return 1;
    }

    @Override
    public @Nullable ItemStack getItem(int index) {
        return this.inputStack;
    }

    @Override
    public String getNameTranslationKey() {
        return "container.carpentry.name";
    }

    @Override
    public @Nullable ItemStack removeItem(int index, int decreaseBy) {
        if (this.inputStack != null) {
            ItemStack newItemStack;
            if (this.inputStack.stackSize <= decreaseBy) {
                newItemStack = this.inputStack;
                this.inputStack = null;
                this.menu.slotsChanged(this);
                return newItemStack;
            } else {
                newItemStack = this.inputStack.splitStack(decreaseBy);
                if (this.inputStack.stackSize <= 0) {
                    this.inputStack = null;
                }

                this.menu.slotsChanged(this);
                return newItemStack;
            }
        } else {
            return null;
        }
    }

    @Override
    public void setItem(int index, @Nullable ItemStack itemStack) {
        this.inputStack = itemStack;
        this.menu.slotsChanged(this);
    }

    @Override
    public int getMaxStackSize() {
        return 64;
    }

    @Override
    public void setChanged() {
        this.menu.slotsChanged(this);
    }

    @Override
    public boolean stillValid(Player player) {
        return true;
    }

    @Override
    public void sortContainer() {
    }
}
