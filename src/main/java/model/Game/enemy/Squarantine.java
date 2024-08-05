package model.Game.enemy;

import model.enums.EnemyType;

public class Squarantine extends GameObjects {

    private int HPPerAttack;
    // number of HP it costs from epsilon per each attack

    private int epsilonXPos;
    private int epsilonYPos;
    private int angle;
    private int speed;

    private boolean dead;

    private int timer;
    // show collectibles only for 10 seconds
    private static int HPDecrement = 5;
    private static int HPDecrement2 = 10;

    public Squarantine() {
    }

    public Squarantine(int x, int y) {
        super(x, y);
        setMinimumWave(1);
        setSpawnNumber(2);
        setHP(10);
        setEnemyType(EnemyType.Squarantine);
        setMAX_DIMENSION(300);
    }


    public int getHPPerAttack() {
        return HPPerAttack;
    }

    public void setHPPerAttack(int HPPerAttack) {
        this.HPPerAttack = HPPerAttack;
    }

    public int getEpsilonXPos() {
        return epsilonXPos;
    }

    public void setEpsilonXPos(int epsilonXPos) {
        this.epsilonXPos = epsilonXPos;
    }

    public int getEpsilonYPos() {
        return epsilonYPos;
    }

    public void setEpsilonYPos(int epsilonYPos) {
        this.epsilonYPos = epsilonYPos;
    }

    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    @Override
    public boolean isDead() {
        return dead;
    }

    @Override
    public void setDead(boolean dead) {
        this.dead = dead;
    }

    @Override
    public int getTimer() {
        return timer;
    }

    @Override
    public void setTimer(int timer) {
        this.timer = timer;
    }

    public static int getHPDecrement() {
        return HPDecrement;
    }

    public static void setHPDecrement(int HPDecrement) {
        Squarantine.HPDecrement = HPDecrement;
    }

    public static int getHPDecrement2() {
        return HPDecrement2;
    }

    public static void setHPDecrement2(int HPDecrement2) {
        Squarantine.HPDecrement2 = HPDecrement2;
    }
}
