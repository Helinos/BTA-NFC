package net.helinos.btanfc.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.core.block.entity.TileEntityFurnace;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.Items;

// Prevent cheese from having too much iron
// This is not the way I wanted to do this but, alas, here we are
@Mixin(value = TileEntityFurnace.class, remap = false)
public abstract class TileEntityFurnaceMixin {
    @Shadow
    private ItemStack[] furnaceItemStacks;
    private boolean isMilkBucket = false;
    
    @Inject(method = "smeltItem", at = @At("HEAD"))
    private void checkIfMilkBucket(CallbackInfo callbackInfo) {
        isMilkBucket = this.furnaceItemStacks[0].itemID == Items.BUCKET_MILK.id;
    }

    @Inject(method = "smeltItem", at = @At("TAIL"))
    private void dontConsumeMilkBucket(CallbackInfo callbackInfo) {
        if (isMilkBucket && this.furnaceItemStacks[0] == null) {
            this.furnaceItemStacks[0] = Items.BUCKET.getDefaultStack();
        }
    }
}
