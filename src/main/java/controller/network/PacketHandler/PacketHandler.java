package controller.network.PacketHandler;


import model.networkCommunication.Packet.Packet;

public interface PacketHandler {
    void handlePacket(Packet packet);
}
