package model.networkCommunication.Packet;

import model.Game.enemy.GameObjects;
import model.enums.PacketType;

import java.util.ArrayList;


public class KilledEnemyPacket extends Packet{
    private GameObjects enemy;
    private ArrayList<String> users = new ArrayList<>();

    public KilledEnemyPacket() {

            setPacketType(PacketType.KILLED_ENEMY);

        }


    public GameObjects getEnemy() {
        return enemy;
    }

    public void setEnemy(GameObjects enemy) {
        this.enemy = enemy;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }
}
