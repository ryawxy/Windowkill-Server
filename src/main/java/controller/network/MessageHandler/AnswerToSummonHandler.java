package controller.network.MessageHandler;


import model.networkCommunication.Message.AnswerToSummonMessage;
import model.networkCommunication.Message.Message;
import model.networkCommunication.Message.SummonMessage;
import myProject.MyProject;

public class AnswerToSummonHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message) {

        AnswerToSummonMessage answer = (AnswerToSummonMessage) message;
        sendResultMessage(answer);
    }
    private void sendResultMessage(AnswerToSummonMessage answer){
        SummonMessage result = new SummonMessage();
        result.setSender(answer.getSender());
        result.setTarget(answer.getTarget());
        result.setAccepted(answer.isAccepted());
        MyProject.getInstance().getDatabase().getClientHandlerMap().get(result.getTarget()).sendMessage(result);
    }
    }

