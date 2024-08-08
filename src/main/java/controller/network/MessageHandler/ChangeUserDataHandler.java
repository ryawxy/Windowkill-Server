package controller.network.MessageHandler;


import com.fasterxml.jackson.core.JsonProcessingException;
import model.Game.OnlineUser;
import model.Game.Squad;
import model.networkCommunication.Message.ChangeUserDataMessage;
import model.networkCommunication.Message.Message;
import myProject.MyProject;

public class ChangeUserDataHandler implements MessageHandler {
    @Override
    public void handleMessage(Message message)  {

        ChangeUserDataMessage dataMessage = (ChangeUserDataMessage) message;
        String sender = dataMessage.getUsername();
        for(OnlineUser onlineUser : MyProject.getInstance().getDatabase().getAllUsers().values()){
            if(onlineUser.getUserData().getUsername().equals(sender))
                if(dataMessage.getData().equals("XP")) onlineUser.getUserData()
                        .setXP(Integer.parseInt(dataMessage.getChangedData()));

            if(dataMessage.getData().equals("HP")) onlineUser.getUserData()
                    .setHP(Integer.parseInt(dataMessage.getChangedData()));

        }
        for(String  username : MyProject.getInstance().getDatabase().getClientHandlerMap().keySet()){
            if(!username.equals(sender)) {
                MyProject.getInstance().getDatabase().getClientHandlerMap().get(username).sendMessage(dataMessage);
            }
        }

    }
}
