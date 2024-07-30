package model.networkCommunication.Message;

import model.enums.MessageType;

public class ServerConnectionMessage extends Message{

    private String username;
    private String status;

    public ServerConnectionMessage() {
        setMessageType(MessageType.SERVER_CONNECTION);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
