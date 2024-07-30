package controller.network.MessageHandler;

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
        MyProject.getInstance().getDatabase().getClientHandlerMap().get(message.getUsername()).sendMessage(message);


    }
}
