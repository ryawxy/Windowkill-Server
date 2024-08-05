package model.Game.enemy;

import controller.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

public class Smiley extends GameObjects  {
    private boolean getCloser = true;
    private double angle;
    private final ArrayList<Rectangle> holes = new ArrayList<>();

    public Smiley() {
    }

    public Smiley(int x, int y) {
        super(x, y);
        setPreviousLocalFrame(getLocalFrame());
        getLocalFrames().add(getLocalFrame());
        setHP(300);
        setWidth(Constants.smileySize());
        setHeight(Constants.smileySize());
    }



    public ArrayList<Rectangle> getHoles() {
        return holes;
    }

    public boolean isGetCloser() {
        return getCloser;
    }

    public void setGetCloser(boolean getCloser) {
        this.getCloser = getCloser;
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }
}
