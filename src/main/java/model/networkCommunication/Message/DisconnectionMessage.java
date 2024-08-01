package model.networkCommunication.Message;

import model.enums.MessageType;

public class DisconnectionMessage extends Message{
    private String username;

    private String squad;

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
}
