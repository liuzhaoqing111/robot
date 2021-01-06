package com.lzq.robot.starter.websocket.context;

import com.lzq.robot.starter.websocket.collect.ConcurrentHashSet;
import com.lzq.robot.starter.websocket.destination.BaseWebsocketDestination;
import java.util.Collection;
import java.util.HashSet;
import java.util.concurrent.ConcurrentHashMap;
import lombok.extern.slf4j.Slf4j;

/**
 * 用户订阅管理
 *
 * @author liuzhaoqing5
 * @date 2020/9/3 10:07
 */
@Slf4j
public class WsUserSubscribeManager {

  /**
   * user-destination mapping
   */
  private static ConcurrentHashMap<String, Collection<BaseWebsocketDestination>> USER_DESTINATION_POOL = new ConcurrentHashMap<>();
  /**
   * destination-user mapping
   */
  private static ConcurrentHashMap<BaseWebsocketDestination, Collection<String>> DESTINATION_USER_POOL = new ConcurrentHashMap<>();

  /**
   * 添加订阅
   *
   * @param username
   * @param destination
   */
  public static void add(String username, BaseWebsocketDestination destination) {
    log.debug("添加订阅地址，username:{}, des:{}", username, destination.getDestination());
    USER_DESTINATION_POOL.putIfAbsent(username, new ConcurrentHashSet<>());  // 并发线程安全的集合
    USER_DESTINATION_POOL.get(username).add(destination);

    DESTINATION_USER_POOL.putIfAbsent(destination, new ConcurrentHashSet<>()); // 并发线程安全的集合
    DESTINATION_USER_POOL.get(destination).add(username);
  }

  /**
   * 删除用户，以及用户的订阅
   *
   * @param username
   */
  public static void remove(String username) {
    log.debug("删除订阅地址, username:{}", username);

    // 1. 删除DESTINATION_USER_POOL中的数据
    getDestinationsSet(username).stream()
        .map(WsUserSubscribeManager::getUsersSet)
        .forEach(set -> set.remove(username));

    // 2. 删除USER_DESTINATION_POOL中数据
    USER_DESTINATION_POOL.remove(username);
  }

  /**
   * 删除用户指定的订阅数据
   *
   * @param username
   * @param destination
   */
  public static void unSubscribe(String username, BaseWebsocketDestination destination) {
    // 删除USER_DESTINATION_POOL数据
    getDestinationsSet(username).remove(destination);

    // 1. 删除DESTINATION_USER_POOL中的数据
    getUsersSet(destination).remove(username);
  }

  /**
   * 地址列表数据
   *
   * @param username
   * @return
   */
  private static Collection<BaseWebsocketDestination> getDestinationsSet(String username) {
    return USER_DESTINATION_POOL.getOrDefault(username, new HashSet<>());
  }

  /**
   * 用户列表数据
   *
   * @param destination
   * @return
   */
  public static Collection<String> getUsersSet(BaseWebsocketDestination destination) {
    return DESTINATION_USER_POOL.getOrDefault(destination, new HashSet<>());
  }

  /**
   * 是否有订阅
   *
   * @param username
   * @param destination
   * @return
   */
  public static boolean exist(String username, BaseWebsocketDestination destination) {
    if (destination == null) {
      return false;
    }
    return getDestinationsSet(username).contains(destination);
  }
}
