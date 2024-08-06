package model.networkCommunication;

import controller.commandLine.GameCLI;
import myProject.MyProject;
import picocli.CommandLine;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static Server instance;
    public final int PORT = 12345;
    private Server(){


    }


    public void start() throws IOException {
        ServerSocket serverSocket = new ServerSocket(PORT);
        while(true){
            try{
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Socket clientSocket = serverSocket.accept();

            TCPClientHandler TCPClientHandler = new TCPClientHandler(clientSocket);
            MyProject.getInstance().getDatabase().getClientHandlerMap().put("", TCPClientHandler);
        }
    }


    public static Server getInstance() {
        if(instance == null) instance = new Server();
        return instance;
    }
}
