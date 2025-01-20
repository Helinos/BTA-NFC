package net.helinos.btanfc.item;

import net.helinos.btanfc.block.NFCBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.ItemBlock;

public class ItemBlockWindow extends ItemBlock {
    public ItemBlockWindow(Block block) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
    }

    @Override
    public int getPlacedBlockMetadata(int metadata) {
        return metadata;
    }

    @Override
    public String getLanguageKey(ItemStack itemstack) {
        return super.getKey() + "." + NFCBlocks.windowTypes[itemstack.getMetadata() % NFCBlocks.windowTypes.length];
    }
}
