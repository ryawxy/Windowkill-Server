package model.networkCommunication.Packet;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.networkCommunication.Packet.EpsilonPacket;
import model.networkCommunication.Packet.PacketType;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "messageType",visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = EpsilonPacket.class, name = "EPSILON"),
        @JsonSubTypes.Type(value = ShotGunPacket.class, name = "SHOTGUN"),
})
public class Packet {
    private PacketType packetType;
    private String sender;
    private int senderPort;

    public Packet() {
    }

    public PacketType getPacketType() {
        return packetType;
    }

    public void setPacketType(PacketType packetType) {
        this.packetType = packetType;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getSenderPort() {
        return senderPort;
    }

    public void setSenderPort(int senderPort) {
        this.senderPort = senderPort;
    }
}
