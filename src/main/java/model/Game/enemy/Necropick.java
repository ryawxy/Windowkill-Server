package model.Game.enemy;


import model.enums.EnemyType;

import java.util.Random;

public class Necropick extends GameObjects  {
    private  int currentTime;
    private boolean canMove;
    private boolean visible = true;
    private boolean showPortal;

    public Necropick() {
    }

    public Necropick(int x, int y) {
        super(x, y);
        setX(x);
        setY(y);
        setMinimumWave(1);
        setSpawnNumber(2);
        setHP(10);
        setEnemyType(EnemyType.Necropick);
        setMAX_DIMENSION(300);
    }

    @Override
    public boolean isVisible() {
        return visible;
    }
    @Override
    public int getWidth() {
        return 50;
    }

    @Override
    public int getHeight() {
        return 50;
    }
    @Override
    public int getNumCollectibles() {
        return 4;
    }

    public boolean isShowPortal() {
        return showPortal;
    }

    public  int getCurrentTime() {
        return currentTime;
    }

    public  void setCurrentTime(int currentTime) {
        this.currentTime = currentTime;
    }

    public boolean isCanMove() {
        return canMove;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public void setShowPortal(boolean showPortal) {
        this.showPortal = showPortal;
    }
}
