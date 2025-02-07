package net.helinos.btanfc.block.model;

import java.util.ArrayList;

import net.fabricmc.api.Environment;
import net.fabricmc.api.EnvType;
import net.minecraft.client.render.block.model.BlockModelStandard;
import net.minecraft.client.render.texture.stitcher.IconCoordinate;
import net.minecraft.client.render.texture.stitcher.TextureRegistry;
import net.minecraft.client.render.tessellator.Tessellator;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogic;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.util.phys.AABB;

@Environment(EnvType.CLIENT)
public class BlockModelAxisAlignedVariants<T extends BlockLogic> extends BlockModelStandard<T> {
    public final int variantCount;
    public final ArrayList<IconCoordinate[]> textures;

    public BlockModelAxisAlignedVariants(Block<T> block, String texturePrefix, String[] variantList) {
        super(block);
        this.variantCount = variantList.length;

        ArrayList<IconCoordinate[]> results = new ArrayList<>();
        for (String name : variantList) {
            String underscore = name.length() == 0 ? "" : "_";
            results.add(new IconCoordinate[] {
                TextureRegistry.getTexture("btanfc:block/" + texturePrefix + underscore + name + underscore + "side"),
                TextureRegistry.getTexture("btanfc:block/" + texturePrefix + underscore + name + underscore + "end")
            });
        }

        this.textures = results;
    }

    public boolean render(Tessellator tessellator, int x, int y, int z) {
        AABB bounds = this.block.getBlockBoundsFromState(renderBlocks.blockAccess, x, y, z);
        int metadata = renderBlocks.blockAccess.getBlockMetadata(x, y, z);
        int orientation = metadata >> 6;
        switch (orientation) {
            case 0:
                renderBlocks.uvRotateEast = 0;
                renderBlocks.uvRotateWest = 0;
                renderBlocks.uvRotateSouth = 0;
                renderBlocks.uvRotateNorth = 0;
                break;
            case 1:
                renderBlocks.uvRotateSouth = 1;
                renderBlocks.uvRotateNorth = 1;
                break;
            case 2:
                renderBlocks.uvRotateEast = 1;
                renderBlocks.uvRotateWest = 1;
                renderBlocks.uvRotateTop = 1;
                renderBlocks.uvRotateBottom = 1;
        }

        this.renderStandardBlock(tessellator, bounds, x, y, z);
        this.resetRenderBlocks();
        return true;
    }

    @Override
    public IconCoordinate getBlockTextureFromSideAndMetadata(Side side, int metadata) {
        int rotation = metadata >> 6;
        IconCoordinate sideTexture = textures.get(metadata % this.variantCount)[0];
        IconCoordinate endTexture = textures.get(metadata % this.variantCount)[1];
        switch (rotation) {
            case 0:
                switch(side) {
                    case TOP:
                    case BOTTOM:
                        return endTexture;
                    default:
                        return sideTexture;
                }
            case 1:
                switch(side) {
                    case NORTH:
                    case SOUTH:
                        return endTexture;
                    default:
                        return sideTexture;
                }
            case 2:
                switch(side) {
                    case EAST:
                    case WEST:
                        return endTexture;
                    default:
                        return sideTexture;
                }
            default:
            return BLOCK_TEXTURE_UNASSIGNED;
        }
        
    }
}
