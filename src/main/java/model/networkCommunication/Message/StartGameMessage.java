package model.networkCommunication.Message;

import model.Game.UserData;
import model.enums.MessageType;

import java.util.ArrayList;

public class StartGameMessage extends Message{

    private String battleMode;
    private ArrayList<UserData> users = new ArrayList<>();

    public StartGameMessage() {
        setMessageType(MessageType.START_GAME);
    }


    public String getBattleMode() {
        return battleMode;
    }

    public void setBattleMode(String battleMode) {
        this.battleMode = battleMode;
    }

    public ArrayList<UserData> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserData> users) {
        this.users = users;
    }
}
