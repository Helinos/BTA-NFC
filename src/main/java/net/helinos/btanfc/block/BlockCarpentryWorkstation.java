package net.helinos.btanfc.block;

import net.helinos.btanfc.gui.GuiCarpentryWorkstation;
import net.helinos.btanfc.interfaces.mixin.IEntityPlayerMP;
import net.minecraft.client.Minecraft;
import net.minecraft.core.block.BlockRotatableHorizontal;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.EntityPlayer;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;
import net.minecraft.server.entity.player.EntityPlayerMP;

public class BlockCarpentryWorkstation extends BlockRotatableHorizontal {
    public BlockCarpentryWorkstation(String key, int id) {
        super(key, id, Material.wood);
    }

    @Override
    public boolean onBlockRightClicked(World world, int x, int y, int z, EntityPlayer entityPlayer, Side side, double xPlaced, double yPlaced) {
        if (entityPlayer instanceof EntityPlayerMP) {
            ((IEntityPlayerMP)entityPlayer).displayGUICarpentryWorkstation(x, y, z);
        } else {
            Minecraft.getMinecraft(Minecraft.class).displayGuiScreen(new GuiCarpentryWorkstation(entityPlayer.inventory, world, x, y, z));
        }

        return true;
    }
}
