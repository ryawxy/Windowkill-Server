package controller.network.MessageHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.networkCommunication.Message.Message;
import model.networkCommunication.Message.ShowRecordsMessage;
import myProject.MyProject;

public class ShowRecordsHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message)  {

        ShowRecordsMessage recordsMessage = (ShowRecordsMessage) message;
        recordsMessage.setRecords(MyProject.getInstance().getDatabase().getGameResults());
        MyProject.getInstance().getDatabase().getClientHandlerMap().get(recordsMessage.getSender()).sendMessage(recordsMessage);
    }
}
