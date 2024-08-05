package controller.WaveController;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.Game.Game;
import model.Game.enemy.GameObjects;
import model.enums.Attack;
import model.enums.GameMode;
import model.networkCommunication.Packet.EnemyPacket;
import model.networkCommunication.TCPClientHandler;
import model.networkCommunication.UDPClientHandler;
import myProject.MyProject;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.net.SocketException;
import java.util.*;

public class WaveManager {

    private final List<Class<? extends GameObjects>> enemyClasses;
    private final Random random;
    private final Game game;
    private final ArrayList<GameObjects> killedEnemies = new ArrayList<>();
    private int time;
    private int waveNum = 1;
    private final List<GameObjects> wave = new ArrayList<>();
    private final ArrayList<Attack> bossAttacks = new ArrayList<>();
    public WaveManager(Game game){
        enemyClasses = WaveData.enemyClasses(game.getBattleMode());
        random = new Random();
        this.game = game;
        bossAttacks.add(Attack.POWERPUNCH);
        bossAttacks.add(Attack.QUAKE);
        bossAttacks.add(Attack.PROJECTILE);
        bossAttacks.add(Attack.RAPIDFIRE);
        bossAttacks.add(Attack.SQUEEZE);
        bossAttacks.add(Attack.ANNIHILATOR);
        bossAttacks.add(Attack.SLAP);
        bossAttacks.add(Attack.VOMIT);
    }
    public void generateWave(int waveNumber) {

        time++;
        System.out.println(time);
        if (waveNumber == waveNum) {

            if (time >= 3000) {
                if (killedEnemies.isEmpty()) {
                    wave.clear();
                    int index = random.nextInt(enemyClasses.size());
                    Class<? extends GameObjects> enemyClass = enemyClasses.get(index);

                    try {
                        Field minWaveField = enemyClass.getSuperclass().getDeclaredField("minimumWave");
                        minWaveField.setAccessible(true);
                        Constructor<? extends GameObjects> constructor = enemyClass.getConstructor(int.class, int.class);
                        GameObjects tempGameObjects = constructor.newInstance(0, 0);
                        int minWave = tempGameObjects.getMinimumWave();
                        if (waveNumber >= minWave) {
                            int numberOfEnemies = tempGameObjects.getSpawnNumber();
                            for (int i = 0; i < 1; i++) {
                                int x = random.nextInt(tempGameObjects.getMAX_DIMENSION());
                                int y = random.nextInt(tempGameObjects.getMAX_DIMENSION());
                                GameObjects gameObjects = constructor.newInstance(x, y);
                                gameObjects.setVisible(true);
                                int target = random.nextInt(2);
                                gameObjects.setTargetSquad(MyProject.getInstance().getDatabase().getAllUsers().get(game.getPlayers().get(target)).getUserData().getSquad());
                                wave.add(gameObjects);
                            }
                        }
                    } catch (NoSuchFieldException | NoSuchMethodException | InvocationTargetException |
                             InstantiationException |
                             IllegalAccessException e) {
                        throw new RuntimeException(e);
                    }
                    sendPacket(wave);
                    time = 0;

                }
            }
            if(!killedEnemies.isEmpty() && !game.getBattleMode().equals(String.valueOf(GameMode.MONOMACHIA))) {
                if (killedEnemies.size() == wave.size()) {
                    waveNum++;
                    time = 0;
                }
            }
        }
    }


    private void sendPacket(List<GameObjects> waveGameObjects){
        EnemyPacket packet = new EnemyPacket();
        packet.setEnemies(waveGameObjects);
        packet.setSender("server");

        for(String user : MyProject.getInstance().getDatabase().getClientHandlerMap().keySet()){
            if(game.getPlayers().contains(user)){

        try {
            UDPClientHandler.getInstance().broadcastMessage(packet,12345,MyProject.getInstance().getDatabase().getClientHandlerMap().get(user).getUdpPort());
        } catch (JsonProcessingException | SocketException e) {
            throw new RuntimeException(e);
        }
    }
        }
    }

    public ArrayList<GameObjects> getKilledEnemies() {
        return killedEnemies;
    }
}
