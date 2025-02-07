package net.helinos.btanfc.mixin;

import java.util.Random;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.helinos.btanfc.BTANFC;
import net.helinos.btanfc.block.NFCBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.Blocks;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeatureGlowstoneA;

@Mixin(value = WorldFeatureGlowstoneA.class, remap = false)
public abstract class WorldFeatureGlowstoneAMixin {
    private int rng = 0;
    
    @Inject(method = "place", at = @At("Head"))
    private void rng(World world, Random random, int x, int y, int z, CallbackInfoReturnable<?> callbackInfoReturnable) {
        rng = random.nextInt(BTANFC.BLUE_GLOWSTONE_CHANCE_1);
    }

    @ModifyArg(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/World;setBlockWithNotify(IIII)Z", ordinal = 0), index = 3)
    private int setBlock1(int blockID) {
        return chooseID();
    }

    @ModifyArg(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/World;setBlockWithNotify(IIII)Z", ordinal = 1), index = 3)
    private int setBlock2(int blockID) {
        return chooseID();
    }

    @Redirect(method = "place", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/block/Block;id()I", ordinal = 2))
    private int testIfGlowstone(Block<?> block) {
        return chooseID();
    }

    private int chooseID() {
        if (rng == 0) {
            return NFCBlocks.GLOWSTONE_BLUE.id();
        }

        return Blocks.GLOWSTONE.id();
    }
}
