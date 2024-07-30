package model.networkCommunication.Message;

import model.enums.MessageType;

public class RequestToAdminMessage extends Message{
    private String username;
    private boolean isAccepted;
    private String squad;
    private int numberOfMembers;

    public RequestToAdminMessage() {
        setMessageType(MessageType.REQUEST_TO_ADMIN);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public String getSquad() {
        return squad;
    }

    public void setSquad(String squad) {
        this.squad = squad;
    }

    public int getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setNumberOfMembers(int numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }
}
