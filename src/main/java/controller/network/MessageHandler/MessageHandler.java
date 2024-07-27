package controller.network.MessageHandler;

import model.networkCommunication.Message.Message;

public interface MessageHandler {
    void handleMessage(Message message);
}
