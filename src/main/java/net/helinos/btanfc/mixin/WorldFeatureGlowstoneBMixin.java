package net.helinos.btanfc.mixin;

import java.util.Random;

import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.helinos.btanfc.BTANFC;
import net.helinos.btanfc.block.NFCBlocks;
import net.minecraft.core.block.Block;
import net.minecraft.core.world.World;
import net.minecraft.core.world.generate.feature.WorldFeatureGlowstoneB;

@Mixin(value = WorldFeatureGlowstoneB.class, remap = false)
public abstract class WorldFeatureGlowstoneBMixin {
    private int rng = 0;
    
    @Inject(method = "generate", at = @At("Head"))
    private void rng(World world, Random random, int x, int y, int z, CallbackInfoReturnable<?> callbackInfoReturnable) {
        rng = random.nextInt(BTANFC.BLUE_GLOWSTONE_CHANCE_2);
    }

    @ModifyArg(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/World;setBlockWithNotify(IIII)Z", ordinal = 0), index = 3)
    private int setBlock1(int blockID) {
        return chooseID();
    }

    @ModifyArg(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/world/World;setBlockWithNotify(IIII)Z", ordinal = 1), index = 3)
    private int setBlock2(int blockID) {
        return chooseID();
    }

    @Redirect(method = "generate", at = @At(value = "FIELD", target = "Lnet/minecraft/core/block/Block;id:I", opcode = Opcodes.GETFIELD, ordinal = 2))
    private int testIfGlowstone(Block block) {
        return chooseID();
    }

    private int chooseID() {
        if (rng == 0) {
            return NFCBlocks.glowstoneBlue.id;
        }

        return Block.glowstone.id;
    }
}
