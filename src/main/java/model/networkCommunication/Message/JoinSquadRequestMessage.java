package model.networkCommunication.Message;

public class JoinSquadRequestMessage extends Message{
    private String username;
    private String squad;
    private boolean isJoined;

    public JoinSquadRequestMessage() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSquad() {
        return squad;
    }

    public void setSquad(String squad) {
        this.squad = squad;
    }

    public boolean isJoined() {
        return isJoined;
    }

    public void setJoined(boolean joined) {
        isJoined = joined;
    }
}
