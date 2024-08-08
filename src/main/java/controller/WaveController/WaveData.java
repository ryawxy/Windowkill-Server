package controller.WaveController;

import model.Game.enemy.*;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class WaveData {

    public WaveData(){
    }

    public static Set<Class<? extends GameObjects>> getEnemyClasses(){
        Reflections reflections = new Reflections("model.Game.enemy");
        return reflections.getSubTypesOf(GameObjects.class);
    }
    public static List<Class<? extends GameObjects>> getBattleModeEnemies(String mode){
        Set<Class<? extends GameObjects>> enemyClasses = getEnemyClasses();
        return enemyClasses.stream().filter(enemyClass -> {
            try{
                Constructor<?extends GameObjects> constructor = enemyClass.getConstructor(int.class,int.class);
                GameObjects enemyInstance = constructor.newInstance(0,0);
                ArrayList<String> modes = enemyInstance.getBattles();
                return modes.contains(mode);
            }catch (Exception e){
               return false;
            }
        }).collect(Collectors.toList());
    }

//    public static List<Class<? extends GameObjects>> enemyClasses(String battleMode){
//        List< Class<? extends GameObjects>> enemyClasses = new ArrayList<>();
//
//        if(battleMode.equals(String.valueOf(GameMode.MONOMACHIA))){
//                enemyClasses.add(Trigorath.class);
//            enemyClasses.add(Squarantine.class);
//            enemyClasses.add(Omenoct.class);
//            enemyClasses.add(Barricados.class);
//        }else if(battleMode.equals(String.valueOf(GameMode.COLOSSEUM))){
//            enemyClasses.add(Omenoct.class);
//            enemyClasses.add(Necropick.class);
//            enemyClasses.add(Barricados.class);
//        }
//        return enemyClasses;
//    }

}
