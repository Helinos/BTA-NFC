package net.helinos.btanfc.block;

import net.helinos.btanfc.interfaces.mixin.IPlayer;
import net.minecraft.core.block.Block;
import net.minecraft.core.block.BlockLogicRotatable;
import net.minecraft.core.block.material.Material;
import net.minecraft.core.entity.player.Player;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class BlockLogicCarpentryWorkstation extends BlockLogicRotatable {
    public BlockLogicCarpentryWorkstation(Block<?> block) {
        super(block, Material.wood);
    }

    @Override
    public boolean onBlockRightClicked(World world, int x, int y, int z, Player player, Side side, double xPlaced, double yPlaced) {
        ((IPlayer) player).displayGUICarpentryWorkstation(x, y, z);
        return true;
    }
}
