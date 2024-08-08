package controller.network.MessageHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.data.BattleHistory;
import model.networkCommunication.Message.Message;
import model.networkCommunication.Message.ShowHistoryMessage;
import myProject.MyProject;

import java.util.ArrayList;

public class ShowHistoryHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message)  {

        ShowHistoryMessage showHistoryMessage =  (ShowHistoryMessage) message;
        ArrayList<BattleHistory> data = BattleHistory.readResults();
        for(BattleHistory battleHistory : data)
            if(battleHistory.getS1().equals(showHistoryMessage.getSquad()) ||
                    battleHistory.getS2().equals(showHistoryMessage.getSquad())) showHistoryMessage.getHistory().add(battleHistory);

        MyProject.getInstance().getDatabase().getClientHandlerMap().get(showHistoryMessage.getSender()).sendMessage(showHistoryMessage);
    }
}
