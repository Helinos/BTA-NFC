package net.helinos.btanfc.inventory.menu;

import java.util.ArrayList;
import java.util.List;

import net.helinos.btanfc.block.NFCBlocks;
import net.helinos.btanfc.interfaces.mixin.IRecipeRegistry;
import net.helinos.btanfc.inventory.container.ContainerCarpentry;
import net.helinos.btanfc.inventory.container.ContainerCarpentryResult;
import net.helinos.btanfc.inventory.slot.SlotCarpentryResult;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.container.Container;
import net.minecraft.core.player.inventory.container.ContainerInventory;
import net.minecraft.core.player.inventory.menu.MenuAbstract;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.world.World;

public class MenuCarpentry extends MenuAbstract {
    public ContainerCarpentry inputSlot = new ContainerCarpentry(this);
    public ContainerCarpentryResult resultSlots = new ContainerCarpentryResult();
    private World world;
    private int x;
    private int y;
    private int z;
    
    public MenuCarpentry(ContainerInventory playerInventory, World world, int x, int y, int z) {
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;

        // Input slot
        this.addSlot(new Slot(this.inputSlot, 0, 36, 35));

        // Result Slots
        for(int row = 0; row < 3; ++row) {
            for(int column = 0; column < 4; ++column) {
                this.addSlot(new SlotCarpentryResult(this.inputSlot, this.resultSlots, column + row * 4, 98 + column * 18, 17 + row * 18));
            }
        }

        // Player Inventory
        for(int row = 0; row < 3; ++row) {
            for(int column = 0; column < 9; ++column) {
                this.addSlot(new Slot(playerInventory, column + row * 9 + 9, 8 + column * 18, 84 + row * 18));
            }
        }

        // Player Hotbar
        for(int column = 0; column < 9; ++column) {
            this.addSlot(new Slot(playerInventory, column, 8 + column * 18, 142));
        }

        this.slotsChanged(this.inputSlot);
    }

    @Override
    public List<Integer> getMoveSlots(InventoryAction inventoryAction, Slot slot, int target, Player player) {
        if (slot.index <= 12) {
            return this.getSlots(slot.index, 1, false);
        } else if (inventoryAction == InventoryAction.MOVE_SIMILAR && slot.index <= 48) {
            return this.getSlots(13, 36, false);
        } else if (slot.index <= 39) {
            return this.getSlots(13, 27, false);
        } else if (slot.index <= 48) {
            return this.getSlots(40, 9, false);
        }

        return null;
    }

    @Override
    public List<Integer> getTargetSlots(InventoryAction inventoryAction, Slot slot, int target, Player player) {
        if (slot.index <= 12) {
            return slot.index == 0 ? this.getSlots(13, 36, false) : this.getSlots(13, 36, true);
        } else if (target == 0) {
            return this.getSlots(0, 1, false);
        } else if (slot.index <= 39) {
            return this.getSlots(40, 9, false);
        } else if (slot.index <= 48) {
            return this.getSlots(13, 27, false);
        }

        return null;
    }

    @Override
    public boolean stillValid(Player player) {
        if (this.world.getBlockId(x, y, z) != NFCBlocks.CARPENTRY_WORKSTATION.id()) {
            return false;
        } else {
            return player.distanceToSqr(x + 0.5, y + 0.5, z + 0.5) <= 64.0;
        }
    }

    @Override
    public void slotsChanged(Container container) {
        ArrayList<ItemStack> results = ((IRecipeRegistry) Registries.RECIPES).findMatchingRecipe(this.inputSlot);
        
        if (results == null) {
            for (int index = 0; index < 12; index++) {
                this.resultSlots.setItem(index, null);
            }
        } else {
            for (int index = 0; index < results.size(); index++) {
                ItemStack result = results.get(index);
                this.resultSlots.setItem(index, result);
            }
        }
    }

    @Override
    public void onCraftGuiClosed(Player player) {
        super.onCraftGuiClosed(player);
        
        ItemStack itemStack = this.inputSlot.getItem(0);
        if (itemStack != null) {
            this.storeOrDropItem(player, itemStack);
            player.world.playSoundAtEntity(null, player, "random.insert", 0.1F, 1.0F);
        }
    }

    @Override
    public void handleItemMove(InventoryAction action, Slot slot, int target, Player player) {
        if (slot instanceof SlotCarpentryResult) {
            ItemStack itemToCarpent = slot.getItemStack();
            if (itemToCarpent != null) {
                Integer carpentCount = null;
                
                if (action == InventoryAction.MOVE_SINGLE_ITEM) {
                    carpentCount = 1;
                } else if (action == InventoryAction.MOVE_STACK) {
                    carpentCount = itemToCarpent.getMaxStackSize() / itemToCarpent.stackSize;
                } else if (action == InventoryAction.MOVE_SIMILAR) {
                    carpentCount = 65536;
                }

                if (carpentCount != null) {
                    List<Integer> resultSlots = this.getTargetSlots(action, slot, target, player);
                    if (resultSlots != null) {
                        for (int count = 0; count < carpentCount; count++) {
                            int freeSpace = this.getFreeSpace(itemToCarpent, resultSlots);
                            if (freeSpace >= itemToCarpent.stackSize) {
                                ItemStack newStack = itemToCarpent.copy();

                                slot.set(null);
                                slot.onTake(newStack);
                                this.slotsChanged(player.inventory);
                                this.mergeItems(newStack, resultSlots);
                                if (newStack.stackSize > 0) {
                                    this.storeOrDropItem(player, newStack);
                                }

                                ItemStack inputItem = slot.getItemStack();
                                if (inputItem == null) {
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        } else {
            super.handleItemMove(action, slot, target, player);
        }
    }
}
