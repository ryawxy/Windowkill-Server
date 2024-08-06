package controller.commandLine;

import model.Game.BattleHandler;
import model.networkCommunication.Server;
import picocli.CommandLine;
@CommandLine.Command(name = "startBattle", description = "Starts a battle")
public class StartBattleCommand implements Runnable {
    private final Server server;

    public StartBattleCommand(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        BattleHandler.initiateBattle();
    }
}
