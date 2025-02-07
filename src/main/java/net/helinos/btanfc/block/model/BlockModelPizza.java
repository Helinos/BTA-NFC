package net.helinos.btanfc.block.model;

import net.fabricmc.api.Environment;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.fabricmc.api.EnvType;

@Environment(EnvType.CLIENT)
public class BlockModelPizza<T extends BlockLogic> extends BlockModelStandard<T> {
    public BlockModelPizza(Block<T> block) {
        super(block);
        double onePixel = 1.0 / 16.0;
        this.withCustomItemBounds(onePixel, 0.0, onePixel, 1.0 - onePixel, onePixel, 1.0 - onePixel);
    }
}
