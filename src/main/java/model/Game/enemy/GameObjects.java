package model.Game.enemy;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import model.Game.ShotGun;
import model.enums.EnemyType;

import javax.swing.*;
import java.util.ArrayList;
import java.util.UUID;
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type",visible = true)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Trigorath.class,name = "trigorath"),
        @JsonSubTypes.Type(value = Squarantine.class,name = "squarantine"),
        @JsonSubTypes.Type(value = Necropick.class,name = "necropick"),
        @JsonSubTypes.Type(value = Omenoct.class,name = "omenoct"),
        @JsonSubTypes.Type(value = Barricados.class,name = "barricados"),
        @JsonSubTypes.Type(value = Smiley.class,name = "smiley"),
})
public class GameObjects {

    @JsonIgnore
    private int x;
    @JsonIgnore
    private int y;
    private int localX;
    private int localY;
    private int xVelocity;
    private int yVelocity;
    private int minimumWave;
    private String targetSquad;
    private int spawnNumber;
    private EnemyType enemyType;
    private UUID id;
    private int width;
    private int height;
    private boolean showCollectibles;
    private boolean attackByMelee;
    private int HP;
    private int XP_PER_COLLECTIBLE;
    @JsonIgnore
    private JFrame localFrame = new JFrame();
    @JsonIgnore
    private JFrame previousLocalFrame;
    @JsonIgnore
    private final ArrayList<JFrame> localFrames = new ArrayList<>();
    private boolean dead;
    private int numCollectibles;
    private int timer;
    private boolean visible;
    private int frameX;
    private int frameY;
    private int frameWidth;
    private int frameHeight;
    private String frameTitle;
    private int MAX_DIMENSION;

    public GameObjects() {
    }

    public GameObjects(int x, int y) {
        this.x = x;
        this.y = y;
        setX(x);
        setY(y);
        setLocalX(getX());
        setLocalY(getY());
        setId(UUID.randomUUID());
    }

    public int getLocalX() {
        return localX;
    }

    public void setLocalX(int localX) {
        this.localX = localX;
    }

    public int getLocalY() {
        return localY;
    }

    public void setLocalY(int localY) {
        this.localY = localY;
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

    public int getMinimumWave() {
        return minimumWave;
    }

    public void setMinimumWave(int minimumWave) {
        this.minimumWave = minimumWave;
    }

    public String getTargetSquad() {
        return targetSquad;
    }

    public void setTargetSquad(String targetSquad) {
        this.targetSquad = targetSquad;
    }

    public int getSpawnNumber() {
        return spawnNumber;
    }

    public void setSpawnNumber(int spawnNumber) {
        this.spawnNumber = spawnNumber;
    }

    public EnemyType getEnemyType() {
        return enemyType;
    }

    public void setEnemyType(EnemyType enemyType) {
        this.enemyType = enemyType;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
    @JsonIgnore

    public int getX() {
        return x;
    }
    @JsonIgnore

    public void setX(int x) {
        this.x = x;
    }
    @JsonIgnore

    public int getY() {
        return y;
    }
    @JsonIgnore

    public void setY(int y) {
        this.y = y;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isShowCollectibles() {
        return showCollectibles;
    }

    public void setShowCollectibles(boolean showCollectibles) {
        this.showCollectibles = showCollectibles;
    }

    public boolean isAttackByMelee() {
        return attackByMelee;
    }

    public void setAttackByMelee(boolean attackByMelee) {
        this.attackByMelee = attackByMelee;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getXP_PER_COLLECTIBLE() {
        return XP_PER_COLLECTIBLE;
    }

    public void setXP_PER_COLLECTIBLE(int XP_PER_COLLECTIBLE) {
        this.XP_PER_COLLECTIBLE = XP_PER_COLLECTIBLE;
    }
@JsonIgnore
    public JFrame getLocalFrame() {
        return localFrame;
    }
    @JsonIgnore

    public void setLocalFrame(JFrame localFrame) {
        this.localFrame = localFrame;
    }
    @JsonIgnore

    public JFrame getPreviousLocalFrame() {
        return previousLocalFrame;
    }
    @JsonIgnore

    public void setPreviousLocalFrame(JFrame previousLocalFrame) {
        this.previousLocalFrame = previousLocalFrame;
    }
    @JsonIgnore

    public ArrayList<JFrame> getLocalFrames() {
        return localFrames;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public int getNumCollectibles() {
        return numCollectibles;
    }

    public void setNumCollectibles(int numCollectibles) {
        this.numCollectibles = numCollectibles;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getFrameX() {
        return frameX;
    }

    public void setFrameX(int frameX) {
        this.frameX = frameX;
    }

    public int getFrameY() {
        return frameY;
    }

    public void setFrameY(int frameY) {
        this.frameY = frameY;
    }

    public int getFrameWidth() {
        return frameWidth;
    }

    public void setFrameWidth(int frameWidth) {
        this.frameWidth = frameWidth;
    }

    public int getFrameHeight() {
        return frameHeight;
    }

    public void setFrameHeight(int frameHeight) {
        this.frameHeight = frameHeight;
    }

    public String getFrameTitle() {
        return frameTitle;
    }

    public void setFrameTitle(String frameTitle) {
        this.frameTitle = frameTitle;
    }

    public int getMAX_DIMENSION() {
        return MAX_DIMENSION;
    }

    public void setMAX_DIMENSION(int MAX_DIMENSION) {
        this.MAX_DIMENSION = MAX_DIMENSION;
    }
}
