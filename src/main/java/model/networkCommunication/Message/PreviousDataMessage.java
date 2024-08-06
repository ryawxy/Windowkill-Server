package model.networkCommunication.Message;

import model.LeaderboardData;
import model.enums.MessageType;

import java.util.ArrayList;

public class PreviousDataMessage extends Message{
    ArrayList<LeaderboardData> data = new ArrayList<>();
    public PreviousDataMessage() {
        setMessageType(MessageType.PREVIOUS_DATA);
    }

    public ArrayList<LeaderboardData> getData() {
        return data;
    }

    public void setData(ArrayList<LeaderboardData> data) {
        this.data = data;
    }
}
