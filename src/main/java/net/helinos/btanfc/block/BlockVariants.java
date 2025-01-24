package net.helinos.btanfc.block;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class BlockVariants extends Block {
    public BlockVariants(String key, int id, Material material) {
        super(key, id, material);
    }

    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int metadata, TileEntity tileEntity) {
        return getBreakResult(new ItemStack(this), world, dropCause, metadata);
    }

    public static ItemStack[] getBreakResult(ItemStack itemStack, World world, EnumDropCause dropCause, int metadata) {
        itemStack.setMetadata(metadata & 0b111111);
        return dropCause != EnumDropCause.IMPROPER_TOOL ? new ItemStack[] { itemStack } : null;
    }
}
