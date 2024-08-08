package model.networkCommunication.Message;

import model.data.BattleHistory;
import model.enums.MessageType;

import java.util.ArrayList;

public class ShowHistoryMessage extends Message{
    private ArrayList<BattleHistory> history = new ArrayList<>();
    private String squad;

    public ShowHistoryMessage() {
        setMessageType(MessageType.SHOW_HISTORY);
    }

    public ArrayList<BattleHistory> getHistory() {
        return history;
    }

    public void setHistory(ArrayList<BattleHistory> history) {
        this.history = history;
    }

    public String getSquad() {
        return squad;
    }

    public void setSquad(String squad) {
        this.squad = squad;
    }
}
