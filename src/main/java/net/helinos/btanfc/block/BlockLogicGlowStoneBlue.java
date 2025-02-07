package net.helinos.btanfc.block;

import net.helinos.btanfc.item.NFCItems;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicGlowStone;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class BlockLogicGlowStoneBlue extends BlockLogicGlowStone {
    public BlockLogicGlowStoneBlue(Block<?> block) {
        super(block, Material.stone);
    }
   
    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int meta, TileEntity tileEntity) {
        switch (dropCause) {
            case PICK_BLOCK:
            case SILK_TOUCH:
                return new ItemStack[]{new ItemStack(this)};
            case PROPER_TOOL:
                return new ItemStack[]{new ItemStack(NFCItems.DUST_GLOWSTONE_BLUE, 4)};
            default:
                   return null;
        }
    }
}