package net.helinos.btanfc.block;

import net.helinos.btanfc.item.NFCItems;
import net.minecraft.core.block.BlockGlowStone;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class BlockGlowStoneBlue extends BlockGlowStone {
   public BlockGlowStoneBlue(String key, int id) {
      super(key, id, Material.stone);
   }
   
   @Override
   public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int meta, TileEntity tileEntity) {
      switch (dropCause.ordinal()) {
         case 3:
         case 4:
            return new ItemStack[]{new ItemStack(this)};
         case 1:
         case 5:
         case 6:
            return null;
         default:
            return new ItemStack[]{new ItemStack(NFCItems.dustGlowstoneBlue, 4)};
      }
   }
}