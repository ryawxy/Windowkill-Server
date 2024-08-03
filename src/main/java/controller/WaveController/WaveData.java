package controller.WaveController;

import model.Game.enemy.GameObjects;
import model.Game.enemy.Trigorath;
import model.enums.GameMode;

import java.util.ArrayList;
import java.util.List;

public class WaveData {

    public WaveData(){

    }

    public static List<Class<? extends GameObjects>> enemyClasses(String battleMode){
        List< Class<? extends GameObjects>> enemyClasses = new ArrayList<>();

        if(battleMode.equals(String.valueOf(GameMode.MONOMACHIA))){
            enemyClasses.add(Trigorath.class);
        }
        return enemyClasses;
    }

}
