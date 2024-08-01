package model.networkCommunication.Message;

import model.enums.MessageType;

public class ChangeStateMessage extends Message{

    private String username;
    private String squad;
    private String state;
    public ChangeStateMessage() {
        setMessageType(MessageType.CHANGE_STATE);
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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
