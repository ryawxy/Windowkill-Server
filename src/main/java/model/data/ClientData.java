package model.data;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Game.OnlineUser;
import model.Game.UserData;
import myProject.MyProject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ClientData {

    private UserData userData;
//    private String username;
//    private String squad;
//    private int XP;

    public ClientData() {
    }

    public ClientData(UserData userData) {
        this.userData = userData;
    }

    public void saveData() {


        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        try {
            objectMapper.writeValue(new FileWriter(new File("src/main/java/clientsData/" + userData.getUsername() + ".json")), this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void loadData(){

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);

        File directory = new File("src/main/java/clientsData");
        if(directory.exists() && directory.isDirectory()){
            File[] files = directory.listFiles((die, name) -> name.endsWith(".json"));

          Map<String,OnlineUser> allUsers = new ConcurrentHashMap<>();
            if(files != null){

                for(File file : files){
                    try{
                        ClientData clientData = objectMapper.readValue(new File(file.getPath()), ClientData.class);
                        OnlineUser onlineUser = new OnlineUser();
                        onlineUser.setUserData(clientData.getUserData());

                        allUsers.put(clientData.userData.getUsername(),onlineUser);
                        MyProject.getInstance().getDatabase().getClientsData().add(clientData);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
                MyProject.getInstance().getDatabase().setAllUsers(allUsers);
                System.out.println(MyProject.getInstance().getDatabase().getAllUsers().size());
            }
        }
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
