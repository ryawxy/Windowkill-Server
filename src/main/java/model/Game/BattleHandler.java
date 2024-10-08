package model.Game;

import com.fasterxml.jackson.core.JsonProcessingException;
import controller.Constants;
import model.data.BattleHistory;
import model.enums.VaultItem;
import model.networkCommunication.Message.ChangeUserDataMessage;
import model.networkCommunication.Message.EndBattleMessage;
import model.networkCommunication.Message.StartBattleMessage;
import myProject.MyProject;

import java.util.*;

public class BattleHandler {
    private static final List<Pair<Squad, Squad>> pairs = new ArrayList<>();
    private static final List<Squad> winners = new ArrayList<>();
    private static final List<Squad> losers = new ArrayList<>();
    public BattleHandler() {

    }
    public static void initiateBattle() throws JsonProcessingException {
        System.out.println("**************");
        pairs.clear();
        winners.clear();
        losers.clear();
        ArrayList<Squad> squads = new ArrayList<>(MyProject.getInstance().getDatabase().getSquadMap().values());
        Collections.shuffle(squads);


        for(int i=0;i<squads.size()-1;i++){
            pairs.add(new Pair<>(squads.get(i),squads.get(i+1)));
        }
       for(Pair<Squad,Squad> pair : pairs) notifyMembers(pair.first(),pair.second());
       MyProject.getInstance().setBattleStarted(true);
    }
    public static void notifyMembers(Squad squad1, Squad squad2) throws JsonProcessingException {

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
    public static void terminateBattle() throws JsonProcessingException {

        System.out.println(5555);
        for(OnlineUser onlineUser : MyProject.getInstance().getDatabase().getAllUsers().values()){
            onlineUser.getUserData().setPlayedMonomachia(false);
            onlineUser.getUserData().setPlayedColosseum(false);
        }
        MyProject.getInstance().setBattleStarted(false);
        for(Pair<Squad,Squad> pair : pairs){
            BattleHistory battleHistory = new BattleHistory();
            battleHistory.setS1(pair.first.getSquadName());
            battleHistory.setS2(pair.second.getSquadName());
            if(pair.first().getTotalXP()>pair.second().getTotalXP()){
                winners.add(pair.first());
                battleHistory.setWinner(pair.first.getSquadName());

            }
            else if(pair.first().getTotalXP()<pair.second().getTotalXP()){
                winners.add(pair.second());
                battleHistory.setWinner(pair.second.getSquadName());
            }
            else{
                if(pair.first().getMonomachiaWin()>pair.second().getMonomachiaWin()){
                    winners.add(pair.first());
                    battleHistory.setWinner(pair.first.getSquadName());
                }
                else if(pair.first().getMonomachiaWin()<pair.second().getMonomachiaWin()){
                    winners.add(pair.second());
                    battleHistory.setWinner(pair.second.getSquadName());
                }
                else{
                    if(pair.first().getVaultItems().get(VaultItem.CallOfGefjon)>0 &&
                            pair.second().getVaultItems().get(VaultItem.CallOfGefjon)<=0){
                        winners.add(pair.first());
                        battleHistory.setWinner(pair.first.getSquadName());
                    }
                    else if(pair.first().getVaultItems().get(VaultItem.CallOfGefjon)<=0 &&
                            pair.second().getVaultItems().get(VaultItem.CallOfGefjon)>0){
                        winners.add(pair.second());
                        battleHistory.setWinner(pair.second.getSquadName());
                    }
                    else{
                        Random random = new Random();
                        int index = random.nextInt(2);
                        if(index == 0){
                            winners.add(pair.first());
                            battleHistory.setWinner(pair.first.getSquadName());
                        }
                        else {
                            winners.add(pair.second());
                            battleHistory.setWinner(pair.second.getSquadName());
                        }
                    }
                }
            }
            BattleHistory.addResult(battleHistory);
        }

        for(Pair<Squad, Squad> pair : pairs){
            if(!winners.contains(pair.first())) losers.add(pair.first());
            else losers.add(pair.second());
        }
        assignBattleXP();
        sendEndBattleMessage();
    }
    public static void sendEndBattleMessage() throws JsonProcessingException {
       for(Squad squad : winners){
           for(String member : squad.getMembers()){
               EndBattleMessage endBattleMessage = new EndBattleMessage();
               endBattleMessage.setWin(true);
               MyProject.getInstance().getDatabase().getClientHandlerMap().get(member).sendMessage(endBattleMessage);
           }
       }
        for(Squad squad : losers){
            for(String member : squad.getMembers()){
                EndBattleMessage endBattleMessage = new EndBattleMessage();
                endBattleMessage.setWin(false);
                MyProject.getInstance().getDatabase().getClientHandlerMap().get(member).sendMessage(endBattleMessage);
            }
        }
    }
    public static void assignBattleXP() throws JsonProcessingException {
        for(Squad winner : winners){
            for(String member : winner.getMembers()){
                int XP = MyProject.getInstance().getDatabase().getAllUsers().get(member).getUserData().getXP();
                if(winner.getVaultItems().get(VaultItem.CallOfGefjon)>0) {
                    MyProject.getInstance().getDatabase().getAllUsers().get(member).getUserData().setXP(XP + Constants.XP_PER_WINNER() * 2);
                    int gefjonNumber = winner.getVaultItems().get(VaultItem.CallOfGefjon);
                    winner.getVaultItems().put(VaultItem.CallOfGefjon,gefjonNumber-1);
                }
                else MyProject.getInstance().getDatabase().getAllUsers().get(member).getUserData().setXP(XP + Constants.XP_PER_WINNER());

            }
        }
        for(Squad loser : losers){
            for(String member : loser.getMembers()){
                int XP = MyProject.getInstance().getDatabase().getAllUsers().get(member).getUserData().getXP();
                if(loser.getVaultItems().get(VaultItem.CallOfGefjon)>0) {
                    MyProject.getInstance().getDatabase().getAllUsers().get(member).getUserData().setXP(XP - Constants.XP_PER_LOSER_WITH_GEFJON());
                    int gefjonNumber = loser.getVaultItems().get(VaultItem.CallOfGefjon);
                    loser.getVaultItems().put(VaultItem.CallOfGefjon,gefjonNumber-1);
                }
                else MyProject.getInstance().getDatabase().getAllUsers().get(member).getUserData().setXP(XP - Constants.XP_PER_LOSER());
            }
        }
       sendChangedData();
    }
    public static void sendChangedData() throws JsonProcessingException {
        for(Squad squad : winners) {
            for (String member : squad.getMembers()) {
                ChangeUserDataMessage changeUserDataMessage = new ChangeUserDataMessage();
                changeUserDataMessage.setData("XP");
                changeUserDataMessage.setChangedData(String.valueOf(MyProject.getInstance().getDatabase().getAllUsers().get(member).getUserData().getXP()));
                changeUserDataMessage.setUsername(member);
                for(Pair<Squad, Squad> pair : pairs){
                   if(pair.first.equals(squad) || pair.second.equals(squad)){
                       for(String username : pair.first.getMembers()){
                           MyProject.getInstance().getDatabase().getClientHandlerMap().get(username).sendMessage(changeUserDataMessage);
                       }
                       for(String username : pair.second.getMembers()){
                           MyProject.getInstance().getDatabase().getClientHandlerMap().get(username).sendMessage(changeUserDataMessage);
                       }
                   }
                }
            }
        }
        for(Squad squad : losers) {
            for (String member : squad.getMembers()) {
                ChangeUserDataMessage changeUserDataMessage = new ChangeUserDataMessage();
                changeUserDataMessage.setData("XP");
                changeUserDataMessage.setChangedData(String.valueOf(MyProject.getInstance().getDatabase().getAllUsers().get(member).getUserData().getXP()));
                changeUserDataMessage.setUsername(member);
                for(Pair<Squad, Squad> pair : pairs){
                    if(pair.first.equals(squad) || pair.second.equals(squad)){
                        for(String username : pair.first.getMembers()){
                            MyProject.getInstance().getDatabase().getClientHandlerMap().get(username).sendMessage(changeUserDataMessage);
                        }
                        for(String username : pair.second.getMembers()){
                            MyProject.getInstance().getDatabase().getClientHandlerMap().get(username).sendMessage(changeUserDataMessage);
                        }
                    }
                }
            }
        }
    }

    public static List<Pair<Squad,Squad>> getPairs(){
        return pairs;
    }
    public record Pair<T, U>(T first, U second) {
    }

}
