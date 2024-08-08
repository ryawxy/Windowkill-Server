package controller.network.MessageHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.Game.OnlineUser;
import model.Game.Squad;
import model.networkCommunication.Message.Message;
import model.networkCommunication.Message.RemoveMemberMessage;
import myProject.MyProject;

public class RemoveMemberHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message)  {

        RemoveMemberMessage removeMemberMessage = (RemoveMemberMessage) message;

        String squadName = removeMemberMessage.getSquad();
        String admin = removeMemberMessage.getAdmin();
        String user = removeMemberMessage.getUsername();
        Squad squad = MyProject.getInstance().getDatabase().getSquadMap().get(squadName);

        squad.getMembers().remove(user);
        MyProject.getInstance().getDatabase().getClientHandlerMap().get(user).sendMessage(removeMemberMessage);
        MyProject.getInstance().getDatabase().getClientHandlerMap().get(admin).sendMessage(removeMemberMessage);

        for(OnlineUser onlineUser : MyProject.getInstance().getDatabase().getAllUsers().values()){
            if(onlineUser.getUserData().getSquad().equals(squadName)) {
                if (!onlineUser.getUserData().getUsername().equals(admin) && !onlineUser.getUserData().getUsername().equals(user)) {
                    MyProject.getInstance().getDatabase().getClientHandlerMap().get(onlineUser.getUserData().getUsername()).sendMessage(removeMemberMessage);
                }
            }
        }

    }
}
