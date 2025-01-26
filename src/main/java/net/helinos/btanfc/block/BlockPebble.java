package net.helinos.btanfc.block;

import java.util.Random;

import net.helinos.btanfc.entity.EntityFallingPebble;
import net.minecraft.core.block.BlockSand;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.world.World;

public class BlockPebble extends BlockSand {
    public BlockPebble(String key, int id) {
        super(key, id);
    }

    @Override
    public void updateTick(World world, int x, int y, int z, Random random) {
        if(random.nextInt(8) == 0) {
            int metadata = world.getBlockMetadata(x, y, z);
            this.tryToFall(world, x, y, z, metadata);            
        }
    }

    private void tryToFall(World world, int x, int y, int z, int metadata) {
        if (canFallBelow(world, x, y - 1, z) && y >= 0) {
            int range = 32;
            if (!fallInstantly && world.areBlocksLoaded(x - range, y - range, z - range, x + range, y + range, z + range)) {
                EntityFallingPebble entityfallingsand = new EntityFallingPebble(world, x, y, z, this.id);
                world.entityJoinedWorld(entityfallingsand);
            } else {
                world.setBlockWithNotify(x, y, z, 0);

                while(canFallBelow(world, x, y - 1, z) && y > 0) {
                    y--;
                }

                if (y > 0) {
                    world.setBlockAndMetadataWithNotify(x, y, z, this.id, metadata);
                }
            }
        }
    }


    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int metadata, TileEntity tileEntity) {
        ItemStack itemStack = new ItemStack(this);
        itemStack.setMetadata(metadata);
        return dropCause != EnumDropCause.IMPROPER_TOOL ? new ItemStack[] { itemStack } : null;
    }
}
