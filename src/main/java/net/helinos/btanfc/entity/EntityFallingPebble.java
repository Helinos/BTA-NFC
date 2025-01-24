package net.helinos.btanfc.entity;

import com.mojang.nbt.CompoundTag;

import net.minecraft.core.block.BlockSand;
import net.minecraft.core.entity.EntityFallingSand;
import net.minecraft.core.entity.EntityItem;
import net.minecraft.core.item.ItemStack;
import net.minecraft.core.util.helper.MathHelper;
import net.minecraft.core.util.helper.Side;
import net.minecraft.core.world.World;

public class EntityFallingPebble extends EntityFallingSand {
    public int metadata;

    public EntityFallingPebble(World world, int x, int y, int z, int id) {
        super(world, x + 0.5, y + 0.5, z + 0.5, id);
        this.metadata = world.getBlockMetadata(x, y, z);
    }

    @Override
    public EntityItem spawnAtLocation(ItemStack itemStack, float verticalOffset) {
        itemStack.setMetadata(this.metadata);
        return super.spawnAtLocation(itemStack, verticalOffset);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag tag) {
        tag.putShort("Tile", (short)this.blockID);
        tag.putShort("Metadata", (short)this.metadata);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag tag) {
        this.blockID = tag.getShort("Tile") & 16383;
        this.metadata = tag.getShort("Metadata") & 255;
    }

    @Override
    public void tick() {
        if (this.blockID == 0) {
            this.remove();
        } else {
            this.xo = this.x;
            this.yo = this.y;
            this.zo = this.z;
            ++this.fallTime;
            this.yd -= 0.04;
            this.move(this.xd, this.yd, this.zd);
            this.xd *= 0.98;
            this.yd *= 0.98;
            this.zd *= 0.98;
            int i = MathHelper.floor_double(this.x);
            int j = MathHelper.floor_double(this.y);
            int k = MathHelper.floor_double(this.z);
            if (this.world.getBlockId(i, j, k) == this.blockID) {
                this.world.setBlockWithNotify(i, j, k, 0);
                this.hasRemovedBlock = true;
            }

            if (this.onGround) {
                this.xd *= 0.7;
                this.zd *= 0.7;
                this.yd *= -0.5;
                this.remove();
                if ((!this.world.canBlockBePlacedAt(this.blockID, i, j, k, true, Side.TOP) || BlockSand.canFallBelow(this.world, i, j - 1, k) || !this.world.setBlockAndMetadataWithNotify(i, j, k, this.blockID, this.metadata)) && !this.world.isClientSide && this.hasRemovedBlock) {
                this.spawnAtLocation(this.blockID, 1);
                }
            } else if (this.fallTime > 100 && !this.world.isClientSide) {
                if (this.hasRemovedBlock) {
                this.spawnAtLocation(this.blockID, 1);
                }

                this.remove();
            }
        }
    }
}
