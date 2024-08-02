package controller.network.MessageHandler;

import model.networkCommunication.Message.Message;
import model.networkCommunication.Message.SummonMessage;
import myProject.MyProject;

public class SummonHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message) {

        SummonMessage summonMessage = (SummonMessage) message;


        MyProject.getInstance().getDatabase().getClientHandlerMap().get(summonMessage.getTarget()).sendMessage(summonMessage);
    }
}
