package model.networkCommunication.Message;


import model.enums.MessageType;

public class BattleRequestMessage extends Message{

    private String sender;
    private String target;
    private String battle;
    private boolean isAccepted;
    public BattleRequestMessage() {
        setMessageType(MessageType.BATTLE_REQUEST);
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

    public String getBattle() {
        return battle;
    }

    public void setBattle(String battle) {
        this.battle = battle;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }
}
