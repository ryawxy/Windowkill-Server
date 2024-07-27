package controller;

import myProject.MyProject;

public class UsernameLogic {
    public boolean okToSignUp(String username){
        return !MyProject.getInstance().getDatabase().getAllUsers().containsKey(username);
    }
}
