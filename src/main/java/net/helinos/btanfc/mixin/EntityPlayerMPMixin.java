package net.helinos.btanfc.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import net.helinos.btanfc.container.ContainerCarpentryWorkstation;
import net.helinos.btanfc.interfaces.mixin.IEntityPlayerMP;
import net.helinos.btanfc.mp.packet.PacketOpenCarpentryWindow;
import net.minecraft.core.crafting.ICrafting;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.world.World;
import net.minecraft.server.entity.player.EntityPlayerMP;
import net.minecraft.server.net.handler.NetServerHandler;

@Mixin(value = EntityPlayerMP.class, remap = false)
public abstract class EntityPlayerMPMixin extends EntityPlayer implements IEntityPlayerMP, ICrafting {
    public EntityPlayerMPMixin(World world) {
        super(world);
    }

    @Shadow
    protected abstract void getNextWindowId();

    @Shadow
    public NetServerHandler playerNetServerHandler;

    @Shadow
    private int currentWindowId;

    @Override
    public void displayGUICarpentryWorkstation(int x, int y, int z) {
        this.getNextWindowId();
        this.playerNetServerHandler.sendPacket(new PacketOpenCarpentryWindow(this.currentWindowId, x, y, z));
        this.craftingInventory = new ContainerCarpentryWorkstation(this.inventory, this.world, x, y, z);
        this.craftingInventory.windowId = this.currentWindowId;
        this.craftingInventory.onContainerInit((EntityPlayerMP)(Object)this);
    }
}
