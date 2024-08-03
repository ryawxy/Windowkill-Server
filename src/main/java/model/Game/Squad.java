package model.Game;

import model.enums.VaultItem;

import java.util.ArrayList;
import java.util.HashMap;

public class Squad {
    private String squadName;
    private String admin;
    private ArrayList<String> members = new ArrayList<>();
    private HashMap<String, Integer> membersXP = new HashMap<>();
    private HashMap<String,String> membersStatus = new HashMap<>();
    private int vault;
    private HashMap<VaultItem,Integer> vaultItems = new HashMap<>();
    private int monomachiaWin;
    private int totalXP;

    public Squad(String squadName,String admin) {
        this.squadName = squadName;
        this.admin = admin;

        vaultItems.put(VaultItem.CallOfPalioxis,0);
        vaultItems.put(VaultItem.CallOfAdonis,0);
        vaultItems.put(VaultItem.CallOfGefjon,0);

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

    public HashMap<String, Integer> getMembersXP() {
        return membersXP;
    }

    public void setMembersXP(HashMap<String, Integer> membersXP) {
        this.membersXP = membersXP;
    }

    public HashMap<String, String> getMembersStatus() {
        return membersStatus;
    }

    public void setMembersStatus(HashMap<String, String> membersStatus) {
        this.membersStatus = membersStatus;
    }

    public HashMap<VaultItem, Integer> getVaultItems() {
        return vaultItems;
    }

    public void setVaultItems(HashMap<VaultItem, Integer> vaultItems) {
        this.vaultItems = vaultItems;
    }

    public int getMonomachiaWin() {
        return monomachiaWin;
    }

    public void setMonomachiaWin(int monomachiaWin) {
        this.monomachiaWin = monomachiaWin;
    }

    public int getTotalXP() {
        return totalXP;
    }

    public void setTotalXP(int totalXP) {
        this.totalXP = totalXP;
    }
}
