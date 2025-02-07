package net.helinos.btanfc.block;

import java.util.Random;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicSand;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.util.phys.AABB;
import net.minecraft.core.world.World;
import net.minecraft.core.world.WorldSource;

public class BlockLogicScorchedSand extends BlockLogicSand {
    public BlockLogicScorchedSand(Block<?> block) {
        super(block);
    }

    @Override
    public AABB getCollisionBoundingBoxFromPool(WorldSource world, int x, int y, int z) {
        return AABB.getTemporaryBB(x, y, z, x + 1, y + 0.875, z + 1);
    }

    @Override
    public void animationTick(World world, int x, int y, int z, Random random) {
        if (random.nextInt(32) != 0) {
            return;
        }

        Block<?> aboveBlock = world.getBlock(x, y + 1, z);
        Material aboveMaterial = aboveBlock == null ? Material.air : aboveBlock.getMaterial();

        if (aboveMaterial.blocksMotion()) {
            return;
        }

        double spawnX = x + random.nextDouble();
        double spawnY = y + 1 - (random.nextDouble() * 0.05);
        double spawnZ = z + random.nextDouble();
        if (random.nextInt(3) == 0) {
            world.spawnParticle("smoke", spawnX, spawnY, spawnZ, 0.0, 0.0, 0.0, 0);
        } else {
            world.spawnParticle("largesmoke", spawnX, spawnY, spawnZ, 0.0, 0.0, 0.0, 0);
        }
    }
}
