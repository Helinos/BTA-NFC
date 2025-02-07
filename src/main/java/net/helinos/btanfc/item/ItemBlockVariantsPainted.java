package net.helinos.btanfc.item;

import javax.annotation.Nullable;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.item.block.ItemBlock;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class ItemBlockVariantsPainted<T extends BlockLogic> extends ItemBlock<T> {
    private String[] variantList;
    
    public ItemBlockVariantsPainted(Block<T> block, String[] variantList) {
        super(block);
        this.setMaxDamage(0);
        this.setHasSubtypes(true);
        this.variantList = variantList;
    }

    @Override
    public int getPlacedBlockMetadata(@Nullable Player player, ItemStack stack, World world, int x, int y, int z, Side side, double xPlaced, double yPlaced) {
        return stack.getMetadata();
    }

    @Override
    public String getLanguageKey(ItemStack itemstack) {
        int metadata = itemstack.getMetadata();
        if (((metadata >> 7) & 1) == 1) {
            return super.getKey() + "." + this.variantList[metadata % 8] + "." + DyeColor.colorFromBlockMeta(15 - (metadata >> 3 & 0b1111));
        } else {
            return super.getKey() + "." + this.variantList[metadata % 8];
        }
    }
}
