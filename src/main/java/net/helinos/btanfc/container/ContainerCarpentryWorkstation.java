package net.helinos.btanfc.container;

import java.util.ArrayList;
import java.util.List;

import net.helinos.btanfc.block.NFCBlocks;
import net.helinos.btanfc.container.inventory.InventoryCarpentry;
import net.helinos.btanfc.container.inventory.InventoryCarpentryResult;
import net.helinos.btanfc.container.slot.SlotCarpentryResult;
import net.helinos.btanfc.interfaces.mixin.IRecipeRegistry;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.data.registry.Registries;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.player.inventory.Container;
import net.minecraft.core.player.inventory.IInventory;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.player.inventory.slot.Slot;
import net.minecraft.core.world.World;

public class ContainerCarpentryWorkstation extends Container {
    public InventoryCarpentry inputSlot = new InventoryCarpentry(this);
    public InventoryCarpentryResult resultSlots = new InventoryCarpentryResult();
    private World world;
    private int x;
    private int y;
    private int z;
    
    public ContainerCarpentryWorkstation(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
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
                this.addSlot(new Slot(inventoryPlayer, column + row * 9 + 9, 8 + column * 18, 84 + row * 18));
            }
        }

        // Player Hotbar
        for(int column = 0; column < 9; ++column) {
            this.addSlot(new Slot(inventoryPlayer, column, 8 + column * 18, 142));
        }

        this.onCraftMatrixChanged(this.inputSlot);
    }

    @Override
    public List<Integer> getMoveSlots(InventoryAction inventoryAction, Slot slot, int target, EntityPlayer entityPlayer) {
        if (slot.id <= 12) {
            return this.getSlots(slot.id, 1, false);
        } else if (inventoryAction == InventoryAction.MOVE_SIMILAR && slot.id <= 48) {
            return this.getSlots(13, 36, false);
        } else if (slot.id <= 39) {
            return this.getSlots(13, 27, false);
        } else if (slot.id <= 48) {
            return this.getSlots(40, 9, false);
        }

        return null;
    }

    @Override
    public List<Integer> getTargetSlots(InventoryAction inventoryAction, Slot slot, int target, EntityPlayer entityPlayer) {
        if (slot.id <= 12) {
            return slot.id == 0 ? this.getSlots(13, 36, false) : this.getSlots(13, 36, true);
        } else if (target == 0) {
            return this.getSlots(0, 1, false);
        } else if (slot.id <= 39) {
            return this.getSlots(40, 9, false);
        } else if (slot.id <= 48) {
            return this.getSlots(13, 27, false);
        }

        return null;
    }

    @Override
    public boolean isUsableByPlayer(EntityPlayer entityPlayer) {
        if (this.world.getBlockId(x, y, z) != NFCBlocks.carpentryWorkstation.id) {
            return false;
        } else {
            return entityPlayer.distanceToSqr(x + 0.5, y + 0.5, z + 0.5) <= 64.0;
        }
    }

    @Override
    public void onCraftMatrixChanged(IInventory inputSlot) {
        ArrayList<ItemStack> results = ((IRecipeRegistry) Registries.RECIPES).findMatchingRecipe(this.inputSlot);
        
        if (results == null) {
            for (int index = 0; index < 12; index++) {
                this.resultSlots.setInventorySlotContents(index, null);
            }
        } else {
            for (int index = 0; index < results.size(); index++) {
                ItemStack result = results.get(index);
                this.resultSlots.setInventorySlotContents(index, result);
            }
        }
    }

    @Override
    public void onCraftGuiClosed(EntityPlayer player) {
        super.onCraftGuiClosed(player);
        
        ItemStack itemStack = this.inputSlot.getStackInSlot(0);
        if (itemStack != null) {
            this.storeOrDropItem(player, itemStack);
            player.world.playSoundAtEntity(null, player, "random.insert", 0.1F, 1.0F);
        }
    }

    @Override
    public void handleItemMove(InventoryAction action, Slot slot, int target, EntityPlayer player) {
        if (slot instanceof SlotCarpentryResult) {
            ItemStack itemToCarpent = slot.getStack();
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

                                slot.putStack(null);
                                slot.onPickupFromSlot(newStack);
                                this.onCraftMatrixChanged(player.inventory);
                                this.mergeItems(newStack, resultSlots);
                                if (newStack.stackSize > 0) {
                                    this.storeOrDropItem(player, newStack);
                                }

                                ItemStack inputItem = slot.getStack();
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
