package controller.network.MessageHandler;

import model.networkCommunication.Message.JoinSquadRequestMessage;
import model.networkCommunication.Message.Message;
import model.networkCommunication.Message.RequestToAdminMessage;
import myProject.MyProject;

public class JoinSquadRequestHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message) {

        if(message instanceof JoinSquadRequestMessage joinSquadRequestMessage){
            sendRequestToAdmin(joinSquadRequestMessage);

        }
    }
    private static void sendRequestToAdmin(JoinSquadRequestMessage message){

        RequestToAdminMessage request = new RequestToAdminMessage();
        request.setUsername(message.getUsername());
        request.setSquad(message.getSquad());
        request.setNumberOfMembers(MyProject.getInstance().getDatabase().getSquadMap().get(message.getSquad()).getMembers().size());
        String admin = MyProject.getInstance().getDatabase().getSquadMap().get(message.getSquad()).getAdmin();
        MyProject.getInstance().getDatabase().getClientHandlerMap().get(admin).sendMessage(request);
    }
}
