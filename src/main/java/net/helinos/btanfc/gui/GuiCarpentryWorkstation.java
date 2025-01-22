package net.helinos.btanfc.gui;

import org.lwjgl.opengl.GL11;

import net.helinos.btanfc.container.ContainerCarpentryWorkstation;
import net.minecraft.client.gui.GuiContainer;
import net.minecraft.core.lang.I18n;
import net.minecraft.core.player.inventory.InventoryPlayer;
import net.minecraft.core.world.World;

public class GuiCarpentryWorkstation extends GuiContainer {
    public GuiCarpentryWorkstation(InventoryPlayer inventoryPlayer, World world, int x, int y, int z) {
        super(new ContainerCarpentryWorkstation(inventoryPlayer, world, x, y, z));
    }

    @Override
    public void onClosed() {
        super.onClosed();
        this.inventorySlots.onCraftGuiClosed(this.mc.thePlayer);
    }

    @Override
    protected void drawGuiContainerForegroundLayer() {
        this.fontRenderer.drawString(I18n.getInstance().translateKey("gui.crafting.label.carpentry_workstation"), 28, 6, 4210752);
        this.fontRenderer.drawString(I18n.getInstance().translateKey("gui.crafting.label.inventory"), 8, this.ySize - 96 + 2, 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float f) {
        int textureIndex = this.mc.renderEngine.getTexture("/assets/btanfc/gui/carpentry.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        this.mc.renderEngine.bindTexture(textureIndex);
        int x = (this.width - this.xSize) / 2;
        int y = (this.height - this.ySize) / 2;
        this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
    }
}
