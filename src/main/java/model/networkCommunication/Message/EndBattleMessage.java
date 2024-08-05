package model.networkCommunication.Message;

import model.enums.MessageType;

public class EndBattleMessage extends Message{

    private boolean isWin;
    public EndBattleMessage() {
        setMessageType(MessageType.END_BATTLE);
    }

    public boolean isWin() {
        return isWin;
    }

    public void setWin(boolean win) {
        isWin = win;
    }
}
