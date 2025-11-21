package br.com.alura.refl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class ObjectToJson {

    public String transformToJson(Object object){
        String result = null;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        Map<String, Object> mapper = new HashMap<>();
        Class<?> clazz = object.getClass();

        Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()).forEach(f -> {
            f.setAccessible(true);
            String key = f.getName();
            Object value = null;

            try {
                value = f.get(object);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            mapper.put(key, value);
        });

        try {
            result = objectMapper.writeValueAsString(mapper);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return result;
    }
}
