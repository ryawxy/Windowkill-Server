package controller.network.PacketHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.Game.Game;
import model.networkCommunication.Packet.KilledEnemyPacket;
import model.networkCommunication.Packet.Packet;
import model.networkCommunication.UDPClientHandler;
import myProject.MyProject;

import java.net.SocketException;

public class KilledEnemyHandler implements PacketHandler {
    @Override
    public void handlePacket(Packet packet) {

        KilledEnemyPacket killedEnemyPacket = (KilledEnemyPacket) packet;

        for(String player : killedEnemyPacket.getUsers()){

                try {
                    UDPClientHandler.getInstance().broadcastMessage(packet, packet.getSenderPort(),MyProject.getInstance().getDatabase().getClientHandlerMap().get(player).getUdpPort() );
                } catch (JsonProcessingException | SocketException ignored) {

                }

            for(Game game : MyProject.getInstance().getDatabase().getGames()){
                if(game.getPlayers().contains(killedEnemyPacket.getSender())){
                    game.getGameLoop().getWaveManager().getKilledEnemies().add(killedEnemyPacket.getEnemy());
                    break;
                }
            }
        }
    }
}
