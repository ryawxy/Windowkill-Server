package controller.network.MessageHandler;

import controller.UsernameLogic;
import model.Game.BattleHandler;
import model.Game.OnlineUser;
import model.Game.Squad;
import model.enums.MessageType;
import model.enums.UserStatus;
import model.networkCommunication.Message.ChangeStateMessage;
import model.networkCommunication.TCPClientHandler;
import model.networkCommunication.Message.Message;
import model.networkCommunication.Message.SignUPMessage;
import myProject.ClientData;
import myProject.MyProject;

import java.util.ArrayList;

public class SignUpHandler implements MessageHandler{
    private final UsernameLogic usernameLogic;

    public SignUpHandler() {
        usernameLogic = new UsernameLogic();
    }

    @Override
    public void handleMessage(Message message) {

        if(usernameLogic.okToSignUp(((SignUPMessage)message).getUsername())){
            OnlineUser onlineUser = new OnlineUser();
            onlineUser.getUserData().setUsername(((SignUPMessage)message).getUsername());
            onlineUser.getUserData().setStatus(UserStatus.valueOf("Online"));
            MyProject.getInstance().getDatabase().getAllUsers().put(onlineUser.getUserData().getUsername(),onlineUser);
            MyProject.getInstance().getDatabase().getMessageQueMap().put(onlineUser.getUserData().getUsername(),new ArrayList<>());

            ClientData clientData = new ClientData(onlineUser.getUserData());
            MyProject.getInstance().getDatabase().getClientsData().add(clientData);

            TCPClientHandler handler = MyProject.getInstance().getDatabase().getClientHandlerMap().get("");
            MyProject.getInstance().getDatabase().getClientHandlerMap().remove("");
            MyProject.getInstance().getDatabase().getClientHandlerMap().put(onlineUser.getUserData().getUsername(),handler);
            MyProject.getInstance().getDatabase().getClientHandlerMap().get(onlineUser.getUserData().getUsername()).sendMessage(message);


            for(String username : MyProject.getInstance().getDatabase().getClientHandlerMap().keySet()){
                if(!username.equals(onlineUser.getUserData().getUsername())){
                    MyProject.getInstance().getDatabase().getClientHandlerMap().get(username).sendMessage(message);
                    SignUPMessage signUPMessage = new SignUPMessage();
                    signUPMessage.setMessageType(MessageType.SIGN_UP);
                    signUPMessage.setUsername(username);
                    MyProject.getInstance().getDatabase().getClientHandlerMap().get(onlineUser.getUserData().getUsername()).sendMessage(signUPMessage);
                }
            }

        }else{
            TCPClientHandler handler = MyProject.getInstance().getDatabase().getClientHandlerMap().get("");
            MyProject.getInstance().getDatabase().getClientHandlerMap().put(((SignUPMessage) message).getUsername(),handler);
            SignUPMessage signUPMessage = new SignUPMessage();
            signUPMessage.setMessageType(MessageType.SIGN_UP);
            signUPMessage.setUsername(((SignUPMessage) message).getUsername());
            signUPMessage.setSignedIn(true);
            signUPMessage.setBattleStarted(true);

            MyProject.getInstance().getDatabase().getAllUsers().get(((SignUPMessage) message).getUsername()).getUserData().setStatus(UserStatus.Online);
            if(MyProject.getInstance().getDatabase().getSquadMap().get(MyProject.getInstance().getDatabase().
                    getAllUsers().get(((SignUPMessage) message).getUsername()).getUserData().getSquad())!=null) {
                for (String username : MyProject.getInstance().getDatabase().getSquadMap().get(MyProject.getInstance().getDatabase().
                        getAllUsers().get(((SignUPMessage) message).getUsername()).getUserData().getSquad()).getMembers()) {
                    signUPMessage.getMembersStatus().put(username, String.valueOf(MyProject.getInstance().getDatabase().getAllUsers().get(username).getUserData().getStatus()));
                    signUPMessage.getMembersXP().put(username, MyProject.getInstance().getDatabase().getAllUsers().get(username).getUserData().getXP());
                }
            }
            for(BattleHandler.Pair<Squad,Squad> pair : BattleHandler.getPairs()){
                if(pair.first().getSquadName().equals(MyProject.getInstance().getDatabase().getAllUsers().get(((SignUPMessage) message).getUsername()).getUserData().getSquad())){
                    for(String enemy : pair.second().getMembers()){
                        signUPMessage.getEnemyStatus().put(enemy,String.valueOf(MyProject.getInstance().getDatabase().getAllUsers().get(enemy).getUserData().getStatus()));
                        signUPMessage.getEnemyXP().put(enemy,MyProject.getInstance().getDatabase().getAllUsers().get(enemy).getUserData().getXP());

                    }
                }else if(pair.second().getSquadName().equals(MyProject.getInstance().getDatabase().getAllUsers().get(((SignUPMessage) message).getUsername()).getUserData().getSquad())){
                    for(String enemy : pair.first().getMembers()){
                        signUPMessage.getEnemyStatus().put(enemy,String.valueOf(MyProject.getInstance().getDatabase().getAllUsers().get(enemy).getUserData().getStatus()));
                        signUPMessage.getEnemyXP().put(enemy,MyProject.getInstance().getDatabase().getAllUsers().get(enemy).getUserData().getXP());
                    }
                }
            }

            signUPMessage.setUserData(MyProject.getInstance().getDatabase().getAllUsers().get(((SignUPMessage) message).getUsername()).getUserData());
            MyProject.getInstance().getDatabase().getClientHandlerMap().get(((SignUPMessage) message).getUsername()).sendMessage(signUPMessage);

            sendChangeStateMessage(((SignUPMessage) message).getUsername());
        }
    }
    private void sendChangeStateMessage(String username){
        ChangeStateMessage changeStateMessage = new ChangeStateMessage();
        changeStateMessage.setState("Online");
        changeStateMessage.setUsername(username);

        for(String user : MyProject.getInstance().getDatabase().getClientHandlerMap().keySet())
            if(!user.equals(username))
                MyProject.getInstance().getDatabase().getClientHandlerMap().get(user).sendMessage(changeStateMessage);
    }
}
