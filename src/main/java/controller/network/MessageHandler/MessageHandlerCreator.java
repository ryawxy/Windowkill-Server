package controller.network.MessageHandler;

import model.networkCommunication.Message.MessageType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MessageHandlerCreator {
    private static MessageHandlerCreator instance;
    private MessageHandlerCreator(){

    }
    public static MessageHandlerCreator getInstance(){
        if(instance == null) instance = new MessageHandlerCreator();
        return instance;
    }
    public Map<MessageType,MessageHandler> createMessageHandlerMap(){
        Map<MessageType,MessageHandler> messageHandlerMap = new ConcurrentHashMap<>();
        messageHandlerMap.put(MessageType.SIGN_UP,new SignUpHandler());
        messageHandlerMap.put(MessageType.NEW_SQUAD,new NewSquadHandler());
        messageHandlerMap.put(MessageType.SHOW_SQUADS,new ShowSquadsHandler());
        return messageHandlerMap;
    }
}
