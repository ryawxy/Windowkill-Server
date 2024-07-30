package controller.network.MessageHandler;

import model.networkCommunication.Message.Message;
import model.networkCommunication.Message.ServerConnectionMessage;

public class ServerConnectionHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message) {

        if(message instanceof ServerConnectionMessage){
            ServerConnectionMessage connectionMessage = (ServerConnectionMessage) message;

        }

    }
}
