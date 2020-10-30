package com.lzq.robot.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Jackson工具类
 *
 * @date 2020-08-31 16:56
 * @author liuzhaoqing5
 */
@Slf4j
public class JacksonUtil {

  /**
   * json对象
   */
  private static ObjectMapper objectMapper;

  /**
   * 将对象转成Jsong字符串
   * 失败时候不会抛出异常-但是会返回null
   */
  public static String toJson(Object model) {
    try {
      return getObjectMapper().writeValueAsString(model);
    } catch (JsonProcessingException e) {
      log.error("writeValueAsString error", e);
      return null;
    }
  }


  /**
   * 将 Json 饭序列化成 List 对象
   * 失败时候不会抛出异常-但是会返回null
   *
   * @param body  Json
   * @param clazz 类
   * @return 失败时 返回 null
   */
  public static <T> List<T> totArray(String body, Class<T> clazz) {

    List<T> objs;
    CollectionType collectionType = TypeFactory.defaultInstance()
        .constructCollectionType(ArrayList.class, clazz);
    try {
      objs = getObjectMapper().readValue(body, collectionType);
    } catch (Exception e) {
      log.error("toObjectArray error", e);
      return null;
    }
    return objs;
  }


  /**
   * 将 Json 饭序列化成 clazz 对象
   *
   * @param body  Json
   * @param valueTypeRef 类
   * @return 失败时 返回 null
   */
  public static <T> T toObject(String body, TypeReference<T> valueTypeRef) {
    try {
      return getObjectMapper().readValue(body, valueTypeRef);
    } catch (IOException e) {
      log.error("readValue error", e);
      return null;
    }
  }


  /**
   * 将 Json 饭序列化成 clazz 对象
   *
   * @param body  Json
   * @param clazz 类
   * @return 失败时 返回 null
   */
  public static <T> T toObject(String body, Class<T> clazz) {
    try {
      return getObjectMapper().readValue(body, clazz);
    } catch (IOException e) {
      log.error("readValue error", e);
      return null;
    }
  }

  /**
   * 获取object mapper
   *
   * @return
   */
  public static ObjectMapper getObjectMapper() {
    if (objectMapper == null) {
      ObjectMapper retval = new ObjectMapper();
      retval.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
      retval.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
      retval.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
      retval.configure(DeserializationFeature.EAGER_DESERIALIZER_FETCH, false);
      retval.configure(SerializationFeature.EAGER_SERIALIZER_FETCH, false);
      objectMapper = retval;
    }
    return objectMapper;
  }
}
