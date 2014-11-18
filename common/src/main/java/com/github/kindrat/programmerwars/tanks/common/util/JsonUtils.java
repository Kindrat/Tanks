package com.github.kindrat.programmerwars.tanks.common.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;


public final class JsonUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);
    public static final ObjectMapper MAPPER = new ObjectMapper();

    static {
        MAPPER.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        MAPPER.setConfig(MAPPER.getSerializationConfig().without(SerializationFeature.FAIL_ON_EMPTY_BEANS));
    }

    private JsonUtils() {
        throw new AssertionError("Instantiating utility class");
    }

    public static String toJson(Object obj) throws IOException {
        LOGGER.debug("Sending {}", obj);
        return MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
    }

    public static <T> T fromJson(String json, Class<T> type) throws IOException {
        LOGGER.debug("Receiving json {} of type {}", json, type);
        return MAPPER.readValue(json, type);
    }

    public static <T> T fromJsonAsObject(Object obj, Class<T> type) throws IOException {
        LOGGER.debug("Receiving object {} of type {}", obj, type);
        return MAPPER.convertValue(obj, type);
    }

    public static <T> List<T> fromJsonArrayAsObject(Object obj, Class<T> type) throws IOException {
        LOGGER.debug("Receiving object array {} of type {}", obj, type);
        return MAPPER.convertValue(obj, MAPPER.getTypeFactory().constructCollectionType(List.class, type));
    }

    public static <T> List<T> fromJsonAsList(String json, Class<T> type) throws IOException {
        LOGGER.debug("Receiving json list {} of type {}", json, type);
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, type);
        return MAPPER.readValue(json, javaType);
    }

    public static <K, V> List<Map<K, V>> fromJsonAsListWithMap(String json, Class<K> key, Class<V> value) throws IOException {
        LOGGER.debug("Receiving json list {} with map of type <{}>, {}", json, key, value);
        JavaType mapType = MAPPER.getTypeFactory().constructMapType(Map.class, key, value);
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, mapType);
        return MAPPER.readValue(json, javaType);
    }

    public static <K, V> Map<K, List<V>> fromJsonAsMapWithList(String json, Class<K> key, Class<V> value) throws IOException {
        LOGGER.debug("Receiving json list {} with map of type <{}>, {}", json, key, value);
        JavaType keyType = MAPPER.getTypeFactory().constructType(String.class);
        JavaType valueType = MAPPER.getTypeFactory().constructParametricType(List.class, value);
        JavaType mapType = MAPPER.getTypeFactory().constructMapType(Map.class, keyType, valueType);
        return MAPPER.readValue(json, mapType);
    }

    public static <K, V> Map<K, V> fromJsonAsMap(String json, Class<K> key, Class<V> value) throws IOException {
        LOGGER.debug("Receiving json map {} of type <{},{}>", json, key, value);
        return MAPPER.readValue(json, MAPPER.getTypeFactory().constructMapType(Map.class, key, value));
    }
}
