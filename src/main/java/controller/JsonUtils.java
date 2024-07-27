package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static String serializeToJson(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
    public static <T> T deserializeFromJson(String json, Class<T> valueType) throws JsonProcessingException {
        objectMapper.enable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
        objectMapper.enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
        return objectMapper.readValue(json,valueType);
    }

}
