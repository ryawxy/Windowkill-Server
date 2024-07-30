package model.networkCommunication.Message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.enums.MessageType;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,property = "messageType",visible = true)
@JsonSubTypes({
@JsonSubTypes.Type(value = SignUPMessage.class, name = "SIGN_UP"),
@JsonSubTypes.Type(value = NewSquadMessage.class, name = "NEW_SQUAD"),
@JsonSubTypes.Type(value = ShowSquadsMessage.class, name = "SHOW_SQUADS"),
@JsonSubTypes.Type(value = JoinSquadRequestMessage.class, name = "JOIN_SQUAD"),
@JsonSubTypes.Type(value = RequestToAdminMessage.class, name = "REQUEST_TO_ADMIN"),
@JsonSubTypes.Type(value = LeaveSquadMessage.class, name = "LEAVE_SQUAD"),
@JsonSubTypes.Type(value = SquadDataMessage.class, name = "SQUAD_DATA"),
@JsonSubTypes.Type(value = ServerConnectionMessage.class, name = "SERVER_CONNECTION"),
@JsonSubTypes.Type(value = RemoveMemberMessage.class, name = "REMOVE_MEMBER"),
@JsonSubTypes.Type(value = PayToVaultMessage.class, name = "PAY_TO_VAULT"),
@JsonSubTypes.Type(value = InitMessage.class, name = "INIT"),
})

public class Message {
    private MessageType messageType;
    private String sender;
    private String target;
    public Message(){

    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
