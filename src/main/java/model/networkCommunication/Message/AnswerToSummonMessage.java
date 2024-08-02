package model.networkCommunication.Message;

import model.Game.UserData;
import model.enums.MessageType;

import java.util.ArrayList;

public class AnswerToSummonMessage extends Message{
    private String sender;
    private String target;
    private boolean isAccepted;
    private ArrayList<UserData> users = new ArrayList<>();

    public AnswerToSummonMessage() {
        setMessageType(MessageType.ANSWER_TO_SUMMON);
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

    public ArrayList<UserData> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserData> users) {
        this.users = users;
    }
}
