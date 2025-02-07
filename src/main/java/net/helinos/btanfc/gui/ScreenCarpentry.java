package net.helinos.btanfc.gui;

import org.lwjgl.opengl.GL11;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.helinos.btanfc.inventory.menu.MenuCarpentry;
import net.minecraft.client.gui.container.ScreenContainerAbstract;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.player.inventory.container.ContainerInventory;
import net.minecraft.core.world.World;

@Environment(EnvType.CLIENT)
public class ScreenCarpentry extends ScreenContainerAbstract {
    public ScreenCarpentry(ContainerInventory playerInventory, World world, int x, int y, int z) {
        super(new MenuCarpentry(playerInventory, world, x, y, z));
    }

    @Override
    public void removed() {
        super.removed();
        this.inventorySlots.onCraftGuiClosed(this.mc.thePlayer);
    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        this.font.drawString(I18n.getInstance().translateKey("gui.crafting.label.carpentry_workstation"), 28, 6, 4210752);
        this.font.drawString(I18n.getInstance().translateKey("gui.crafting.label.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f) {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.textureManager.loadTexture("/assets/btanfc/gui/carpentry.png").bind();
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }
}
