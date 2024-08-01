package model.networkCommunication.Message;


import model.enums.MessageType;

public class AnswerToBattleRequestMessage extends Message{

    private boolean isAccepted;
    private String battleMode;

    public AnswerToBattleRequestMessage() {
        setMessageType(MessageType.ANSWER_TO_BATTLE_REQUEST);
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public String getBattleMode() {
        return battleMode;
    }

    public void setBattleMode(String battleMode) {
        this.battleMode = battleMode;
    }
}
