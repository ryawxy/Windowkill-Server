package model.Game;

import model.networkCommunication.Message.StartGameMessage;
import myProject.MyProject;

import javax.swing.*;

public class GameLoop extends Thread{
    private int countTime;
    private boolean isGameStarted;
    private Game game;
    private int seconds;
    private Timer elapsedTimer;
    public GameLoop(Game game) {
        this.game = game;
    }


    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / 120;
        double timePerUpdate = 1000000000.0 / 120;

        long previousTime = System.nanoTime();

        int frames = 0;
        int updates = 0;
        long lastCheck = System.currentTimeMillis();

        double deltaUpdate = 0;
        double deltaFrame = 0;

        while (true) {

            long currentTime = System.nanoTime();

            deltaUpdate += (currentTime - previousTime) / timePerUpdate;
            deltaFrame += (currentTime - previousTime) / timePerFrame;

            previousTime = currentTime;


            if (deltaUpdate >= 1) {

                updates++;
                deltaUpdate--;
            }

            if (deltaFrame >= 1) {

                countTime++;
                System.out.println(countTime);
                if(countTime == 100){

                    StartGameMessage startGameMessage = new StartGameMessage();
                    for(String players : game.getPlayers()) startGameMessage.getPlayers().add(players);
                    startGameMessage.setBattleMode(game.getBattleMode());

                    for(String player : game.getPlayers()) MyProject.getInstance().getDatabase().
                            getClientHandlerMap().get(player).sendMessage(startGameMessage);


                }

                frames++;
                deltaFrame--;
            }

            if (System.currentTimeMillis() - lastCheck >= 1000) {
                lastCheck = System.currentTimeMillis();

                frames = 0;
                updates = 0;
            }
        }
    }

    public int getCountTime() {
        return countTime;
    }

    public void setCountTime(int countTime) {
        this.countTime = countTime;
    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    public void setGameStarted(boolean gameStarted) {
        isGameStarted = gameStarted;
    }
}
