package net.helinos.btanfc.mp.packet;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import net.helinos.btanfc.interfaces.mixin.IPacketHandlerClient;
import net.minecraft.core.net.handler.PacketHandler;
import net.minecraft.core.net.packet.Packet;

public class PacketOpenCarpentryWindow extends Packet {
    public int windowId;
    public int blockX;
    public int blockY;
    public int blockZ;
    
    public PacketOpenCarpentryWindow(int windowId, int x, int y, int z) {
        this.windowId = windowId;
    }

    @Override
    public int getEstimatedSize() {
        return 4;
    }

    @Override
    public void handlePacket(PacketHandler packetHandler) {
        ((IPacketHandlerClient) packetHandler).handleOpenCarpentryWindow(this);
    }

    @Override
    public void read(DataInputStream dataInputStream) throws IOException {
        this.windowId = dataInputStream.readByte();
        this.blockX = dataInputStream.readByte();
        this.blockY = dataInputStream.readByte();
        this.blockZ = dataInputStream.readByte();
    }

    @Override
    public void write(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeByte(this.windowId);
        dataOutputStream.writeByte(this.blockX);
        dataOutputStream.writeByte(this.blockY);
        dataOutputStream.writeByte(this.blockZ);
    }
}
