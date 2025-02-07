package net.helinos.btanfc.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.helinos.btanfc.interfaces.mixin.IPlayer;
import net.helinos.btanfc.inventory.menu.MenuCarpentry;
import net.helinos.btanfc.mp.packet.PacketOpenCarpentryWindow;
import net.minecraft.core.crafting.ContainerListener;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.world.World;
import net.minecraft.server.entity.player.PlayerServer;
import net.minecraft.server.net.handler.PacketHandlerServer;

@Mixin(value = PlayerServer.class, remap = false)
public abstract class PlayerServerMixin extends Player implements IPlayer, ContainerListener {
    public PlayerServerMixin(World world) {
        super(world);
    }
    
    @Shadow
    protected abstract void getNextWindowId();

    @Shadow
    public PacketHandlerServer playerNetServerHandler;

    @Shadow
    private int currentWindowId;

    @Override
    public void displayGUICarpentryWorkstation(int x, int y, int z) {
        this.getNextWindowId();
        this.playerNetServerHandler.sendPacket(new PacketOpenCarpentryWindow(this.currentWindowId, x, y, z));
        this.craftingInventory = new MenuCarpentry(this.inventory, this.world, x, y, z);
        this.craftingInventory.containerId = this.currentWindowId;
        this.craftingInventory.addSlotListener(this);
    }
}
