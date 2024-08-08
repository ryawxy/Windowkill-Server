package controller.network.MessageHandler;


import com.fasterxml.jackson.core.JsonProcessingException;
import model.Game.OnlineUser;
import model.Game.Squad;
import model.networkCommunication.Message.LeaveSquadMessage;
import model.networkCommunication.Message.Message;
import myProject.MyProject;

public class LeaveSquadHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message)  {

        LeaveSquadMessage leaveSquadMessage = (LeaveSquadMessage) message;
        String squadName = leaveSquadMessage.getSquad();
        String username = leaveSquadMessage.getUsername();
        Squad squad = MyProject.getInstance().getDatabase().getSquadMap().get(squadName);
        leaveSquadMessage.setAdmin(squad.getAdmin());

        if(squad.getAdmin().equals(username)){
            for(OnlineUser onlineUser : MyProject.getInstance().getDatabase().getAllUsers().values()){
                if(onlineUser.getUserData().getSquad().equals(squadName)){
                    MyProject.getInstance().getDatabase().getClientHandlerMap().
                            get(onlineUser.getUserData().getUsername()).sendMessage(leaveSquadMessage);
                    }
                }

            for(OnlineUser onlineUser : MyProject.getInstance().getDatabase().getAllUsers().values()){
                if(onlineUser.getUserData().getSquad().equals(squadName)) onlineUser.getUserData().setSquad(null);
            }
            MyProject.getInstance().getDatabase().getSquadMap().remove(squadName);

        }else{

            for(OnlineUser onlineUser : MyProject.getInstance().getDatabase().getAllUsers().values()) {
                if (onlineUser.getUserData().getSquad().equals(squadName)) {
                    if (!onlineUser.getUserData().getUsername().equals(leaveSquadMessage.getAdmin())) {
                        MyProject.getInstance().getDatabase().getClientHandlerMap().get(onlineUser.getUserData().getUsername()).sendMessage(leaveSquadMessage);
                    }
                }
            }
            squad.getMembers().remove(username);
        }


    }
}
