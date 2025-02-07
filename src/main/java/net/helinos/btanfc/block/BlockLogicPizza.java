package net.helinos.btanfc.block;

import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicEdible;
import net.minecraft.core.item.Item;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.WorldSource;

public class BlockLogicPizza extends BlockLogicEdible {
    public BlockLogicPizza(Block<?> block, int healAmount, @NotNull Supplier<Item> dropItemSupplier) {
        super(block, 6, healAmount, dropItemSupplier);
    }

    public AABB getBlockBoundsFromState(WorldSource worldSource, int x, int y, int z) {
        int metadata = worldSource.getBlockMetadata(x, y, z);
        double onePixel = 1.0 / 16.0;
        double edibleSidePadding = (1 + metadata * 2) / 16.0;

        return AABB.getTemporaryBB(edibleSidePadding, 0.0, onePixel, 1.0 - onePixel, onePixel, 1.0 - onePixel);
    }
}
