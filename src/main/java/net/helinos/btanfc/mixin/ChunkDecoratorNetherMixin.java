package net.helinos.btanfc.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.helinos.btanfc.block.BlockLogicScorchedSand;
import net.helinos.btanfc.block.NFCBlocks;
import net.helinos.btanfc.feature.WorldFeatureScorchedSandPatch;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.World;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.generate.chunk.perlin.nether.ChunkDecoratorNether;
import net.minecraft.core.world.generate.feature.WorldFeatureFlowers;

@Mixin(value = ChunkDecoratorNether.class, remap = false)
public abstract class ChunkDecoratorNetherMixin {
    @Shadow
    private World world;
    
    @Inject(method = "decorate", at = @At("TAIL"))
    private void decorate(Chunk chunk, CallbackInfo callbackInfo) {
        int chunkX = chunk.xPosition;
        int chunkZ = chunk.zPosition;
        int x = chunkX * 16;
        int z = chunkZ * 16;
        int minY = this.world.getWorldType().getMinY();
        int maxY = this.world.getWorldType().getMaxY() - 16;
        int rangeY = maxY + 1 - minY;
        Random random = new Random((long)chunkX * 341873128712L + (long)chunkZ * 132897987541L);  
        
        BlockLogicScorchedSand.fallInstantly = true;

        int xStart = x + random.nextInt(16);
        int yStart = minY + random.nextInt(rangeY);
        int zStart = z + random.nextInt(16);
        new WorldFeatureScorchedSandPatch().place(this.world, random, xStart, yStart, zStart);

        if (random.nextBoolean()) {
            xStart = x + random.nextInt(16) + 8;
            yStart = minY + random.nextInt(rangeY);
            zStart = z + random.nextInt(16) + 8;
            new WorldFeatureFlowers(Blocks.MUSHROOM_RED.id(), 64, false).place(world, random, xStart, yStart, zStart);
        }

        if (random.nextBoolean()) {
            xStart = x + random.nextInt(16) + 8;
            yStart = minY + random.nextInt(rangeY);
            zStart = z + random.nextInt(16) + 8;
            new WorldFeatureFlowers(Blocks.MUSHROOM_BROWN.id(), 64, false).place(world, random, xStart, yStart, zStart);
        }

        if (random.nextBoolean()) {
            xStart = x + random.nextInt(16) + 8;
            yStart = minY + random.nextInt(rangeY);
            zStart = z + random.nextInt(16) + 8;
            new WorldFeatureFlowers(NFCBlocks.MUSHROOM_GLOWING.id(), 64, false).place(world, random, xStart, yStart, zStart);
        }

        if (random.nextInt(3) == 0) {
            xStart = x + random.nextInt(16) + 8;
            yStart = minY + random.nextInt(rangeY);
            zStart = z + random.nextInt(16) + 8;
            new WorldFeatureFlowers(NFCBlocks.MUSHROOM_FIRE.id(), 64, false).place(world, random, xStart, yStart, zStart);
        }

        if (random.nextInt(48) == 0) {
            xStart = x + random.nextInt(16) + 8;
            yStart = minY + random.nextInt(rangeY);
            zStart = z + random.nextInt(16) + 8;
            new WorldFeatureFlowers(NFCBlocks.MUSHROOM_PURPLE.id(), 64, false).place(world, random, xStart, yStart, zStart);
        }

        BlockLogicScorchedSand.fallInstantly = false;
    }
}
