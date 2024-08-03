package model.networkCommunication.Packet;

import model.Game.enemy.GameObjects;
import model.enums.PacketType;

import java.util.ArrayList;
import java.util.List;


public class EnemyPacket extends Packet {
    private List<GameObjects> enemies = new ArrayList<>();
    public EnemyPacket() {
        setPacketType(PacketType.ENEMY);
    }

    public List<GameObjects> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<GameObjects> enemies) {
        this.enemies = enemies;
    }
}
