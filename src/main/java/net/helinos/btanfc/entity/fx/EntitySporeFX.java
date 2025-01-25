package net.helinos.btanfc.entity.fx;

import net.minecraft.client.entity.fx.EntityFX;
import net.minecraft.client.render.stitcher.TextureRegistry;
import net.minecraft.core.world.World;

public class EntitySporeFX extends EntityFX {
    public EntitySporeFX(World world, double x, double y, double z, double deltaX, double deltaY, double deltaZ, float particleRed, float particleGreen, float particleBlue) {
        super(world, x, y, z, deltaX, deltaY, deltaZ);
        this.xd = deltaX + (this.random.nextDouble() * 2.0 - 1.0) * 0.025;
        this.yd = deltaY + (this.random.nextDouble() * 2.0 - 1.0) * 0.015;
        this.zd = deltaZ + (this.random.nextDouble() * 2.0 - 1.0) * 0.025;
        this.particleScale = 1.0F;
        this.particleTexture = TextureRegistry.getTexture("minecraft:particle/puff_0");
        this.particleRed *= particleRed;
        this.particleGreen *= particleGreen;
        this.particleBlue *= particleBlue;
        this.particleMaxAge = (int) (100.0 / ((this.random.nextDouble() * random.nextDouble() * 0.4 + 0.2) + 2));
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;

        if (this.particleAge++ >= this.particleMaxAge) {
            this.remove();;
        }

        this.yd += 0.0004;
        this.move(xd, yd, zd);
        this.xd *= 0.8125;
        this.yd *= 0.8125;
        this.zd *= 0.8125;

        if (onGround) {
            this.xd *= 0.7;
            this.zd *= 0.7;
        }
    }
}
