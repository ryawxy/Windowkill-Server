package controller.network.PacketHandler;


import com.fasterxml.jackson.core.JsonProcessingException;
import model.networkCommunication.Packet.Packet;
import model.networkCommunication.Packet.ShotGunPacket;
import model.networkCommunication.UDPClientHandler;
import myProject.MyProject;

import java.net.SocketException;

public class ShotGunHandler implements PacketHandler {
    @Override
    public void handlePacket(Packet packet) {

        ShotGunPacket shotGunPacket = (ShotGunPacket) packet;

        for (String user : MyProject.getInstance().getDatabase().getClientHandlerMap().keySet()) {
            if (shotGunPacket.getUsers().contains(user)) {

                try {
                    UDPClientHandler.getInstance().broadcastMessage(packet, packet.getSenderPort(),MyProject.getInstance().getDatabase().getClientHandlerMap().get(user).getUdpPort() );
                } catch (JsonProcessingException | SocketException ignored) {

                }
            }
        }
    }
}
