package controller.exceptionHandler;

import model.networkCommunication.Message.Message;

public interface TCPClientHandlerService {
    void sendMessage(Message message);
    void processMessage(Message message);
}
