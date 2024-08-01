package model.Game;

import java.util.ArrayList;

public class Game {

    private String battleMode;
    private GameLoop gameLoop;
    private ArrayList<String> players = new ArrayList<>();

    public Game(String battleMode) {
        this.battleMode = battleMode;
    }

    public String getBattleMode() {
        return battleMode;
    }

    public void setBattleMode(String battleMode) {
        this.battleMode = battleMode;
    }

    public GameLoop getGameLoop() {
        return gameLoop;
    }

    public void setGameLoop(GameLoop gameLoop) {
        this.gameLoop = gameLoop;
    }

    public ArrayList<String> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<String> players) {
        this.players = players;
    }
}
