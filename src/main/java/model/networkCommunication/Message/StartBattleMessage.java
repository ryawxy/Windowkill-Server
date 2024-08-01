package model.networkCommunication.Message;

import model.Game.UserData;
import model.enums.MessageType;

import java.util.ArrayList;
import java.util.HashMap;

public class StartBattleMessage extends Message{
    private String enemySquad;
    private String squad;
    private ArrayList<UserData> users;
    private HashMap<String,Integer> enemyXP = new HashMap<>();
    private HashMap<String,String> enemyStatus = new HashMap<>();

    public StartBattleMessage() {
        setMessageType(MessageType.START_BATTLE);
    }

    public String getEnemySquad() {
        return enemySquad;
    }

    public void setEnemySquad(String enemySquad) {
        this.enemySquad = enemySquad;
    }

    public String getSquad() {
        return squad;
    }

    public void setSquad(String squad) {
        this.squad = squad;
    }

    public ArrayList<UserData> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserData> users) {
        this.users = users;
    }

    public HashMap<String, Integer> getEnemyXP() {
        return enemyXP;
    }

    public void setEnemyXP(HashMap<String, Integer> enemyXP) {
        this.enemyXP = enemyXP;
    }

    public HashMap<String, String> getEnemyStatus() {
        return enemyStatus;
    }

    public void setEnemyStatus(HashMap<String, String> enemyStatus) {
        this.enemyStatus = enemyStatus;
    }
}
