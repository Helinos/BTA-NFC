package net.helinos.btanfc.block.model;

import java.security.InvalidParameterException;
import java.util.Arrays;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.DyeColor;
import net.minecraft.core.util.helper.Side;

@Environment(EnvType.CLIENT)
public class BlockModelVariantsPainted<T extends BlockLogic> extends BlockModelStandard<T> {
    public final IconCoordinate[] textures = new IconCoordinate[256];

    public BlockModelVariantsPainted(Block<T> block, String texturePrefix, String[] variantList) {
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
                textures[textureIndex] = TextureRegistry.getTexture("btanfc:block/" + texturePrefix + underscore + name + "_" + DyeColor.colorFromBlockMeta(15 - colorIndex).colorID.toLowerCase());
            }
        }
    }

    public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int meta) {
        return textures[meta];
    }
}
