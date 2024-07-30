package controller.network.MessageHandler;

import model.Game.Squad;
import model.networkCommunication.Message.Message;
import model.networkCommunication.Message.NewSquadMessage;
import myProject.MyProject;

public class NewSquadHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message) {
        if(message instanceof NewSquadMessage newSquadMessage){
            String squadName = newSquadMessage.getSquadName();
            String admin = newSquadMessage.getAdmin();
            Squad squad = new Squad(squadName,admin);
            MyProject.getInstance().getDatabase().getSquadMap().put(squadName,squad);
            MyProject.getInstance().getDatabase().getSquadMap().get(squadName).getMembers().add(admin);
            MyProject.getInstance().getDatabase().getAllUsers().get(admin).getUserData().setSquad(squadName);



           MyProject.getInstance().getDatabase().getClientHandlerMap().get(admin).sendMessage(message);


        }
    }
}
