package model.networkCommunication.Message;

import model.enums.MessageType;

public class JoinSquadRequestMessage extends Message{
    private String username;
    private String squad;
    private boolean isJoined;
    private int numberOfMembers;

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
}
