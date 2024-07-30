package model.networkCommunication.Message;

import java.util.UUID;

public class SignUPMessage extends Message{
    private String username;

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


}
