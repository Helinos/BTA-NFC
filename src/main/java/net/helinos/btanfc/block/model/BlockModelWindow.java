package net.helinos.btanfc.block.model;

import java.util.Arrays;
import java.util.stream.Collectors;

import net.helinos.btanfc.block.NFCBlocks;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockGlass;
import net.minecraft.core.util.helper.Side;

public class BlockModelWindow extends BlockModelStandard<BlockGlass> {
    public static final IconCoordinate[] textures = Arrays.asList(NFCBlocks.windowTypes).stream()
            .map(name -> TextureRegistry.getTexture("btanfc:block/" + name)).collect(Collectors.toList())
            .toArray(new IconCoordinate[NFCBlocks.windowTypes.length]);

    public BlockModelWindow(Block block) {
        super(block);
    }

    public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int meta) {
        return textures[meta % 7];
    }
}
