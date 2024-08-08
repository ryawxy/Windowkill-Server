package controller.network.MessageHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.networkCommunication.Message.Message;
import model.networkCommunication.Message.ShowSquadsMessage;
import myProject.MyProject;

import java.util.ArrayList;

public class ShowSquadsHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message)  {
        if(message instanceof ShowSquadsMessage showSquadsMessage){

            ArrayList<String> squads = new ArrayList<>(MyProject.getInstance().getDatabase().getSquadMap().keySet());
            showSquadsMessage.setSquads(squads);
            MyProject.getInstance().getDatabase().getClientHandlerMap().
                    get(((ShowSquadsMessage) message).getUsername()).sendMessage(showSquadsMessage);
        }
    }
}
