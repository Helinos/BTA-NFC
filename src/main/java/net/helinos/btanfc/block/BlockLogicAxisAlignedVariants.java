package net.helinos.btanfc.block;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.entity.TileEntity;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.Mob;
import net.minecraft.core.enums.EnumDropCause;
import net.minecraft.core.enums.PlacementMode;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.Axis;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class BlockLogicAxisAlignedVariants extends BlockLogicVariants {
    public BlockLogicAxisAlignedVariants(Block<?> block, Material material) {
        super(block, material);
    }

    @Override
    public void onBlockPlacedByMob(World world, int x, int y, int z, @NotNull Side side, Mob mob, double xPlaced, double yPlaced) {
        Axis axis = mob.getPlacementDirection(side, PlacementMode.SIDE).getAxis();
        int metadata = world.getBlockMetadata(x, y, z);
        world.setBlockMetadataWithNotify(x, y, z, metadata | axisToMeta(axis));
    }

    @Override
    public void onBlockPlacedOnSide(World world, int x, int y, int z, @NotNull Side side, double xPlaced, double yPlaced) {
        Axis axis = side.getAxis();
        int metadata = world.getBlockMetadata(x, y, z);
        world.setBlockMetadataWithNotify(x, y, z, metadata | axisToMeta(axis));
    }

    public static int axisToMeta(Axis axis) {
        if (axis == Axis.X) {
            return 2 << 6;
        } else {
            return (axis == Axis.Z ? 1 : 0) << 6;
        }
    }

    public static Axis metaToAxis(int meta) {
        meta = meta >> 6;
        
        if (meta == 2) {
            return Axis.X;
        } else {
            return meta == 1 ? Axis.Z : Axis.Y;
        }
    }

    @Override
    public ItemStack[] getBreakResult(World world, EnumDropCause dropCause, int x, int y, int z, int metadata, TileEntity tileEntity) {
        ItemStack itemStack = new ItemStack(this);
        itemStack.setMetadata(metadata & 0b111111);
        return dropCause != EnumDropCause.IMPROPER_TOOL ? new ItemStack[] { itemStack } : null;
    }
}
