package net.helinos.btanfc.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.helinos.btanfc.gui.ScreenCarpentry;
import net.helinos.btanfc.interfaces.mixin.IPacketHandlerClient;
import net.helinos.btanfc.mp.packet.PacketOpenCarpentryWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.net.handler.PacketHandlerClient;
import net.minecraft.client.world.WorldClientMP;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.net.handler.PacketHandler;

@Mixin(value = PacketHandlerClient.class, remap = false)
public abstract class PacketHandlerClientMixin extends PacketHandler implements IPacketHandlerClient {
    @Final
    @Shadow
    private Minecraft mc;

    @Shadow
    private WorldClientMP worldClientMP;

    @Shadow
    protected abstract Entity getEntityByID(int i);

    @Override
    public boolean isServerHandler() {
        return false;
    }

    @Override
    public void handleOpenCarpentryWindow(PacketOpenCarpentryWindow packet) {
        int x = packet.blockX;
        int y = packet.blockY;
        int z = packet.blockZ;

        this.mc.displayScreen(new ScreenCarpentry(this.mc.thePlayer.inventory, this.worldClientMP, x, y, z));
    }
}
