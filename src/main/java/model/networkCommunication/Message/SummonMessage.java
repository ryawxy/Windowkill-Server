package model.networkCommunication.Message;


import model.enums.MessageType;

public class SummonMessage extends Message{
    private String sender;
    private String target;
    public boolean isAccepted;

    public SummonMessage() {
        setMessageType(MessageType.SUMMON);
    }

    @Override
    public String getSender() {
        return sender;
    }

    @Override
    public void setSender(String sender) {
        this.sender = sender;
    }

    @Override
    public String getTarget() {
        return target;
    }

    @Override
    public void setTarget(String target) {
        this.target = target;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }
}
