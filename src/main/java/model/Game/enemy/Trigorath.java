package model.Game.enemy;

import model.enums.EnemyType;
import model.enums.GameMode;

public class Trigorath extends GameObjects {

    private int angle;
    private int speed;
    private int timer;
    // show collectibles only for 10 seconds

    private static  int HPDecrement = 5;
    private static  int HPDecrement2 = 5;

    public Trigorath() {
    }

    public Trigorath(int x, int y) {
        super(x, y);
        setMinimumWave(0);
        setSpawnNumber(2);
        setHP(15);
        setEnemyType(EnemyType.Trigorath);
        setMAX_DIMENSION(300);
        getBattles().add(String.valueOf(GameMode.MONOMACHIA));
        getBattles().add(String.valueOf(GameMode.COLOSSEUM));
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
        Trigorath.HPDecrement = HPDecrement;
    }

    public static int getHPDecrement2() {
        return HPDecrement2;
    }

    public static void setHPDecrement2(int HPDecrement2) {
        Trigorath.HPDecrement2 = HPDecrement2;
    }
}
