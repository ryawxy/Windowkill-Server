package model.Game;

import model.networkCommunication.Message.StartBattleMessage;
import myProject.MyProject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BattleCreator {

    public BattleCreator() {

    }
    public static void initiateBattle(){
        ArrayList<Squad> squads = new ArrayList<>(MyProject.getInstance().getDatabase().getSquadMap().values());
        Collections.shuffle(squads);
        List<Pair<Squad,Squad>> pairs = new ArrayList<>();

        for(int i=0;i<squads.size()-1;i++){
            pairs.add(new Pair<>(squads.get(i),squads.get(i+1)));
        }
       for(Pair<Squad,Squad> pair : pairs) notifyMembers(pair.getFirst(),pair.getSecond());
    }

    public static void notifyMembers(Squad squad1, Squad squad2){

        StartBattleMessage startBattleMessage1 = new StartBattleMessage();
        ArrayList<UserData> users = new ArrayList<>();
        for(String username : MyProject.getInstance().getDatabase().getSquadMap().get(squad2.getSquadName()).getMembers()){
            OnlineUser user = MyProject.getInstance().getDatabase().getAllUsers().get(username);
            UserData userData = new UserData();
            userData.setUsername(user.getUserData().getUsername());
            userData.setStatus(user.getUserData().getStatus());
            userData.setXP(user.getUserData().getXP());
            userData.setSquad(user.getUserData().getSquad());
            users.add(userData);
        }
        startBattleMessage1.setUsers(users);
        startBattleMessage1.setEnemySquad(squad2.getSquadName());
        startBattleMessage1.setSquad(squad1.getSquadName());

        for(String username : MyProject.getInstance().getDatabase().getSquadMap().get(squad1.getSquadName()).getMembers()){
            MyProject.getInstance().getDatabase().getClientHandlerMap().get(username).sendMessage(startBattleMessage1);
        }

        StartBattleMessage startBattleMessage = new StartBattleMessage();
         users = new ArrayList<>();
        for(String username : MyProject.getInstance().getDatabase().getSquadMap().get(squad1.getSquadName()).getMembers()){
            OnlineUser user = MyProject.getInstance().getDatabase().getAllUsers().get(username);
            UserData userData = new UserData();
            userData.setUsername(user.getUserData().getUsername());
            userData.setStatus(user.getUserData().getStatus());
            userData.setXP(user.getUserData().getXP());
            userData.setSquad(user.getUserData().getSquad());
            users.add(userData);
        }
        startBattleMessage.setUsers(users);
        startBattleMessage.setEnemySquad(squad1.getSquadName());
        startBattleMessage.setSquad(squad2.getSquadName());

        for(String username : MyProject.getInstance().getDatabase().getSquadMap().get(squad2.getSquadName()).getMembers()){
            MyProject.getInstance().getDatabase().getClientHandlerMap().get(username).sendMessage(startBattleMessage);
        }

    }

    public static class Pair<T,U> {
        private T first;
        private U second;

        public Pair(T first, U second){
            this.first = first;
            this.second = second;
        }

        public T getFirst() {
            return first;
        }

        public void setFirst(T first) {
            this.first = first;
        }

        public U getSecond() {
            return second;
        }

        public void setSecond(U second) {
            this.second = second;
        }
    }
}
