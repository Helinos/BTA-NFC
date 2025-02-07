package net.helinos.btanfc.block;

import java.util.Random;

import org.jetbrains.annotations.NotNull;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicMushroom;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.util.helper.DamageType;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class BlockLogicMushroomFire extends BlockLogicMushroom {
    public BlockLogicMushroomFire(Block<?> block) {
        super(block);
    }

    @Override
    public void animationTick(World world, int x, int y, int z, Random random) {
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
    public void onBlockPlacedOnSide(World world, int x, int y, int z, @NotNull Side side, double xPlaced, double yPlaced) {
        if (!world.isClientSide) {
            return;
        }

        if (world.getBlockId(x, y - 1, z) == Blocks.OBSIDIAN.id()) {
            world.setBlock(x, y, z, 0);
            if (!(Blocks.PORTAL_NETHER.getLogic()).tryToCreatePortal(world, x, y, z, null)) {
                world.setBlock(x, y, z, this.id());
            }
        }
    }
}
