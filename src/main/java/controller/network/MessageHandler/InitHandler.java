package controller.network.MessageHandler;

import model.networkCommunication.Message.InitMessage;
import model.networkCommunication.Message.Message;
import model.networkCommunication.UDPClientHandler;
import myProject.MyProject;

import java.net.SocketException;

public class InitHandler implements MessageHandler {
    @Override
    public void handleMessage(Message message) {
        if (message instanceof InitMessage initMessage) {

            for (String username : MyProject.getInstance().getDatabase().getClientHandlerMap().keySet()) {
                if (username.equals(initMessage.getUsername())) {
                    MyProject.getInstance().getDatabase().getClientHandlerMap().get(initMessage.getUsername()).setUdpAddress(initMessage.getInetAddress());
                    MyProject.getInstance().getDatabase().getClientHandlerMap().get(initMessage.getUsername()).setUdpPort(initMessage.getUdpPort());
                }
            }
            try {
                UDPClientHandler.getInstance().getClientHandlerMap().put(initMessage.getUsername(),MyProject.getInstance().getDatabase().getClientHandlerMap().get(initMessage.getUsername()));
            } catch (SocketException e) {
                throw new RuntimeException(e);
            }

        }
    }
}
