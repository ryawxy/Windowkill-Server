package controller.network.MessageHandler;

import model.LeaderboardData;
import model.networkCommunication.Message.Message;
import model.networkCommunication.Message.PreviousDataMessage;
import myProject.MyProject;

public class PreviousDataHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message) {

        PreviousDataMessage previousDataMessage = (PreviousDataMessage) message;
        for(LeaderboardData data : previousDataMessage.getData()) MyProject.getInstance().getDatabase().getGameResults().add(data);
    }
}
