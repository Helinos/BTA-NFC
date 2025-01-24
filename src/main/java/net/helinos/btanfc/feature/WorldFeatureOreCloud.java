package net.helinos.btanfc.feature;

import java.util.Random;

import org.apache.commons.lang3.ArrayUtils;

import net.minecraft.core.block.Block;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeature;

public class WorldFeatureOreCloud extends WorldFeature {
    int id;
    int radius;
    int density;
    int amount;
    int[] generateInID;
    
    public WorldFeatureOreCloud(int id, int radius, int density, int amount) {
        this.id = id;
        this.radius = radius;
        this.density = density;
        this.amount = amount;
        this.generateInID = new int[] { Block.stone.id, Block.netherrack.id, Block.basalt.id, Block.limestone.id, Block.granite.id };
    }

    @Override
    public boolean generate(World world, Random random, int blockX, int blockY, int blockZ) {
        int adjustedAmount = (int) (amount - amount * random.nextFloat() * 0.2);
        boolean preGen[][][] = new boolean[radius * 2][radius * 2][radius * 2];
        double angleIncrement = Math.PI * 2 / adjustedAmount;
        
        for (int index = adjustedAmount - 1; index >= 0; index--) {
            double currentLength = radius;
            for (int i = 0; i <= density; i++)
                currentLength *= random.nextFloat();
            
            int offsetX = (int) (currentLength * Math.cos(angleIncrement * index)) + radius;
            int offsetY = (int) (currentLength * Math.sin(angleIncrement * index)) + radius;
            int offsetZ = (int) (currentLength * Math.sin(Math.PI * 2 * random.nextFloat())) + radius;
            
            if (preGen[offsetX][offsetY][offsetZ]) {
                index++;
            } else {
                preGen[offsetX][offsetY][offsetZ] = true;
            }
        }

        for (int x = 0; x < radius * 2; x++)
            for (int y = 0; y < radius * 2; y++)
                for (int z = 0; z < radius * 2; z++)
                    if (preGen[x][y][z] && ArrayUtils.contains(generateInID, world.getBlockId(blockX + x, blockY + y, blockZ + z)))
                        world.setBlock(blockX + x, blockY + y , blockZ + z, this.id);

        return true;
    }
}
