package controller.network.MessageHandler;

import model.Game.OnlineUser;
import model.Game.Squad;
import model.Game.UserData;
import model.networkCommunication.Message.JoinSquadRequestMessage;
import model.networkCommunication.Message.Message;
import model.networkCommunication.Message.RequestToAdminMessage;
import myProject.MyProject;

import java.util.ArrayList;

public class RequestToAdminHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message) {
        if(message instanceof RequestToAdminMessage request){
            if(request.isAccepted()) {
                MyProject.getInstance().getDatabase().getAllUsers()
                        .get(request.getUsername()).getUserData().setSquad(request.getSquad());

                MyProject.getInstance().getDatabase().getSquadMap().get(request.getSquad()).getMembers().add(request.getUsername());
                MyProject.getInstance().getDatabase().getSquadMap().get(request.getSquad()).getMembersXP().put(request.getUsername(),
                        MyProject.getInstance().getDatabase().getAllUsers().get(request.getUsername()).getUserData().getXP());
            }
            resultMessage(request);
        }
    }
    private static void resultMessage(RequestToAdminMessage request){
        JoinSquadRequestMessage message = new JoinSquadRequestMessage();
        ArrayList<UserData> users = new ArrayList<>();
        for(Squad squad : MyProject.getInstance().getDatabase().getSquadMap().values()) {
            if (squad.getSquadName().equals(request.getSquad())) {
                for (String username : squad.getMembers()){
                    UserData user = new UserData();
                    user.setSquad(squad.getSquadName());
                    user.setUsername(username);
                    user.setStatus(MyProject.getInstance().getDatabase().getAllUsers().get(username).getUserData().getStatus());
                    user.setXP(MyProject.getInstance().getDatabase().getAllUsers().get(username).getUserData().getXP());
                    users.add(user);

                }
            }
        }
        message.setUsers(users);
        message.setJoined(request.isAccepted());
        message.setUsername(request.getUsername());
        message.setSquad(request.getSquad());
        message.setNumberOfMembers(request.getNumberOfMembers());
        message.setMembersXP(MyProject.getInstance().getDatabase().getSquadMap().get(request.getSquad()).getMembersXP());
        message.setXP(MyProject.getInstance().getDatabase().getAllUsers().get(request.getUsername()).getUserData().getXP());

        MyProject.getInstance().getDatabase().getClientHandlerMap().get(message.getUsername()).sendMessage(message);

        for(OnlineUser onlineUser : MyProject.getInstance().getDatabase().getAllUsers().values()){
            if(onlineUser.getUserData().getSquad().equals(request.getSquad())){
                if(!onlineUser.getUserData().getUsername().equals(request.getUsername())){
                    MyProject.getInstance().getDatabase().getClientHandlerMap().get(onlineUser.getUserData().getUsername()).sendMessage(message);
                }
            }
        }


    }
}
