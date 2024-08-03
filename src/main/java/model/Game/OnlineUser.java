package model.Game;

public class OnlineUser {

    private UserData userData = new UserData();

    public OnlineUser() {
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
