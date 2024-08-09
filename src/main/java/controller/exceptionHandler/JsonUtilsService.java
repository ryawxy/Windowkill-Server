package controller.exceptionHandler;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface JsonUtilsService {
    public default String serializeToJson(Object object) throws JsonProcessingException {
        return null;
    }

     public default <T> T deserializeFromJson(String json, Class<T> valueType)  {
        return null;
    }
}
