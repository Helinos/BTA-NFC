package net.helinos.btanfc.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.helinos.btanfc.gui.ScreenCarpentry;
import net.helinos.btanfc.interfaces.mixin.IPlayer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.PlayerLocal;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.world.World;

@Mixin(value = PlayerLocal.class, remap = false)
public abstract class PlayerLocalMixin extends Player implements IPlayer {
    public PlayerLocalMixin(World world) {
        super(world);
    }

    @Shadow
    protected Minecraft mc;

    @Override
    public void displayGUICarpentryWorkstation(int x, int y, int z) {
        this.mc.displayScreen(new ScreenCarpentry(this.inventory, this.world, x, y, z));
    }
}
