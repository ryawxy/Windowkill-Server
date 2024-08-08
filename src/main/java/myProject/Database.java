package myProject;

import controller.network.MessageHandler.MessageHandler;
import controller.network.MessageHandler.MessageHandlerCreator;
import controller.network.PacketHandler.PacketHandler;
import controller.network.PacketHandler.PacketHandlerCreator;
import model.Game.Game;
import model.Game.OnlineUser;
import model.Game.Squad;
import model.data.LeaderboardData;
import model.enums.Color;
import model.enums.PacketType;
import model.networkCommunication.TCPClientHandler;
import model.networkCommunication.Message.Message;
import model.enums.MessageType;

import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Database {
    private Map<String, TCPClientHandler> clientHandlerMap;
    private Map<String, OnlineUser> allUsers;
    private Map<String, ArrayList<Message>> messageQueMap;
    private Map<MessageType, MessageHandler> messageHandlerMap;
    private Map<PacketType, PacketHandler> packetHandlerMap;
    private Map<String, Squad> squadMap;
    private ArrayList<Game> games = new ArrayList<>();
    private static ArrayList<Color> colors = new ArrayList<>();
    private ArrayList<Squad> squadsInBattle = new ArrayList<>();
    private ArrayList<ClientData> clientsData = new ArrayList<>();
    private ArrayList<LeaderboardData> GameResults = new ArrayList<>();

    public Database(){
        clientHandlerMap = new ConcurrentHashMap<>();
        packetHandlerMap = new ConcurrentHashMap<>();
        allUsers = new ConcurrentHashMap<>();
        messageQueMap = new ConcurrentHashMap<>();
        messageHandlerMap = MessageHandlerCreator.getInstance().createMessageHandlerMap();
        packetHandlerMap = PacketHandlerCreator.getInstance().createMessageHandlerMap();

        squadMap = new ConcurrentHashMap<>();

        colors.add(Color.Cyan);
        colors.add(Color.Pink);
        colors.add(Color.Green);

    }

    public Map<String, TCPClientHandler> getClientHandlerMap() {
        return clientHandlerMap;
    }

    public void setClientHandlerMap(Map<String, TCPClientHandler> clientHandlerMap) {
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

    public ArrayList<Game> getGames() {
        return games;
    }

    public void setGames(ArrayList<Game> games) {
        this.games = games;
    }

    public static ArrayList<Color> getColors() {
        return colors;
    }

    public static void setColors(ArrayList<Color> colors) {
        Database.colors = colors;
    }

    public ArrayList<Squad> getSquadsInBattle() {
        return squadsInBattle;
    }

    public void setSquadsInBattle(ArrayList<Squad> squadsInBattle) {
        this.squadsInBattle = squadsInBattle;
    }

    public Map<PacketType, PacketHandler> getPacketHandlerMap() {
        return packetHandlerMap;
    }

    public void setPacketHandlerMap(Map<PacketType, PacketHandler> packetHandlerMap) {
        this.packetHandlerMap = packetHandlerMap;
    }

    public ArrayList<ClientData> getClientsData() {
        return clientsData;
    }

    public void setClientsData(ArrayList<ClientData> clientsData) {
        this.clientsData = clientsData;
    }

    public ArrayList<LeaderboardData> getGameResults() {
        return GameResults;
    }

    public void setGameResults(ArrayList<LeaderboardData> gameResults) {
        GameResults = gameResults;
    }
}
