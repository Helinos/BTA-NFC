package net.helinos.btanfc.feature;

import java.util.Random;

import net.helinos.btanfc.block.NFCBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;

public class WorldFeatureScorchedSandPatch extends WorldFeature {
    public WorldFeatureScorchedSandPatch() {}
    
    @Override
    public boolean generate(World world, Random random, int x, int y, int z) {
        // Move down until we find an air block if we didn't start in an air block
        if (world.getBlockId(x, y, z) != 0) {
            while (world.getBlockId(x, y, z) != 0) {
                y -= 1;
                if (y < 0) {
                    return false;
                }
            }
        }

        // Move down until we find our first non-air block
        while (true) {
            y -= 1;

            if (y < 0) {
                return false;
            }

            int blockID = world.getBlockId(x, y, z);

            if (blockID == 0) {
                continue;
            }

            if (blockID != Block.netherrack.id) {
                return false;
            }

            break;
        }

        // Generate patch
        int heightAbove = 2;
        int heightBelow = 4;
        int minLength = 12;
        int maxLength = 24;
        int lengthX = minLength + random.nextInt(maxLength - minLength + 1);
        int lengthZ = minLength + random.nextInt(maxLength - minLength + 1);
        int centerX = x;
        int centerZ = z;
        int startX = x - lengthX / 2;
        int startZ = z - lengthZ / 2;
        int startY = y;

        for (y = startY + heightAbove; y >= startY - heightBelow; y--) {
            for (x = startX; x < startX + lengthX; x++) {
                for(z = startZ; z < startZ + lengthZ; z++) {
                    int weight;
                    if (y > startY) {
                        weight = Math.abs(startY - y) + 2;
                    } else {
                        weight = (startY - y + 1) * 2;
                    }
                    
                    int thisBlock = world.getBlockId(x, y, z);
                    if (thisBlock != Block.netherrack.id && thisBlock != Block.oreNethercoalNetherrack.id) {
                        continue;
                    }
                    
                    double xDistance = (x - centerX) / (lengthX / 2.0);
                    double zDistance = (z - centerZ) / (lengthZ / 2.0);
                    double distanceToCenter = Math.sqrt(xDistance * xDistance + zDistance * zDistance);
                    double distanceFromRadius = (1.0 - distanceToCenter) * Math.max(lengthX, lengthZ) - weight ;

                    if (distanceFromRadius < -weight) {
                        continue;
                    } else if (distanceFromRadius < weight) {
                        double probability = (distanceFromRadius + 2.0) / 4.0;
                        if (random.nextDouble() > probability) {
                            continue;
                        }
                    }

                    int oneAbove = world.getBlockId(x, y + 1, z);
                    int twoBelow = world.getBlockId(x, y - 2, z);
                    // If not one block above an overhang
                    if (twoBelow == Block.netherrack.id || twoBelow == Block.oreNethercoalNetherrack.id) {
                        // If bottom of patch
                        if (startY - y == heightBelow) {
                            chooseSandOrSandstone(world, oneAbove, x, y, z);
                        // If one above bottom of batch
                        } else if (startY - y + 1 == heightBelow) {
                            if (random.nextBoolean()) {
                                world.setBlock(x, y, z, NFCBlocks.scorchedSand.id);     
                            } else {
                                chooseSandOrSandstone(world, oneAbove, x, y, z);
                            }
                        // Everywhere else
                        } else {
                            world.setBlock(x, y, z, NFCBlocks.scorchedSand.id);
                        }

                        continue;
                    }

                    int oneBelow = world.getBlockId(x, y - 1, z);
                    // If one block above an overhang            
                    if ((oneBelow == Block.netherrack.id || oneBelow == Block.oreNethercoalNetherrack.id)) {
                        chooseSandOrSandstone(world, oneAbove, x, y, z);
                        world.setBlock(x, y - 1, z, NFCBlocks.scorchedSandstone.id);
                    }
                }
            }
        }

        return true;
    }

    // Prevent sandstone from generating without sand above it
    private void chooseSandOrSandstone(World world, int oneAbove, int x, int y, int z) {
        if (oneAbove == NFCBlocks.scorchedSand.id || oneAbove == NFCBlocks.scorchedSandstone.id) {
            world.setBlock(x, y, z, NFCBlocks.scorchedSandstone.id);
        } else {
            world.setBlock(x, y, z, NFCBlocks.scorchedSand.id);
        }
    }
}
