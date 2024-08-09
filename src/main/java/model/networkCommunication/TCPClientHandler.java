package model.networkCommunication;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.exc.InvalidTypeIdException;
import controller.JsonUtils;
import controller.exceptionHandler.*;
import controller.network.MessageHandler.MessageHandler;
import model.Game.BattleHandler;
import model.networkCommunication.Message.Message;
import myProject.MyProject;

public class TCPClientHandler extends Thread implements TCPClientHandlerService {

    private  final ObjectMapper objectMapper;
    private final Socket socket;
    private final Scanner receiver;
    private final PrintWriter sender;
    private final String username = "";
    private volatile boolean isClientOnline;
    private InetAddress udpAddress;
    private int udpPort;
    private final Scanner sc = new Scanner(System.in);
    private final TCPClientHandlerService service;
    private final JsonUtils jsonUtils = new JsonUtils();



    public TCPClientHandler(Socket clientSocket) throws IOException {
        GlobalExceptionHandler exceptionHandler = new GlobalExceptionHandler();
        service = ExceptionHandlingProxy.createProxy(TCPClientHandlerService.class,this,exceptionHandler);
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
    public void run()  {
        new Thread(this::startBattle).start();
        while (isClientOnline && receiver.hasNextLine() ) {

            String receivedJson = receiver.nextLine();
            Message receivedMessage = new Message();
            if (receivedJson == null) {
                    break;
                }

            receivedMessage = jsonUtils.deserializeFromJson(receivedJson, Message.class);
            service.processMessage(receivedMessage);
        }
        try {
            socket.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    @HandleExceptions
    public void processMessage(Message message) {

        MessageHandler handler = MyProject.getInstance().getDatabase().getMessageHandlerMap().get(message.getMessageType());
            handler.handleMessage(message);

    }
    @Override
    @HandleExceptions
    public void sendMessage(Message message)  {

        String jsonString = null;
        try {
            jsonString = jsonUtils.serializeToJson(message);
        } catch (JsonProcessingException e) {

        }
        sender.println(jsonString);


    }
    public void startBattle() {
        System.out.println(33333);
        while (true) {
            System.out.println(222);
            String input = sc.nextLine();
            if (input.equalsIgnoreCase("startBattle")) {
                System.out.println(11111);
                try {
                    BattleHandler.initiateBattle();
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
            else if(input.equalsIgnoreCase("terminateBattle")) {
                try {
                    BattleHandler.terminateBattle();
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
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

    public TCPClientHandlerService getService() {
        return service;
    }
}
