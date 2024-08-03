package model.networkCommunication.Message;

import model.enums.MessageType;

import java.util.ArrayList;

public class EndGameMessage extends Message{
    private ArrayList<String> players = new ArrayList<>();
    private String winner;

    public EndGameMessage() {
        setMessageType(MessageType.END_GAME);
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
