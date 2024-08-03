package model.networkCommunication.Packet;


import model.enums.PacketType;

public class EpsilonPacket extends Packet {

    private int x;
    private int y;
    private int XP;
    private int HP;

    public EpsilonPacket() {
        setPacketType(PacketType.EPSILON);
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

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }
}
