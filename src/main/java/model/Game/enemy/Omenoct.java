package model.Game.enemy;

import model.enums.EnemyType;

public class Omenoct extends GameObjects{
    private boolean visible = true;
    private boolean canMove;

    private double angle;
    private boolean canChoose = true;

    public Omenoct() {
    }

    public Omenoct(int x, int y) {
        super(x, y);
        setX(x);
        setY(y);
        setMinimumWave(1);
        setSpawnNumber(2);
        setEnemyType(EnemyType.Omenoct);
        setHP(20);
        setMAX_DIMENSION(300);

    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }
    @Override
    public int getWidth() {
        return 67;
    }

    @Override
    public int getHeight() {
        return 67;
    }

    @Override
    public int getNumCollectibles() {
        return 8;
    }
    public boolean isCanMove() {
        return canMove;
    }
    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }


    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public boolean isCanChoose() {
        return canChoose;
    }

    public void setCanChoose(boolean canChoose) {
        this.canChoose = canChoose;
    }
}
