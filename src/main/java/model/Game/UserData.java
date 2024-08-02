package model.Game;


import model.enums.Color;
import model.enums.UserStatus;
import model.enums.VaultItem;

import java.util.HashMap;


public class UserData {
    private String username;
    private int XP = 10000;
    private boolean isUserOnline;
    private String squad;
    private UserStatus status;
    private int vault;
    private int numberOfMembers;
    private int x;
    private int y;
    private model.enums.Color color;
    private HashMap<VaultItem,Integer> activatedItems = new HashMap<>();


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public boolean isUserOnline() {
        return isUserOnline;
    }

    public void setUserOnline(boolean userOnline) {
        isUserOnline = userOnline;
    }

    public String getSquad() {
        return squad;
    }

    public void setSquad(String squad) {
        this.squad = squad;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    public int getVault() {
        return vault;
    }

    public void setVault(int vault) {
        this.vault = vault;
    }

    public int getNumberOfMembers() {
        return numberOfMembers;
    }

    public void setNumberOfMembers(int numberOfMembers) {
        this.numberOfMembers = numberOfMembers;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public HashMap<VaultItem, Integer> getActivatedItems() {
        return activatedItems;
    }

    public void setActivatedItems(HashMap<VaultItem, Integer> activatedItems) {
        this.activatedItems = activatedItems;
    }
}
