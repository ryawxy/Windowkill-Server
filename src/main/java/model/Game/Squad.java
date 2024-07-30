package model.Game;

import java.util.ArrayList;

public class Squad {
    private String squadName;
    private String admin;
    private ArrayList<String> members;
    private int vault;

    public Squad(String squadName,String admin) {
        this.squadName = squadName;
        this.admin = admin;
        members = new ArrayList<>();


    }

    public String getSquadName() {
        return squadName;
    }

    public void setSquadName(String squadName) {
        this.squadName = squadName;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public ArrayList<String> getMembers() {
        return members;
    }

    public void setMembers(ArrayList<String> members) {
        this.members = members;
    }

    public int getVault() {
        return vault;
    }

    public void setVault(int vault) {
        this.vault = vault;
    }
}
