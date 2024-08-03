package model.networkCommunication.Message;

import java.util.ArrayList;

public class EndGameMessage extends Message{
    private ArrayList<String> players = new ArrayList<>();

    public EndGameMessage() {
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }
}
