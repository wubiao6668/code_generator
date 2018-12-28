package com.generator.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.generator.model.TestModel;

import java.io.IOException;
import java.util.Map;

public class JsonUtil {

    public static ObjectMapper jsonMapper = new ObjectMapper();

    public static <S> String toJsonString(S s) {
        try {
            return jsonMapper.writeValueAsString(s);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T parseJson(String jsonContent, Class<T> tClass) {
        try {
            return jsonMapper.readValue(jsonContent, tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
      String json = "{\"name\":\"zhangsan\"}";
        TestModel testModel = parseJson("", TestModel.class);
//        Map map = parseJson(json, Map.class);
//        System.out.println(toJsonString(testModel));
//
//        System.out.println();
    }






}
