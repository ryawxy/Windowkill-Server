package controller.network.MessageHandler;


import model.Game.Game;
import model.Game.UserData;
import model.enums.UserStatus;
import model.networkCommunication.Message.AnswerToSummonMessage;
import model.networkCommunication.Message.Message;
import myProject.Database;
import myProject.MyProject;

import java.util.Random;

public class AnswerToSummonHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message) {

        AnswerToSummonMessage answer = (AnswerToSummonMessage) message;
        sendResultMessage(answer);
    }
    private void sendResultMessage(AnswerToSummonMessage answer){
        AnswerToSummonMessage result = new AnswerToSummonMessage();
        result.setSender(answer.getSender());
        result.setTarget(answer.getTarget());
        result.setAccepted(answer.isAccepted());
        result.setUsers(answer.getUsers());
        if(result.isAccepted()){
            for(Game game : MyProject.getInstance().getDatabase().getGames()){
                if(game.getPlayers().contains(result.getTarget())){
                    game.getPlayers().add(result.getSender());
                    Random random = new Random();
                    UserData userData = new UserData();
                    userData.setUsername(result.getSender());
                    userData.setXP(MyProject.getInstance().getDatabase().getAllUsers().get(result.getSender()).getUserData().getXP());
                    userData.setX(random.nextInt(300));
                    userData.setY(random.nextInt(300));
                    userData.setSquad(MyProject.getInstance().getDatabase().getAllUsers().get(result.getSender()).getUserData().getSquad());
                    userData.setStatus(UserStatus.Online);
                    result.getUsers().add(userData);
                    userData.setColor(Database.getColors().get(result.getUsers().indexOf(userData)));

                    for(String users : game.getPlayers()){
                        MyProject.getInstance().getDatabase().getClientHandlerMap().get(users).sendMessage(result);
                    }

                }
            }
        }


    }
    }

