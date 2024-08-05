package controller.network.MessageHandler;

import model.networkCommunication.Message.Message;
import model.networkCommunication.Message.SummonMessage;
import myProject.MyProject;

public class SummonHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message) {

        SummonMessage summonMessage = (SummonMessage) message;

        if(MyProject.getInstance().getDatabase().getAllUsers().get(summonMessage.getTarget()).getUserData().isPlayedMonomachia()){
            summonMessage.setHasPlayed(true);
            MyProject.getInstance().getDatabase().getClientHandlerMap().get(summonMessage.getSender()).sendMessage(summonMessage);

        }else MyProject.getInstance().getDatabase().getClientHandlerMap().get(summonMessage.getTarget()).sendMessage(summonMessage);
    }
}
