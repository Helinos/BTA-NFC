package net.helinos.btanfc.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.At;

import net.helinos.btanfc.feature.WorldFeatureScorchedSandPatch;
import net.minecraft.core.block.BlockSand;
import net.minecraft.core.world.World;
import net.minecraft.core.world.chunk.Chunk;
import net.minecraft.core.world.generate.chunk.perlin.nether.ChunkDecoratorNether;

@Mixin(value = ChunkDecoratorNether.class, remap = false)
public abstract class ChunkDecoratorNetherMixin {
    @Shadow
    private World world;
    
    @Inject(method = "decorate", at = @At("TAIL"))
    private void scorchedSandDecorate(Chunk chunk, CallbackInfo callbackInfo) {
        int chunkX = chunk.xPosition;
        int chunkZ = chunk.zPosition;
        int x = chunkX * 16;
        int z = chunkZ * 16;
        int minY = this.world.getWorldType().getOceanY();
        int maxY = this.world.getWorldType().getMaxY() - 16;
        int rangeY = maxY + 1 - minY;
        Random rand = new Random((long)chunkX * 341873128712L + (long)chunkZ * 132897987541L);  
        
        BlockSand.fallInstantly = true;

        int xStart = x + rand.nextInt(16);
        int yStart = minY + rand.nextInt(rangeY);
        int zStart = z + rand.nextInt(16);

        new WorldFeatureScorchedSandPatch().generate(this.world, rand, xStart, yStart, zStart);

        BlockSand.fallInstantly = false;
    }
}
