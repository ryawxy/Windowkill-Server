package myProject;

import controller.network.MessageHandler.MessageHandler;
import controller.network.MessageHandler.MessageHandlerCreator;
import model.Game.OnlineUser;
import model.Game.Squad;
import model.networkCommunication.ClientHandler;
import model.networkCommunication.Message.Message;
import model.networkCommunication.Message.MessageType;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Database {
    private Map<String, ClientHandler> clientHandlerMap;
    private Map<String, OnlineUser> allUsers;
    private Map<String, ArrayList<Message>> messageQueMap;
    private Map<MessageType, MessageHandler> messageHandlerMap;
    private Map<String, Squad> squadMap;

    public Database(){
        clientHandlerMap = new ConcurrentHashMap<>();
        allUsers = new ConcurrentHashMap<>();
        messageQueMap = new ConcurrentHashMap<>();
        messageHandlerMap = MessageHandlerCreator.getInstance().createMessageHandlerMap();
        squadMap = new ConcurrentHashMap<>();
    }

    public Map<String, ClientHandler> getClientHandlerMap() {
        return clientHandlerMap;
    }

    public void setClientHandlerMap(Map<String, ClientHandler> clientHandlerMap) {
        this.clientHandlerMap = clientHandlerMap;
    }

    public Map<String, OnlineUser> getAllUsers() {
        return allUsers;
    }

    public void setAllUsers(Map<String, OnlineUser> allUsers) {
        this.allUsers = allUsers;
    }

    public Map<String, ArrayList<Message>> getMessageQueMap() {
        return messageQueMap;
    }

    public void setMessageQueMap(Map<String, ArrayList<Message>> messageQueMap) {
        this.messageQueMap = messageQueMap;
    }

    public Map<MessageType, MessageHandler> getMessageHandlerMap() {
        return messageHandlerMap;
    }

    public void setMessageHandlerMap(Map<MessageType, MessageHandler> messageHandlerMap) {
        this.messageHandlerMap = messageHandlerMap;
    }

    public Map<String, Squad> getSquadMap() {
        return squadMap;
    }

    public void setSquadMap(Map<String, Squad> squadMap) {
        this.squadMap = squadMap;
    }
}
