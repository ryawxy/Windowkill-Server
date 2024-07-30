package model.networkCommunication.Message;

import model.enums.MessageType;

public class PayToVaultMessage extends Message{

    private int pay;
    private String username;
    private String squad;
    private String admin;
    private int totalXP;

    public PayToVaultMessage() {
        setMessageType(MessageType.PAY_TO_VAULT);
    }

    public int getPay() {
        return pay;
    }

    public void setPay(int pay) {
        this.pay = pay;
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

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public int getTotalXP() {
        return totalXP;
    }

    public void setTotalXP(int totalXP) {
        this.totalXP = totalXP;
    }
}
