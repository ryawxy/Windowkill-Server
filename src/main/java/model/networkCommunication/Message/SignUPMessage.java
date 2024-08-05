package model.networkCommunication.Message;

import model.Game.UserData;

import java.util.HashMap;
import java.util.UUID;

public class SignUPMessage extends Message{
    private String username;
    private int localPort;
    private boolean isSignedIn;
    private UserData userData;
    private boolean isBattleStarted;
    private   HashMap<String,Integer> membersXP = new HashMap<>();
    private   HashMap<String,String> membersStatus = new HashMap<>();
    private HashMap<String,Integer> enemyXP = new HashMap<>();
    private HashMap<String,String> enemyStatus = new HashMap<>();

    public SignUPMessage() {
    }

    public SignUPMessage(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getLocalPort() {
        return localPort;
    }

    public void setLocalPort(int localPort) {
        this.localPort = localPort;
    }

    public boolean isSignedIn() {
        return isSignedIn;
    }

    public void setSignedIn(boolean signedIn) {
        isSignedIn = signedIn;
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }

    public boolean isBattleStarted() {
        return isBattleStarted;
    }

    public void setBattleStarted(boolean battleStarted) {
        isBattleStarted = battleStarted;
    }

    public HashMap<String, Integer> getMembersXP() {
        return membersXP;
    }

    public void setMembersXP(HashMap<String, Integer> membersXP) {
        this.membersXP = membersXP;
    }

    public HashMap<String, String> getMembersStatus() {
        return membersStatus;
    }

    public void setMembersStatus(HashMap<String, String> membersStatus) {
        this.membersStatus = membersStatus;
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
