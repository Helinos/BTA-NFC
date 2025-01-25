package net.helinos.btanfc.block;

import java.util.Random;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockMushroom;
import net.minecraft.core.block.BlockPortal;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.entity.EntityLiving;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class BlockMushroomFire extends BlockMushroom {
    public BlockMushroomFire(String key, int id) {
        super(key, id);
    }

    @Override
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
        if (random.nextInt(4) != 0) {
            return;
        }

        double spawnX = x + 0.1875 + random.nextDouble() / 1.6;
        double spawnY = y + 0.25 + random.nextDouble() / 1.6;
        double spawnZ = z + 0.1875 + random.nextDouble() / 1.6;

        world.spawnParticle("smoke", spawnX, spawnY, spawnZ, 0.0, 0.0, 0.0, 0);
        world.spawnParticle("flame", spawnX, spawnY, spawnZ, 0.0, 0.0, 0.0, 0);
    }

    @Override
    public void onEntityCollidedWithBlock(World world, int x, int y, int z, Entity entity) {
        Random random = new Random();
        if (random.nextInt(80) == 0 && !(entity instanceof EntityItem)) {
            entity.hurt(null, 1, DamageType.FIRE);
            entity.remainingFireTicks = 80;
        }
    }

    @Override
    public void onBlockPlaced(World world, int x, int y, int z, Side side, EntityLiving entity, double sideHeight) {
        if (!world.isClientSide) {
            return;
        }

        if (world.getBlockId(x, y - 1, z) == Block.obsidian.id) {
            world.setBlock(x, y, z, 0);
            if (!((BlockPortal) Block.portalNether).tryToCreatePortal(world, x, y, z)) {
                world.setBlock(x, y, z, this.id);
            }
        }
    }
}
