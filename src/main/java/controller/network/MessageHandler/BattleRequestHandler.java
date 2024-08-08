package controller.network.MessageHandler;


import com.fasterxml.jackson.core.JsonProcessingException;
import model.enums.GameMode;
import model.networkCommunication.Message.BattleRequestMessage;
import model.networkCommunication.Message.Message;
import myProject.MyProject;

public class BattleRequestHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message)  {
        BattleRequestMessage requestMessage = (BattleRequestMessage) message;

        String target = requestMessage.getTarget();
        String sender = requestMessage.getSender();

        if(requestMessage.getBattle().equals(String.valueOf(GameMode.MONOMACHIA))) {
            if (MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserData().isPlayedMonomachia()) {
                requestMessage.setHasPlayed(true);
                MyProject.getInstance().getDatabase().getClientHandlerMap().get(sender).sendMessage(requestMessage);
            } else  MyProject.getInstance().getDatabase().getClientHandlerMap().get(target).sendMessage(requestMessage);
        }
        if(requestMessage.getBattle().equals(String.valueOf(GameMode.COLOSSEUM))) {
            if (MyProject.getInstance().getDatabase().getAllUsers().get(sender).getUserData().isPlayedColosseum()) {
                requestMessage.setHasPlayed(true);
                MyProject.getInstance().getDatabase().getClientHandlerMap().get(sender).sendMessage(requestMessage);
            } else  MyProject.getInstance().getDatabase().getClientHandlerMap().get(target).sendMessage(requestMessage);

        }


    }
}
