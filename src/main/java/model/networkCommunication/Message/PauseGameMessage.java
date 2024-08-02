package model.networkCommunication.Message;

import model.enums.MessageType;

import java.util.ArrayList;

public class PauseGameMessage extends Message{
    private ArrayList<String> players = new ArrayList<>();
    private String sender;
    private boolean isPaused;

    public PauseGameMessage() {
        setMessageType(MessageType.PAUSE_GAME);
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }

    @Override
    public String getSender() {
        return sender;
    }

    @Override
    public void setSender(String sender) {
        this.sender = sender;
    }

    public boolean isPaused() {
        return isPaused;
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }
}
