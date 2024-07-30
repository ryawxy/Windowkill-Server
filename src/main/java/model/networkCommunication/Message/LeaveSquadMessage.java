package model.networkCommunication.Message;

import model.enums.MessageType;

public class LeaveSquadMessage extends Message{
    private String username;
    private String squad;
    private String admin;

    public LeaveSquadMessage() {
        setMessageType(MessageType.LEAVE_SQUAD);
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

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}
