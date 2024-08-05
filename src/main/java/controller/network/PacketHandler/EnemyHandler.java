package controller.network.PacketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.networkCommunication.Packet.EnemyPacket;
import model.networkCommunication.Packet.Packet;
import model.networkCommunication.UDPClientHandler;
import myProject.MyProject;

import java.net.SocketException;

public class EnemyHandler implements PacketHandler{
    @Override
    public void handlePacket(Packet packet) {

        EnemyPacket enemyPacket = (EnemyPacket) packet;
        for (String user : MyProject.getInstance().getDatabase().getClientHandlerMap().keySet()) {
            if (enemyPacket.getUsers().contains(user)) {

                try {
                    UDPClientHandler.getInstance().broadcastMessage(packet, packet.getSenderPort(),MyProject.getInstance().getDatabase().getClientHandlerMap().get(user).getUdpPort() );
                } catch (JsonProcessingException | SocketException ignored) {

                }
            }
        }
    }
}
