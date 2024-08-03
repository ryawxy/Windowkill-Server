package controller.WaveController;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.Game.Game;
import model.Game.enemy.GameObjects;
import model.networkCommunication.Packet.EnemyPacket;
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
    public WaveManager(Game game){
        enemyClasses = WaveData.enemyClasses(game.getBattleMode());
        random = new Random();
        this.game = game;
    }
    public void generateWave(int waveNumber){
        List<GameObjects> wave = new ArrayList<>();
        int index = random.nextInt(enemyClasses.size());
        Class<? extends GameObjects> enemyClass = enemyClasses.get(index);

        try {
            Field minWaveField = enemyClass.getSuperclass().getDeclaredField("minimumWave");
            minWaveField.setAccessible(true);
            Constructor<? extends GameObjects> constructor = enemyClass.getConstructor(int.class,int.class);
            GameObjects tempGameObjects = constructor.newInstance(0,0);
            int minWave = tempGameObjects.getMinimumWave();
            if(waveNumber>= minWave){
                int numberOfEnemies = tempGameObjects.getSpawnNumber();
                for(int i=0;i<numberOfEnemies;i++){
                    int x = random.nextInt(300);
                    int y = random.nextInt(300);
                    GameObjects gameObjects = constructor.newInstance(x,y);
                    gameObjects.setVisible(true);
                    gameObjects.setTargetSquad(MyProject.getInstance().getDatabase().getAllUsers().get(game.getPlayers().get(i)).getUserData().getSquad());
                    wave.add(gameObjects);
                }
            }
        } catch (NoSuchFieldException | NoSuchMethodException | InvocationTargetException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        sendPacket(wave);
    }
    private void sendPacket(List<GameObjects> waveGameObjects){
        EnemyPacket packet = new EnemyPacket();
        packet.setEnemies(waveGameObjects);
        packet.setSender("server");
        try {
            UDPClientHandler.getInstance().broadcastMessage(packet,12345);
        } catch (JsonProcessingException | SocketException e) {
            throw new RuntimeException(e);
        }
    }

}
