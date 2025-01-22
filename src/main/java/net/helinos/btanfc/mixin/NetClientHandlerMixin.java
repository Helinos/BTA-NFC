package net.helinos.btanfc.mixin;

import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.helinos.btanfc.gui.GuiCarpentryWorkstation;
import net.helinos.btanfc.interfaces.mixin.INetClientHandler;
import net.helinos.btanfc.mp.packet.PacketOpenCarpentryWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.net.handler.NetClientHandler;
import net.minecraft.client.world.WorldClient;
import net.minecraft.core.entity.Entity;
import net.minecraft.core.net.handler.NetHandler;

@Mixin(value = NetClientHandler.class, remap = false)
public abstract class NetClientHandlerMixin extends NetHandler implements INetClientHandler {
    @Final
    @Shadow
    private Minecraft mc;

    @Shadow
    private WorldClient worldClient;

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

        this.mc.displayGuiScreen(new GuiCarpentryWorkstation(this.mc.thePlayer.inventory, this.worldClient, x, y, z));
    }
}
