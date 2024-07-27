package model.Game;

public class UserData {
    private String username;
    private int XP;
    private boolean isUserOnline;

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
}
