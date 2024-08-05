package controller.network.PacketHandler;

import model.enums.PacketType;

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
            packetHandlerMap.put(PacketType.ENEMY,new EnemyHandler());
            packetHandlerMap.put(PacketType.KILLED_ENEMY,new KilledEnemyHandler());
            return packetHandlerMap;
        }
    }

