package myProject;

import controller.commandLine.GameCLI;
import controller.commandLine.StartBattleCommand;
import controller.commandLine.TerminateBattleCommand;
import model.networkCommunication.Server;
import myProject.MyProject;
import picocli.CommandLine;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MyProject.getInstance();
        try {
            Server.getInstance().start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        CommandLine commandLine = new CommandLine(new GameCLI(Server.getInstance()));
//        commandLine.addSubcommand(new StartBattleCommand(Server.getInstance()));
//        commandLine.addSubcommand(new TerminateBattleCommand(Server.getInstance()));
//        commandLine.execute(args);

    }
}
