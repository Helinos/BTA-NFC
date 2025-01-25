package net.helinos.btanfc.block.model;

import java.security.InvalidParameterException;
import java.util.Arrays;

import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.stitcher.IconCoordinate;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.item.ItemDye;
import net.minecraft.core.util.helper.Side;

public class BlockModelVariantsPainted<T extends Block> extends BlockModelStandard<T> {
    public final IconCoordinate[] textures = new IconCoordinate[256];

    public BlockModelVariantsPainted(Block block, String texturePrefix, String[] variantList) {
        super(block);

        if (variantList.length > 8) {
            throw new InvalidParameterException("Too many variants to create a BlockModelVariantsPainted.");
        }

        Arrays.fill(textures, BLOCK_TEXTURE_UNASSIGNED);
        
        for (int variantIndex = 0; variantIndex < variantList.length; variantIndex++) {
            String name = variantList[variantIndex];
            String underscore = name.length() == 0 ? "" : "_";
            textures[variantIndex] = TextureRegistry.getTexture("btanfc:block/" + texturePrefix + underscore + name);

            for(int colorIndex = 0; colorIndex < 16; colorIndex++) {
                int textureIndex = (1 << 7) | (colorIndex << 3) | variantIndex;
                textures[textureIndex] = TextureRegistry.getTexture("btanfc:block/" + texturePrefix + underscore + name + "_" + ItemDye.dyeColors[15 - colorIndex]);
            }
        }
    }

    public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int meta) {
        return textures[meta];
    }
}
