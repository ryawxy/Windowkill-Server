package model.networkCommunication.Message;


import model.Game.Squad;
import model.enums.MessageType;

import java.util.HashMap;

public class SquadDataMessage extends Message {

    private String squad;
    private String username;
    private HashMap<String,Integer> membersXP;
    private HashMap <String, String> membersStatus;
    private String admin;
    private int vault;

    public SquadDataMessage() {
        setMessageType(MessageType.SQUAD_DATA);
    }

    public String getSquad() {
        return squad;
    }

    public void setSquad(String squad) {
        this.squad = squad;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public int getVault() {
        return vault;
    }

    public void setVault(int vault) {
        this.vault = vault;
    }
}

