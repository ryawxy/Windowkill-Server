package controller.network.MessageHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.Game.OnlineUser;
import model.Game.Squad;
import model.networkCommunication.Message.Message;
import model.networkCommunication.Message.SquadDataMessage;
import myProject.MyProject;

public class SquadDataHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message)  {
        if(message instanceof SquadDataMessage squadDataMessage) {
            String username = squadDataMessage.getUsername();
            String squadName = squadDataMessage.getSquad();
            for(Squad squad : MyProject.getInstance().getDatabase().getSquadMap().values()){
                if(squad.getSquadName().equals(squadName)){
                    for(OnlineUser onlineUser : MyProject.getInstance().getDatabase().getAllUsers().values()){
                        if (onlineUser.getUserData().getSquad().equals(squadName)) {
                            squadDataMessage.getMembersXP().put(onlineUser.getUserData().
                                    getUsername(),onlineUser.getUserData().getXP());
                            squadDataMessage.getMembersStatus().put(onlineUser.getUserData().
                                    getUsername(),String.valueOf(onlineUser.getUserData().getStatus()));
                        }
                    }
                    squadDataMessage.setSquad(squad.getSquadName());
                    squadDataMessage.setAdmin(squad.getAdmin());
                    squadDataMessage.setVault(squad.getVault());
                }
            }

            MyProject.getInstance().getDatabase().getClientHandlerMap().get(username).sendMessage(squadDataMessage);
        }

    }
}
