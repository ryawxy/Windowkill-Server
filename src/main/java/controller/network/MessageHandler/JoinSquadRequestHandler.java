package controller.network.MessageHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.networkCommunication.Message.JoinSquadRequestMessage;
import model.networkCommunication.Message.Message;
import model.networkCommunication.Message.RequestToAdminMessage;
import myProject.MyProject;

public class JoinSquadRequestHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message)  {

        if(message instanceof JoinSquadRequestMessage joinSquadRequestMessage){
            try {
                sendRequestToAdmin(joinSquadRequestMessage);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }

        }
    }
    private static void sendRequestToAdmin(JoinSquadRequestMessage message) throws JsonProcessingException {

        RequestToAdminMessage request = new RequestToAdminMessage();
        request.setUsername(message.getUsername());
        request.setSquad(message.getSquad());
        request.setXP(message.getXP());
        request.setNumberOfMembers(MyProject.getInstance().getDatabase().getSquadMap().get(message.getSquad()).getMembers().size());
        String admin = MyProject.getInstance().getDatabase().getSquadMap().get(message.getSquad()).getAdmin();
        MyProject.getInstance().getDatabase().getClientHandlerMap().get(admin).sendMessage(request);
    }
}
