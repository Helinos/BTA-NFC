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
        ItemStack itemStack = new ItemStack(this);
        itemStack.setMetadata(metadata);
        
        // Awful hack but I'm too exhausted to care
        if (this.key == "tile.btanfc.window") {
            switch (dropCause) {
                case SILK_TOUCH:
                case PICK_BLOCK:
                    return new ItemStack[]{new ItemStack( itemStack )};
                default:
                    return null;
            }
        }

        return dropCause != EnumDropCause.IMPROPER_TOOL ? new ItemStack[] { itemStack } : null;
    }
}
