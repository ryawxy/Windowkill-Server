package controller.network.MessageHandler;

import controller.UsernameLogic;
import model.Game.OnlineUser;
import model.networkCommunication.ClientHandler;
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
            MyProject.getInstance().getDatabase().getAllUsers().put(onlineUser.getUserData().getUsername(),onlineUser);
            MyProject.getInstance().getDatabase().getMessageQueMap().put(onlineUser.getUserData().getUsername(),new ArrayList<>());

            ClientHandler handler = MyProject.getInstance().getDatabase().getClientHandlerMap().get("");
            MyProject.getInstance().getDatabase().getClientHandlerMap().remove("");
            MyProject.getInstance().getDatabase().getClientHandlerMap().put(onlineUser.getUserData().getUsername(),handler);
            MyProject.getInstance().getDatabase().getClientHandlerMap().get(onlineUser.getUserData().getUsername()).sendMessage(message);
        }
    }
}
