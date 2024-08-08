package model.networkCommunication.Message;



import model.data.LeaderboardData;
import model.enums.MessageType;

import java.util.ArrayList;

public class ShowRecordsMessage extends Message{
    private ArrayList<LeaderboardData> records = new ArrayList<>();
    public ShowRecordsMessage() {
        setMessageType(MessageType.SHOW_RECORDS);
    }

    public ArrayList<LeaderboardData> getRecords() {
        return records;
    }

    public void setRecords(ArrayList<LeaderboardData> records) {
        this.records = records;
    }
}
