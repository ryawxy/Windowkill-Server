package controller.WaveController;

import model.Game.Game;
import model.Game.enemy.*;
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
//            enemyClasses.add(Squarantine.class);
//            enemyClasses.add(Omenoct.class);
//            enemyClasses.add(Barricados.class);
        }else if(battleMode.equals(String.valueOf(GameMode.COLOSSEUM))){
            enemyClasses.add(Omenoct.class);
            enemyClasses.add(Necropick.class);
            enemyClasses.add(Barricados.class);
        }
        return enemyClasses;
    }

}
