package model.networkCommunication.Message;

import model.enums.MessageType;

import java.util.ArrayList;

public class StartGameMessage extends Message{

    private String battleMode;
    private ArrayList<String> players = new ArrayList<>();

    public StartGameMessage() {
        setMessageType(MessageType.START_GAME);
    }


    public String getBattleMode() {
        return battleMode;
    }

    public void setBattleMode(String battleMode) {
        this.battleMode = battleMode;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }
}
