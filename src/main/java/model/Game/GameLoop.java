package model.Game;

import model.enums.UserStatus;
import model.networkCommunication.Message.StartGameMessage;
import myProject.Database;
import myProject.MyProject;

import javax.swing.*;
import java.util.Random;

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

                if(countTime == 100){

                    StartGameMessage startGameMessage = new StartGameMessage();
                    for(String players : game.getPlayers()){
                        Random random = new Random();
                        UserData userData = new UserData();
                        userData.setUsername(players);
                        userData.setXP(MyProject.getInstance().getDatabase().getAllUsers().get(players).getUserData().getXP());
                        userData.setX(random.nextInt(300));
                        userData.setY(random.nextInt(300));
                        userData.setStatus(UserStatus.Online);
                        userData.setSquad(MyProject.getInstance().getDatabase().getAllUsers().get(players).getUserData().getSquad());
                        userData.setColor(Database.getColors().get(game.getPlayers().indexOf(players)));
                        startGameMessage.getUsers().add(userData);
                    }


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

    private void endMonomachiaBattle(int time){

    }
}
