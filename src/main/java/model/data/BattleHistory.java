package model.data;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class BattleHistory {

    private String s1;
    private String s2;
    private String winner;
    private static ArrayList<BattleHistory> results = new ArrayList<>();

    public BattleHistory() {
    }

    public static ArrayList<BattleHistory> readResults() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File("battleHistory.json");
        if (file.exists()) {
            if (file.length() > 0) {

                try {
                    results = objectMapper.readValue(file, new TypeReference<ArrayList<BattleHistory>>() {});
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return results;
    }


    public static void writeResults(ArrayList<BattleHistory> results){
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("battleHistory.json"),results);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void addResult(BattleHistory data){
        ArrayList<BattleHistory> results = readResults();
        results.add(data);
        writeResults(results);

    }

    public String getS1() {
        return s1;
    }

    public void setS1(String s1) {
        this.s1 = s1;
    }

    public String getS2() {
        return s2;
    }

    public void setS2(String s2) {
        this.s2 = s2;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }
}
