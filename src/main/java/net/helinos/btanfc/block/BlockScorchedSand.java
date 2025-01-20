package net.helinos.btanfc.block;

import java.util.Random;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockSand;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;

public class BlockScorchedSand extends BlockSand {
    public BlockScorchedSand(String key, int id) {
        super(key, id);
    }

    @Override
    public AABB getCollisionBoundingBoxFromPool(WorldSource world, int x, int y, int z) {
        return AABB.getBoundingBoxFromPool(x, y, z, x + 1, y + 0.875, z + 1);
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random rand) {
        if (rand.nextInt(32) != 0) {
            return;
        }

        Block aboveBlock = world.getBlock(x, y + 1, z);
        Material aboveMaterial = aboveBlock == null ? Material.air : aboveBlock.blockMaterial;

        if (aboveMaterial.blocksMotion()) {
            return;
        }

        double spawnX = x + rand.nextDouble();
        double spawnY = y + 1 - (rand.nextDouble() * 0.05);
        double spawnZ = z + rand.nextDouble();
        if (rand.nextInt(3) == 0) {
            world.spawnParticle("smoke", spawnX, spawnY, spawnZ, 0.0, 0.0, 0.0, 0);
        } else {
            world.spawnParticle("largesmoke", spawnX, spawnY, spawnZ, 0.0, 0.0, 0.0, 0);
        }
    }
}
