package com.lzq.robot.starter.websocket.collect;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentHashMap.KeySetView;

/**
 * set集合类，借助于ConcurrentHashMap
 *
 * @author liuzhaoqing5
 * @date 2020/9/4 16:06
 */
public class ConcurrentHashSet<E> implements Set<E> {

  /**
   * 实际存储数据结构
   */
  private final KeySetView<E, Boolean> setView;

  /**
   * 构造方法
   */
  public ConcurrentHashSet() {
    setView = ConcurrentHashMap.newKeySet(16);
  }

  /**
   * 构造方法
   */
  public ConcurrentHashSet(int initialCapacity) {
    setView = ConcurrentHashMap.newKeySet(initialCapacity);
  }


  @Override
  public int size() {
    return setView.size();
  }

  @Override
  public boolean isEmpty() {
    return setView.isEmpty();
  }

  @Override
  public boolean contains(Object o) {
    return setView.contains(o);
  }

  @Override
  public Iterator<E> iterator() {
    return setView.iterator();
  }

  @Override
  public Object[] toArray() {
    return setView.toArray();
  }

  @Override
  public <T> T[] toArray(T[] a) {
    return setView.toArray(a);
  }

  @Override
  public boolean add(E e) {
    return setView.add(e);
  }

  @Override
  public boolean remove(Object o) {
    return setView.remove(o);
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    return setView.containsAll(c);
  }

  @Override
  public boolean addAll(Collection<? extends E> c) {
    return setView.addAll(c);
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    return setView.removeAll(c);
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    return setView.retainAll(c);
  }

  @Override
  public void clear() {
    setView.clear();
  }

}
