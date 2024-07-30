package controller.network.PacketHandler;

import controller.network.PacketHandler.PacketHandler;
import model.networkCommunication.Packet.PacketType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PacketHandlerCreator {

        private static PacketHandlerCreator instance;

    public PacketHandlerCreator() {
    }

    public static PacketHandlerCreator getInstance(){
            if(instance == null) instance = new PacketHandlerCreator();
            return instance;
        }
        public Map<PacketType, PacketHandler> createMessageHandlerMap(){
            Map<PacketType,PacketHandler> packetHandlerMap = new ConcurrentHashMap<>();
            packetHandlerMap.put(PacketType.EPSILON,new EpsilonHandler());
            packetHandlerMap.put(PacketType.SHOTGUN,new ShotGunHandler());
            return packetHandlerMap;
        }
    }

