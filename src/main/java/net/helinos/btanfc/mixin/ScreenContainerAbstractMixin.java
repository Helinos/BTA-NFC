package net.helinos.btanfc.mixin;

import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.helinos.btanfc.inventory.slot.SlotCarpentryResult;
import net.minecraft.client.gui.Screen;
import net.minecraft.client.gui.container.ScreenContainerAbstract;
import net.minecraft.core.InventoryAction;
import net.minecraft.core.player.inventory.menu.MenuAbstract;
import net.minecraft.core.player.inventory.slot.Slot;

@Mixin(value = ScreenContainerAbstract.class, remap = false)
public abstract class ScreenContainerAbstractMixin extends Screen {    
    private Slot slot;
    
    @Redirect(method = "clickInventory(III)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/player/inventory/menu/MenuAbstract;getSlot(I)Lnet/minecraft/core/player/inventory/slot/Slot;"))
    private Slot getSlot(MenuAbstract menu, int index) {
        try {
            this.slot = (Slot) menu.slots.get(index);
            return this.slot;
        } catch (Exception e) {
            return null;
        }
    }
    
    @ModifyArg(method = "clickInventory(III)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/controller/PlayerController;handleInventoryMouseClick(ILnet/minecraft/core/InventoryAction;[ILnet/minecraft/core/entity/player/Player;)Lnet/minecraft/core/item/ItemStack;", ordinal = 11), index = 1)
    private InventoryAction shiftClickFromCarpentryWorkstation(InventoryAction action) {
        boolean shiftPressed = Keyboard.isKeyDown(42) || Keyboard.isKeyDown(54);
        boolean ctrlPressed = Keyboard.isKeyDown(29) || Keyboard.isKeyDown(157);

        if (slot instanceof SlotCarpentryResult) {
            if (this.mc.gameSettings.swapCraftingButtons.value) {
                if (shiftPressed && ctrlPressed) {
                    action = InventoryAction.MOVE_SIMILAR;
                } else if (shiftPressed) {
                    action = InventoryAction.MOVE_SINGLE_ITEM;
                } else if (ctrlPressed) {
                    action = InventoryAction.MOVE_STACK;
                }
            } else if (shiftPressed && ctrlPressed) {
                action = InventoryAction.MOVE_SIMILAR;
            } else if (shiftPressed) {
                action = InventoryAction.MOVE_STACK;
            } else if (ctrlPressed) {
                action = InventoryAction.MOVE_SINGLE_ITEM;
            }
        }
        
        return action;
    }
}
