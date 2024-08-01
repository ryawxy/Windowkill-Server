package controller.network.MessageHandler;

import model.Game.OnlineUser;
import model.networkCommunication.Message.JoinSquadRequestMessage;
import model.networkCommunication.Message.Message;
import model.networkCommunication.Message.RequestToAdminMessage;
import myProject.MyProject;

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
        message.setJoined(request.isAccepted());
        message.setUsername(request.getUsername());
        message.setSquad(request.getSquad());
        message.setNumberOfMembers(request.getNumberOfMembers());
        message.setMembersXP(MyProject.getInstance().getDatabase().getSquadMap().get(request.getSquad()).getMembersXP());
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
