package controller.network.MessageHandler;

import model.Game.Game;
import model.Game.GameLoop;
import model.Game.Squad;
import model.enums.GameMode;
import model.networkCommunication.Message.AnswerToBattleRequestMessage;
import model.networkCommunication.Message.ChangeStateMessage;
import model.networkCommunication.Message.Message;
import myProject.MyProject;

public class AnswerToBattleRequestHandler implements MessageHandler {

    @Override
    public void handleMessage(Message message) {
        AnswerToBattleRequestMessage answer = (AnswerToBattleRequestMessage) message;

        MyProject.getInstance().getDatabase().getClientHandlerMap().get(answer.getTarget()).sendMessage(answer);
        if(answer.isAccepted()){
            if(answer.getBattleMode().equals(String.valueOf(GameMode.COLOSSEUM))){
                MyProject.getInstance().getDatabase().getAllUsers().get(answer.getTarget()).getUserData().setPlayedColosseum(true);
                MyProject.getInstance().getDatabase().getAllUsers().get(answer.getSender()).getUserData().setPlayedColosseum(true);
            }
            else{
                MyProject.getInstance().getDatabase().getAllUsers().get(answer.getTarget()).getUserData().setPlayedMonomachia(true);
                MyProject.getInstance().getDatabase().getAllUsers().get(answer.getSender()).getUserData().setPlayedMonomachia(true);

            }

            sendChangeStateMessage(answer);
            Game game = new Game(answer.getBattleMode());
            game.setGameLoop(new GameLoop(game));
            game.getPlayers().add(answer.getTarget());
            game.getPlayers().add(answer.getSender());
            game.setBattleMode(answer.getBattleMode());
            System.out.println(answer.getBattleMode());
            MyProject.getInstance().getDatabase().getGames().add(game);
            game.getGameLoop().start();
        }
    }
    private void sendChangeStateMessage(AnswerToBattleRequestMessage answer){
        ChangeStateMessage changeStateMessage = new ChangeStateMessage();
        changeStateMessage.setUsername(answer.getSender());
        changeStateMessage.setState("Busy");

        ChangeStateMessage changeStateMessage2 = new ChangeStateMessage();
        changeStateMessage2.setUsername(answer.getTarget());
        changeStateMessage2.setState("Busy");

        for(Squad squad : MyProject.getInstance().getDatabase().getSquadMap().values()){
            if(squad.getMembers().contains(answer.getSender()) || squad.getMembers().contains(answer.getTarget())){
                for(String member : squad.getMembers()){
                    MyProject.getInstance().getDatabase().getClientHandlerMap().get(member).sendMessage(changeStateMessage2);
                    MyProject.getInstance().getDatabase().getClientHandlerMap().get(member).sendMessage(changeStateMessage);

                }
            }
        }
    }
}
