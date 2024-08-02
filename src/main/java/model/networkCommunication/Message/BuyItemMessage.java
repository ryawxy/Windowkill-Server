package model.networkCommunication.Message;

import model.enums.MessageType;
import model.enums.VaultItem;

public class BuyItemMessage extends Message{
    private String sender;
    private String Squad;
    private VaultItem vaultItem;

    public BuyItemMessage() {
        setMessageType(MessageType.BUY_ITEM);
    }

    @Override
    public String getSender() {
        return sender;
    }

    @Override
    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSquad() {
        return Squad;
    }

    public void setSquad(String squad) {
        Squad = squad;
    }

    public VaultItem getVaultItem() {
        return vaultItem;
    }

    public void setVaultItem(VaultItem vaultItem) {
        this.vaultItem = vaultItem;
    }
}
