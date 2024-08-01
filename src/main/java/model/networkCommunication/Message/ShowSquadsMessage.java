package model.networkCommunication.Message;

import java.util.AbstractList;
import java.util.ArrayList;

public class ShowSquadsMessage extends Message {


    private String username;
    private ArrayList<String> squads;
    private int XP;

    public ShowSquadsMessage() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ArrayList<String> getSquads() {
        return squads;
    }

    public void setSquads(ArrayList<String> squads) {
        this.squads = squads;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }
}
