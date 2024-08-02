package controller.network.MessageHandler;


import model.networkCommunication.Message.Message;
import model.networkCommunication.Message.PauseGameMessage;
import myProject.MyProject;

public class PauseGameHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message) {

        PauseGameMessage pauseGameMessage = (PauseGameMessage) message;

        for(String user : pauseGameMessage.getPlayers()){
            MyProject.getInstance().getDatabase().getClientHandlerMap().get(user).sendMessage(pauseGameMessage);
        }

    }
}
