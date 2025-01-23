package net.helinos.btanfc.item;

import net.minecraft.core.block.Block;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.ItemBlock;

public class ItemBlockVariants extends ItemBlock {
    private String[] variantList;
    
    public ItemBlockVariants(Block block, String[] variantList) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.variantList = variantList;
    }

    @Override
    public int getPlacedBlockMetadata(int metadata) {
        return metadata;
    }

    @Override
    public String getLanguageKey(ItemStack itemstack) {
        return super.getKey() + "." + this.variantList[itemstack.getMetadata() % this.variantList.length];
    }
}
