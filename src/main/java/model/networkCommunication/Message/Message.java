package model.networkCommunication.Message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,property = "messageType",visible = true)
@JsonSubTypes({
@JsonSubTypes.Type(value = SignUPMessage.class, name = "SIGN_UP"),
@JsonSubTypes.Type(value = NewSquadMessage.class, name = "NEW_SQUAD"),
@JsonSubTypes.Type(value = ShowSquadsMessage.class, name = "SHOW_SQUADS"),
@JsonSubTypes.Type(value = JoinSquadRequestMessage.class, name = "JOIN_SQUAD"),

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
