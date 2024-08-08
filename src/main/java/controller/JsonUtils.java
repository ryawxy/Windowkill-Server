package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import controller.exceptionHandler.ExceptionHandler;
import controller.exceptionHandler.HandleExceptions;
import controller.exceptionHandler.JsonUtilsService;

public class JsonUtils implements JsonUtilsService {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @HandleExceptions
    @Override
    public String serializeToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {

        }
        return null;
    }
    @HandleExceptions
    @Override
    public <T> T deserializeFromJson(String json, Class<T> valueType)  {
        objectMapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        try {
            return objectMapper.readValue(json,valueType);
        } catch (JsonProcessingException e) {

        }
        return null;
    }

}
