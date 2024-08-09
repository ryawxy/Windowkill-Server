package model.data;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Game.Squad;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SquadData {
    private Map<String, Squad> squadMap = new ConcurrentHashMap<>();

    public SquadData() {
    }

    public Map<String, Squad> getSquadMap() {
        return squadMap;
    }

    public void setSquadMap(Map<String, Squad> squadMap) {
        this.squadMap = squadMap;
    }

    public void save() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        try {
            objectMapper.writeValue(new FileWriter(new File( "squads.json")), this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void load() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
        if (new File("squads.json").exists() && new File("squads.json").length() > 0) {
            try {
                objectMapper.readValue((new File("squads.json")), SquadData.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
