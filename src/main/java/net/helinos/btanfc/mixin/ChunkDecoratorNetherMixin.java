package net.helinos.btanfc.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.helinos.btanfc.block.BlockScorchedSand;
import net.helinos.btanfc.block.NFCBlocks;
import net.helinos.btanfc.feature.WorldFeatureScorchedSandPatch;
import net.minecraft.core.block.Block;
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
        
        BlockScorchedSand.fallInstantly = true;

        int xStart = x + random.nextInt(16);
        int yStart = minY + random.nextInt(rangeY);
        int zStart = z + random.nextInt(16);
        new WorldFeatureScorchedSandPatch().generate(this.world, random, xStart, yStart, zStart);

        if (random.nextBoolean()) {
            xStart = x + random.nextInt(16) + 8;
            yStart = minY + random.nextInt(rangeY);
            zStart = z + random.nextInt(16) + 8;
            new WorldFeatureFlowers(Block.mushroomRed.id).generate(world, random, xStart, yStart, zStart);
        }

        if (random.nextBoolean()) {
            xStart = x + random.nextInt(16) + 8;
            yStart = minY + random.nextInt(rangeY);
            zStart = z + random.nextInt(16) + 8;
            new WorldFeatureFlowers(Block.mushroomBrown.id).generate(world, random, xStart, yStart, zStart);
        }

        if (random.nextBoolean()) {
            xStart = x + random.nextInt(16) + 8;
            yStart = minY + random.nextInt(rangeY);
            zStart = z + random.nextInt(16) + 8;
            new WorldFeatureFlowers(NFCBlocks.mushroomGlowing.id).generate(world, random, xStart, yStart, zStart);
        }

        if (random.nextInt(3) == 0) {
            xStart = x + random.nextInt(16) + 8;
            yStart = minY + random.nextInt(rangeY);
            zStart = z + random.nextInt(16) + 8;
            new WorldFeatureFlowers(NFCBlocks.mushroomFire.id).generate(world, random, xStart, yStart, zStart);
        }

        if (random.nextInt(48) == 0) {
            xStart = x + random.nextInt(16) + 8;
            yStart = minY + random.nextInt(rangeY);
            zStart = z + random.nextInt(16) + 8;
            new WorldFeatureFlowers(NFCBlocks.mushroomPurple.id).generate(world, random, xStart, yStart, zStart);
        }

        BlockScorchedSand.fallInstantly = false;
    }
}
