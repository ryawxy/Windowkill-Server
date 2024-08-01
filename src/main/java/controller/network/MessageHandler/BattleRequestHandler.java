package controller.network.MessageHandler;


import model.networkCommunication.Message.BattleRequestMessage;
import model.networkCommunication.Message.Message;
import myProject.MyProject;

public class BattleRequestHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message) {
        BattleRequestMessage requestMessage = (BattleRequestMessage) message;

        String target = requestMessage.getTarget();
        MyProject.getInstance().getDatabase().getClientHandlerMap().get(target).sendMessage(requestMessage);

    }
}
