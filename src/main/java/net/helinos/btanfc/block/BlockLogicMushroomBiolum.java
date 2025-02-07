package net.helinos.btanfc.block;

import java.util.Random;

import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicMushroom;
import net.minecraft.core.world.World;

public class BlockLogicMushroomBiolum extends BlockLogicMushroom {
    private int tick = 0;
    private String particle;
    
    public BlockLogicMushroomBiolum(Block<?> block, String particle) {
        super(block);
        this.particle = particle;
    }
    
    @Override
    public void animationTick(World world, int x, int y, int z, Random random) {
        if (tick > 10) {
            tick = 0;
        } else {
            tick++;
            return;
        }

        double spawnX = x + 0.5;
        double spawnY = y + 0.2;
        double spawnZ = z + 0.5;

        world.spawnParticle(particle, spawnX, spawnY, spawnZ, 0.0, 0.0, 0.0, 0);
    }
}
