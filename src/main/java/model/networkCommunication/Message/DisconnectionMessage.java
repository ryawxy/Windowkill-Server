package model.networkCommunication.Message;

import model.enums.MessageType;

import java.util.ArrayList;

public class DisconnectionMessage extends Message{
    private String username;

    private String squad;
    private ArrayList<String> enemies = new ArrayList<>();

    public DisconnectionMessage() {
        setMessageType(MessageType.DISCONNECTION);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSquad() {
        return squad;
    }

    public void setSquad(String squad) {
        this.squad = squad;
    }

    public ArrayList<String> getEnemies() {
        return enemies;
    }

    public void setEnemies(ArrayList<String> enemies) {
        this.enemies = enemies;
    }
}
