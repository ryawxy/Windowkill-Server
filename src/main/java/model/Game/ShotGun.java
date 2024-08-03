package model.Game;

import java.util.UUID;

public class ShotGun {
    private int x;
    private int y;
    private int xVelocity;
    private int yVelocity;
    private UUID id;
    private String shooter;

    public ShotGun() {
        setShooter("server");
        setId(UUID.randomUUID());

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

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getShooter() {
        return shooter;
    }

    public void setShooter(String shooter) {
        this.shooter = shooter;
    }
}
