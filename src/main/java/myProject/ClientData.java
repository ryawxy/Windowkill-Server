package myProject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Game.OnlineUser;
import model.Game.UserData;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ClientData {

    private UserData userData;

    public ClientData() {
    }

    public ClientData(UserData userData) {
        this.userData = userData;
    }

    public void saveData() {
        while (true) {
            System.out.println(1111);
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
            objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
            try {
                objectMapper.writeValue(new FileWriter(new File(userData.getUsername() + ".json")), this);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
