package controller.commandLine;

import model.Game.BattleHandler;
import model.networkCommunication.Server;
import picocli.CommandLine;

@CommandLine.Command(name = "terminateBattle", description = "Terminates the battle")

public class TerminateBattleCommand implements Runnable{
    private final Server server;

    public TerminateBattleCommand(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        BattleHandler.terminateBattle();
    }
}
