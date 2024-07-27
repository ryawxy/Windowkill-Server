package myProject;

import java.util.ArrayList;

public class MyProject {

    private Database database;
    private static MyProject instance;

    public MyProject(){
     database = new Database();
     for(String user : database.getAllUsers().keySet()) database.getMessageQueMap().put(user,new ArrayList<>());
    }

    public static synchronized MyProject getInstance() {
        if(instance == null) instance = new MyProject();
        return instance;
    }

    public synchronized Database getDatabase() {
        return database;
    }

    public void setDatabase(Database database) {
        this.database = database;
    }
}
