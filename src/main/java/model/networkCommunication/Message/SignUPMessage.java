package model.networkCommunication.Message;

import java.util.UUID;

public class SignUPMessage extends Message{
    private String username;
    private int localPort;

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
}
