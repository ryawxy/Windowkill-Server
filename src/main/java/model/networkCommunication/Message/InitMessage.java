package model.networkCommunication.Message;

import model.enums.MessageType;

import java.net.InetAddress;

public class InitMessage extends Message{
    private InetAddress inetAddress;
    private int udpPort;
    private String username;

    public InitMessage() {
        setMessageType(MessageType.INIT);
    }

    public InetAddress getInetAddress() {
        return inetAddress;
    }

    public void setInetAddress(InetAddress inetAddress) {
        this.inetAddress = inetAddress;
    }

    public int getUdpPort() {
        return udpPort;
    }

    public void setUdpPort(int udpPort) {
        this.udpPort = udpPort;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
