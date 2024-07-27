package model.Game;

public class OnlineUser {

    private UserData userData;

    public OnlineUser() {
        userData = new UserData();
    }

    public UserData getUserData() {
        return userData;
    }

    public void setUserData(UserData userData) {
        this.userData = userData;
    }
}
