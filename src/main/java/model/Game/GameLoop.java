package model.Game;

import controller.WaveController.WaveData;
import controller.WaveController.WaveManager;
import model.enums.UserStatus;
import model.networkCommunication.Message.ChangeUserDataMessage;
import model.networkCommunication.Message.EndGameMessage;
import model.networkCommunication.Message.StartGameMessage;
import myProject.Database;
import myProject.MyProject;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class GameLoop extends Thread{
    private int countTime;
    private boolean isGameStarted;
    private final Game game;
    private final WaveManager waveManager;
    public GameLoop(Game game) {
        this.game = game;
        waveManager = new WaveManager(game);
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

                if(countTime == 900) waveManager.generateWave(1);
                endMonomachiaBattle(countTime);

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

    private void endMonomachiaBattle(int time) {
        boolean endGame = false;

        for (OnlineUser onlineUser : MyProject.getInstance().getDatabase().getAllUsers().values()) {
            if (game.getPlayers().contains(onlineUser.getUserData().getUsername())) {
                if (onlineUser.getUserData().getHP() <= 0){
                    endGame = true;
                    String squad = onlineUser.getUserData().getSquad();
                    for(String player : game.getPlayers()){
                        if(!onlineUser.getUserData().getUsername().equals(player)){
                            if(MyProject.getInstance().getDatabase().getAllUsers().get(player).getUserData().getSquad().equals(squad)
                                    && onlineUser.getUserData().getHP()>0) endGame = false;
                        }
                    }
                }
            }
        }

            if (time == 2000 || endGame) {
                ArrayList<OnlineUser> users = new ArrayList<>();
                for (String players : game.getPlayers()) {
                    users.add(MyProject.getInstance().getDatabase().getAllUsers().get(players));
                }

                users.sort(Comparator.comparingInt(u -> u.getUserData().getHP()));
                boolean tie = true;
                int first = users.get(0).getUserData().getHP();
                for (OnlineUser user : users) {
                    if (first != user.getUserData().getHP()) {
                        tie = false;
                        break;
                    }
                }
                EndGameMessage endGameMessage = new EndGameMessage();
                endGameMessage.setPlayers(game.getPlayers());

                if (!tie){
                    endGameMessage.setWinner(users.getLast().getUserData().getSquad());
                    int monomachiaWin =  MyProject.getInstance().getDatabase().getSquadMap().get(users.getLast().getUserData().getSquad()).getMonomachiaWin();
                    MyProject.getInstance().getDatabase().getSquadMap().get(users.getLast().getUserData().getSquad()).setMonomachiaWin(monomachiaWin+1);
                }

                else endGameMessage.setWinner("tie");
                String username = users.getLast().getUserData().getUsername();
                ChangeUserDataMessage changeUserDataMessage = new ChangeUserDataMessage();
                if (!tie) {

                    MyProject.getInstance().getDatabase().getAllUsers().get(username).getUserData().setXP(users.getLast().getUserData().getXP() + 80);
                    changeUserDataMessage.setData("XP");
                    changeUserDataMessage.setChangedData(String.valueOf(MyProject.getInstance().getDatabase().getAllUsers().get(username).getUserData().getXP()));
                    changeUserDataMessage.setUsername(username);
                }
                    for (String player : game.getPlayers()) {
                        MyProject.getInstance().getDatabase().getClientHandlerMap().get(player).sendMessage(endGameMessage);
                     if(!tie)   MyProject.getInstance().getDatabase().getClientHandlerMap().get(player).sendMessage(changeUserDataMessage);
                    }


            }
        }
    }



