package controller.network.MessageHandler;


import com.fasterxml.jackson.core.JsonProcessingException;
import model.networkCommunication.Message.DisconnectionMessage;
import model.networkCommunication.Message.Message;
import myProject.MyProject;

public class DisconnectionHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message)  {

        if (message instanceof DisconnectionMessage disconnectionMessage) {

            if (MyProject.getInstance().getDatabase().getSquadMap().get(disconnectionMessage.getSquad()).getMembers() != null) {
                for (String username : MyProject.getInstance().getDatabase().getSquadMap().get(disconnectionMessage.getSquad()).getMembers()) {
                    if (!username.equals(disconnectionMessage.getUsername())) {
                        MyProject.getInstance().getDatabase().getClientHandlerMap().get(username).sendMessage(disconnectionMessage);
                    }
                }

            }
            for(String enemy : disconnectionMessage.getEnemies()){
                MyProject.getInstance().getDatabase().getClientHandlerMap().get(enemy).sendMessage(disconnectionMessage);
            }
        }
    }
}
