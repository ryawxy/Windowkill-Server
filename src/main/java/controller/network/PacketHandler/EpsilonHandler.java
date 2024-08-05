package controller.network.PacketHandler;


import com.fasterxml.jackson.core.JsonProcessingException;
import model.networkCommunication.Packet.EpsilonPacket;
import model.networkCommunication.Packet.Packet;
import model.networkCommunication.UDPClientHandler;
import myProject.MyProject;

import java.net.SocketException;

public class EpsilonHandler implements PacketHandler {
    @Override
    public void handlePacket(Packet packet) {
        EpsilonPacket epsilonPacket = (EpsilonPacket) packet;
        for (String user : MyProject.getInstance().getDatabase().getClientHandlerMap().keySet()) {
            if (epsilonPacket.getUsers().contains(user)) {

                try {
                    UDPClientHandler.getInstance().broadcastMessage(packet, packet.getSenderPort(),MyProject.getInstance().getDatabase().getClientHandlerMap().get(user).getUdpPort() );
                } catch (JsonProcessingException | SocketException ignored) {

                }
            }
        }
    }
}
