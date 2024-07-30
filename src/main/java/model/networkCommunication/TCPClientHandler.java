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
        while (isClientOnline && receiver.hasNextLine() ) {

            String receivedJson = receiver.nextLine();

            if (receivedJson == null) {
                    break;
                }
            try {

                Message receivedMessage = JsonUtils.deserializeFromJson(receivedJson, Message.class);
                System.out.println(receivedJson);
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
            sender.println(jsonString);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
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
