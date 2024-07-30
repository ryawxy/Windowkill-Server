package controller.network.MessageHandler;

import controller.UsernameLogic;
import model.Game.OnlineUser;
import model.enums.MessageType;
import model.enums.UserStatus;
import model.networkCommunication.TCPClientHandler;
import model.networkCommunication.Message.Message;
import model.networkCommunication.Message.SignUPMessage;
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
            onlineUser.getUserData().setStatus(UserStatus.valueOf("online"));
            MyProject.getInstance().getDatabase().getAllUsers().put(onlineUser.getUserData().getUsername(),onlineUser);
            MyProject.getInstance().getDatabase().getMessageQueMap().put(onlineUser.getUserData().getUsername(),new ArrayList<>());


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
        }
    }
}
