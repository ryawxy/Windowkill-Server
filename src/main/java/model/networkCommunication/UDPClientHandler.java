package model.networkCommunication;

import com.fasterxml.jackson.core.JsonProcessingException;
import controller.JsonUtils;
import model.networkCommunication.Packet.Packet;
import myProject.MyProject;

import java.io.IOException;
import java.net.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UDPClientHandler extends Thread {

    private static final int PORT = 54321;
    private final DatagramSocket udpSocket;

    private Map<String, TCPClientHandler> clientHandlerMap;
    private static final int PACKET_SIZE = 1024;
    private static UDPClientHandler instance;
    public UDPClientHandler() throws SocketException {
        clientHandlerMap = new ConcurrentHashMap<>();
        this.udpSocket = new DatagramSocket(PORT);
        start();
//        Thread thread = new Thread(this::broadcastMessage);
//        thread.start();

    }

    @Override
    public void run() {
        byte[] receivedData = new byte[PACKET_SIZE];

        while (true){
            try{
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                udpSocket.receive(receivedPacket);
                String message = new String(receivedPacket.getData(),0, receivedPacket.getLength());
                Packet receivePacket = JsonUtils.deserializeFromJson(message, Packet.class);

                System.out.println(message);

                broadcastMessage(receivePacket,receivePacket.getSenderPort());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        }
    }
    public void broadcastMessage(Packet packet,int senderPort) throws JsonProcessingException {


        String message = JsonUtils.serializeToJson(packet);

            byte[] sendData = message.getBytes();
               for(TCPClientHandler TCPClientHandler : MyProject.getInstance().getDatabase().getClientHandlerMap().values()){

                 if(TCPClientHandler.getUdpPort() != senderPort){

                   System.out.println(TCPClientHandler.getUdpAddress()+" "+ TCPClientHandler.getUdpPort());
                   try {
                    DatagramPacket  sendPacket = new DatagramPacket(sendData, sendData.length, InetAddress.getByName("localhost"), TCPClientHandler.getUdpPort());
                       udpSocket.send(sendPacket);
                       System.out.println(sendPacket);
                   } catch (IOException e) {
                       throw new RuntimeException(e);
                   }


               }
                }
        }


    public static UDPClientHandler getInstance() throws SocketException {
        if(instance == null) instance = new UDPClientHandler();
        return instance;
    }

    public Map<String, TCPClientHandler> getClientHandlerMap() {
        return clientHandlerMap;
    }

    public void setClientHandlerMap(Map<String, TCPClientHandler> clientHandlerMap) {
        this.clientHandlerMap = clientHandlerMap;
    }
}
