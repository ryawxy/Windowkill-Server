package model.networkCommunication.Packet;

import model.enums.PacketType;

import java.util.ArrayList;
import java.util.UUID;

public class ShotGunPacket extends Packet{

    private int x;
    private int y;
    private UUID id;
    private int xVelocity;
    private int yVelocity;
    private String shooter;
    private ArrayList<String> users = new ArrayList<>();


    public ShotGunPacket() {
        setPacketType(PacketType.SHOTGUN);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public int getxVelocity() {
        return xVelocity;
    }

    public void setxVelocity(int xVelocity) {
        this.xVelocity = xVelocity;
    }

    public int getyVelocity() {
        return yVelocity;
    }

    public void setyVelocity(int yVelocity) {
        this.yVelocity = yVelocity;
    }

    public String getShooter() {
        return shooter;
    }

    public void setShooter(String shooter) {
        this.shooter = shooter;
    }

    public ArrayList<String> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<String> users) {
        this.users = users;
    }
}
