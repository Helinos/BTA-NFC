package net.helinos.btanfc.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.helinos.btanfc.block.NFCBlocks;
import net.helinos.btanfc.feature.WorldFeatureOreCloud;
import net.minecraft.core.block.BlockLogicSand;
import net.minecraft.core.world.World;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.generate.chunk.perlin.overworld.ChunkDecoratorOverworld;
import net.minecraft.core.world.generate.feature.WorldFeatureOre;

import org.spongepowered.asm.mixin.injection.At;

@Mixin(value = ChunkDecoratorOverworld.class, remap = false)
public abstract class ChunkDecoratorOverworldMixin {
    @Shadow
    private World world;
    
    @Inject(method = "decorate", at = @At("TAIL"))
    private void generatePebble(Chunk chunk, CallbackInfo callbackInfo) {
        this.world.scheduledUpdatesAreImmediate = true;
        BlockLogicSand.fallInstantly = true;

        int chunkX = chunk.xPosition;
        int chunkZ = chunk.zPosition;
        int minY = this.world.getWorldType().getMinY();
        int maxY = this.world.getWorldType().getMaxY();
        int rangeY = maxY + 1 - minY;
        int x = chunkX * 16;
        int z = chunkZ * 16;
        Random random = new Random(this.world.getRandomSeed());
        
        for (int index1 = 0; index1 < 2; index1++) {
            for (int index2 = 0; index2 < 2; index2++) {
                int xStart = x + random.nextInt(16);
                int yStart = random.nextInt(rangeY);
                int zStart = z + random.nextInt(16);
                new WorldFeatureOreCloud(NFCBlocks.PEBBLE.id(), 10, 2, 80).place(this.world, random, xStart, yStart, zStart);
            }

            int xStart = x + random.nextInt(16);
            int yStart = random.nextInt(rangeY);
            int zStart = z + random.nextInt(16);
            new WorldFeatureOre(NFCBlocks.PEBBLE.id(), 32).place(this.world, random, xStart, yStart, zStart);
        }


        BlockLogicSand.fallInstantly = false;
        this.world.scheduledUpdatesAreImmediate = false;
    }
}
