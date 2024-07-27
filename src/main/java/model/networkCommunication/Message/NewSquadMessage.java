package model.networkCommunication.Message;

public class NewSquadMessage extends Message {

    private String squadName;
    private String admin;

    public NewSquadMessage() {
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
}
