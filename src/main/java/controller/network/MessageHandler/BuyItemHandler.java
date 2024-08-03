package controller.network.MessageHandler;

import model.Game.OnlineUser;
import model.Game.Squad;
import model.networkCommunication.Message.BuyItemMessage;
import model.networkCommunication.Message.Message;
import myProject.MyProject;

public class BuyItemHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message) {

        BuyItemMessage buyItemMessage = (BuyItemMessage) message;
        Squad squad = MyProject.getInstance().getDatabase().getSquadMap().get(buyItemMessage.getSquad());
        for(OnlineUser onlineUser : MyProject.getInstance().getDatabase().getAllUsers().values()){
            if(squad.getMembers().contains(onlineUser.getUserData().getUsername())){
                System.out.println( onlineUser.getUserData().getActivatedItems());
                System.out.println(buyItemMessage.getVaultItem());
             int number = onlineUser.getUserData().getActivatedItems().get(buyItemMessage.getVaultItem());
             onlineUser.getUserData().getActivatedItems().put(buyItemMessage.getVaultItem(),number+1);
            }
          int number1 = squad.getVaultItems().get(buyItemMessage.getVaultItem());
            squad.getVaultItems().put(buyItemMessage.getVaultItem(),number1+1);
        }
        for(String member : squad.getMembers()) MyProject.getInstance().getDatabase().getClientHandlerMap().get(member).sendMessage(buyItemMessage);
    }
}
