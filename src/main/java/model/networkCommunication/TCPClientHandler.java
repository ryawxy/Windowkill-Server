package model.networkCommunication;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import controller.JsonUtils;
import controller.network.MessageHandler.MessageHandler;
import model.Game.BattleHandler;
import model.networkCommunication.Message.Message;
import myProject.MyProject;

public class TCPClientHandler extends Thread {

    private  final ObjectMapper objectMapper;
    private final Socket socket;
    private final Scanner receiver;
    private final PrintWriter sender;
    private volatile String username = "";
    private volatile boolean isClientOnline;
    private InetAddress udpAddress;
    private int udpPort;
    private final Scanner scanner = new Scanner(System.in);
    private final Scanner sc = new Scanner(System.in);


public TCPClientHandler(Socket clientSocket) throws IOException {

    socket = clientSocket;
    receiver = new Scanner(clientSocket.getInputStream());
    sender = new PrintWriter(clientSocket.getOutputStream(),true);
    objectMapper = new ObjectMapper();
    objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
    objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    isClientOnline = true;
    start();


}

    @Override
    public void run() {
        new Thread(this::startBattle).start();
        while (isClientOnline && receiver.hasNextLine() ) {

            String receivedJson = receiver.nextLine();

        //    System.out.println(receivedJson);
            if (receivedJson == null) {
                    break;
                }
            try {

                Message receivedMessage = JsonUtils.deserializeFromJson(receivedJson, Message.class);
           //     System.out.println(receivedJson);
                processMessage(receivedMessage);

            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processMessage(Message message){

        MessageHandler handler = MyProject.getInstance().getDatabase().getMessageHandlerMap().get(message.getMessageType());
        handler.handleMessage(message);
    }
    public void sendMessage(Message message){
        try {
            String jsonString = JsonUtils.serializeToJson(message);
          //  System.out.println(jsonString);
            sender.println(jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
    public void startBattle() {
        System.out.println(33333);
        while (true) {
            System.out.println(222);
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("startBattle")) {
                System.out.println(11111);
                BattleHandler.initiateBattle();
            }
            else if(input.equalsIgnoreCase("terminateBattle")) BattleHandler.terminateBattle();
        }
    }


    public InetAddress getUdpAddress() {
        return udpAddress;
    }

    public void setUdpAddress(InetAddress udpAddress) {
        this.udpAddress = udpAddress;
    }

    public int getUdpPort() {
        return udpPort;
    }

    public void setUdpPort(int udpPort) {
        this.udpPort = udpPort;
    }
}
