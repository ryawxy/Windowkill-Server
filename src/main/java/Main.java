import model.networkCommunication.Server;
import myProject.MyProject;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        MyProject.getInstance();
        try {
            Server.getInstance().start();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
