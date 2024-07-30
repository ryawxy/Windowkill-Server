package model.networkCommunication.Message;

import model.enums.MessageType;

public class RemoveMemberMessage extends Message{
    private String admin;

    private String squad;
    private String username;

    public RemoveMemberMessage() {
        setMessageType(MessageType.REMOVE_MEMBER);
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
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
}
