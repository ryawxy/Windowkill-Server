package model.networkCommunication.Message;


import model.enums.MessageType;

public class ChangeUserDataMessage extends Message {
    private String username;
    private String data;
    private String changedData;

    public ChangeUserDataMessage() {
        setMessageType(MessageType.CHANGE_USER_DATA);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getChangedData() {
        return changedData;
    }

    public void setChangedData(String changedData) {
        this.changedData = changedData;
    }
}