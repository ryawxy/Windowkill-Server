package controller.network.MessageHandler;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.Game.Squad;
import model.networkCommunication.Message.Message;
import model.networkCommunication.Message.PayToVaultMessage;
import myProject.MyProject;

public class PayToVaultHandler implements MessageHandler{
    @Override
    public void handleMessage(Message message)  {

        if(message instanceof PayToVaultMessage payToVaultMessage){
            for(Squad squad : MyProject.getInstance().getDatabase().getSquadMap().values()){
                if(payToVaultMessage.getSquad().equals(squad.getSquadName())){
                    squad.setVault(squad.getVault()+ payToVaultMessage.getPay());
                    payToVaultMessage.setAdmin(squad.getAdmin());
                    payToVaultMessage.setTotalXP(squad.getVault());
                    for(String member : squad.getMembers()){
                        MyProject.getInstance().getDatabase().getClientHandlerMap().get(member).sendMessage(payToVaultMessage);
                    }
                }
            }
        }

    }
}
