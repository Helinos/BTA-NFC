package net.helinos.btanfc.block;

import java.util.function.Supplier;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.block.BlockEdible;
import net.minecraft.core.item.Item;
import net.minecraft.core.world.WorldSource;

public class BlockPizza extends BlockEdible {
    public BlockPizza(String key, int id, int healAmount, @NotNull Supplier<Item> dropItemSupplier) {
        super(key, id, 6, healAmount, dropItemSupplier);
    }

    public void setBlockBoundsBasedOnState(WorldSource worldSource, int x, int y, int z) {
        int metadata = worldSource.getBlockMetadata(x, y, z);
        double onePixel = 1.0 / 16.0;
        double edibleSidePadding = (1 + metadata * 2) / 16.0;

        this.setBlockBounds(edibleSidePadding, 0.0, onePixel, 1.0 - onePixel, onePixel, 1.0 - onePixel);
    }
}
