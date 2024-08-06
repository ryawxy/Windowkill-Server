package controller.commandLine;

import model.networkCommunication.Server;

import java.lang.module.FindException;
import java.lang.reflect.Field;

public class GameCLI implements Runnable{

    private final Server server;

    public GameCLI(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        System.out.println("Welcome to the Game CLI. --help to see available commands.");
    }
}
