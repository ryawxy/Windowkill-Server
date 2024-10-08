package model.networkCommunication.Packet;

import model.Game.enemy.GameObjects;
import model.enums.PacketType;

import java.util.ArrayList;
import java.util.List;


public class EnemyPacket extends Packet {
    private List<GameObjects> enemies = new ArrayList<>();
    private ArrayList<String> users = new ArrayList<>();
    public EnemyPacket() {
        setPacketType(PacketType.ENEMY);
    }

    public List<GameObjects> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<GameObjects> enemies) {
        this.enemies = enemies;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }
}
