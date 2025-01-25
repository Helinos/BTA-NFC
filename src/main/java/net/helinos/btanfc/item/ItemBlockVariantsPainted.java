package net.helinos.btanfc.item;

import net.minecraft.core.block.Block;
import net.minecraft.core.item.ItemDye;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.ItemBlock;

public class ItemBlockVariantsPainted extends ItemBlock {
    private String[] variantList;
    
    public ItemBlockVariantsPainted(Block block, String[] variantList) {
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
        int metadata = itemstack.getMetadata();
        if (((metadata >> 7) & 1) == 1) {
            return super.getKey() + "." + this.variantList[metadata % 8] + "." + ItemDye.dyeColors[15 - (metadata >> 3 & 0b1111)];
        } else {
            return super.getKey() + "." + this.variantList[metadata % 8];
        }
    }
}
