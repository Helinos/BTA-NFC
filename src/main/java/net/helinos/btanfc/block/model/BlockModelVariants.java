package net.helinos.btanfc.block.model;

import java.util.Arrays;
import java.util.stream.Collectors;

import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.util.helper.Side;

public class BlockModelVariants<T extends Block> extends BlockModelStandard<T> {
    public final int variantCount;
    public final IconCoordinate[] textures;

    public BlockModelVariants(Block block, String texturePrefix, String[] variantList) {
        super(block);
        
        this.variantCount = variantList.length;
        this.textures = Arrays.asList(variantList).stream()
            .map(name -> {
                String underscore = name.length() == 0 ? "" : "_";
                return TextureRegistry.getTexture("btanfc:block/" + texturePrefix + underscore + name);
            }).collect(Collectors.toList())
            .toArray(new IconCoordinate[variantList.length]);
    }

    public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int meta) {
        return textures[meta % this.variantCount];
    }
}
