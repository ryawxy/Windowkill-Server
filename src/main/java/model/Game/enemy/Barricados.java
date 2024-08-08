package model.Game.enemy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import controller.Constants;
import model.enums.EnemyType;
import model.enums.GameMode;

import javax.swing.*;

public class Barricados extends GameObjects  {

    private final long currentTime = System.currentTimeMillis();
    private boolean visible = true;
    @JsonIgnore
    private JFrame itsFrame;

    public Barricados() {
    }

    public Barricados(int x, int y) {
        super(x, y);
        setPreviousLocalFrame(getLocalFrame());
        getLocalFrames().add(getLocalFrame());
        setMinimumWave(2);
        setSpawnNumber(1);
        setMAX_DIMENSION(1000);
        getBattles().add(String.valueOf(GameMode.COLOSSEUM));

        setEnemyType(EnemyType.Barricados2);
    }

    public void invisible(){

        if((System.currentTimeMillis()-currentTime)/1000>=120) visible = false;
    }


    @Override
    public int getWidth() {
        return Constants.barricadosWidth();
    }

    @Override
    public int getHeight() {
        return Constants.barricadosWidth();
    }

    @Override
    public boolean isVisible() {
        return visible;
    }

    @Override
    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @JsonIgnore
    public void setItsFrame(JFrame itsFrame) {
        this.itsFrame = itsFrame;
    }

    public long getCurrentTime() {
        return currentTime;
    }
    @JsonIgnore
    public JFrame getItsFrame() {
        return itsFrame;
    }
}
