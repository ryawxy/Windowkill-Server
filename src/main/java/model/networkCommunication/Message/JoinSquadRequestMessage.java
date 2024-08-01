package model.networkCommunication.Message;

import model.enums.MessageType;

import java.util.HashMap;

public class JoinSquadRequestMessage extends Message{
    private String username;
    private String squad;
    private boolean isJoined;
    private int numberOfMembers;
    private int XP;
    private HashMap<String,Integer> membersXP = new HashMap<>();
    private HashMap<String,String> membersStatus = new HashMap<>();

    public JoinSquadRequestMessage() {
        setMessageType(MessageType.JOIN_SQUAD);
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

    public boolean isJoined() {
        return isJoined;
    }

    public void setJoined(boolean joined) {
        isJoined = joined;
    }

    public int getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setNumberOfMembers(int numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
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
}
