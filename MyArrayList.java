package com.leo;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by Lelily on 10/12/2017.
 */
public class MyArrayList<T> implements List<T>, Iterable<T> {
  private T[] arr;
  private int size;

  @SuppressWarnings("unchecked")
  public MyArrayList(){
    this.arr = (T[])new Object[2];
    this.size = 0;
  }

  @Override
  public int size() {
    return size;
  }

  @Override
  public boolean isEmpty() {
    if(size == 0){
      return true;
    }
    return false;
  }

  @Override
  public boolean contains(Object o) {
    for(int i = 0; i < size; i++){
      if(o.equals(arr[i])){
        return true;
      }
    }
    return false;
  }

  @Override
  public Iterator<T> iterator() {
    Iterator<T> iterator = new Iterator<T>(){
      int current = 0;

      @Override
      public boolean hasNext() {
        boolean nextHasObj = true;
        if(current >= size || arr[current] == null){
          nextHasObj = false;
        }
        return nextHasObj;
      }

      @Override
      public T next() {
        return arr[current++];
      }
    };
    return iterator;
  }

  @Override
  public Object[] toArray() {
    Object[] objArr = new Object[size];
    for(int i = 0; i < size; i++){
      objArr[i] = arr[i];
    }
    return objArr;
  }

  @SuppressWarnings("unchecked")
  @Override
  public <T1> T1[] toArray(T1[] a) {
    T1[] returnArr = (T1[])new Object[size];
    for(int i = 0; i < size; i++){
      returnArr[i] = (T1)arr[i];
    }
    return returnArr;
  }

  @Override
  public boolean add(T t) {
    if(hasSpaceFor(1)){
      arr[size++] = t;
      return true;
    } else {
      arr = sizeUP(this.arr);
      arr[size++] = t;
      return true;
    }
  }

  private boolean hasSpaceFor(int addSize){
    if((size + addSize) > arr.length){
      return false;
    }
    return true;
  }

  private T[] sizeUP(T[] sizeUpArr){
    T[] temp = (T[]) new Object[size * 2];
    for(int i = 0; i < size; i++){
      temp[i] = sizeUpArr[i];
    }
    return temp;
  }

  @Override
  public boolean remove(Object o) {
    if(!contains(o)) {
      return false;
    } else {
      int toRemove = indexOf(o);
      for(int i = toRemove; i < size - 1; i++){
        arr[i] = arr[i + 1];
      }
      return true;
    }
  }

  @Override
  public boolean containsAll(Collection<?> c) {
    Iterator cIt = c.iterator();
    while(cIt.hasNext()){
      if(contains(cIt.next())){
        continue;
      }
      return false;
    }
    return true;
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean addAll(Collection<? extends T> c) {
    if(c == null){
      return false;
    }
    T[] toAdd = turnToArr(c);
    appendArr(toAdd);
    return true;
  }

  private T[] turnToArr(Collection<? extends T>c){
    T[] newArr = (T[]) new Object[c.size()];
    Iterator cIt = c.iterator();
    int toAddIndex = 0;
    while(cIt.hasNext()){
      newArr[toAddIndex++] = (T)cIt.next();
    }
    return newArr;
  }

  private void appendArr(T[] toAdd){
    for(int i = 0; i < toAdd.length; i++){
      add(toAdd[i]);
    }
  }

  @Override
  public boolean addAll(int index, Collection<? extends T> c) {
    T[] temp = (T[]) new Object[size - index];
    for(int i = index, j = 0; i < size; i++){
      temp[j++] = arr[i];
    }
    T[] toAdd = turnToArr(c);
    size = index;
    appendArr(toAdd);
    appendArr(temp);
    return true;
  }

  @Override
  public boolean removeAll(Collection<?> c) {
    Iterator cIt = c.iterator();
    while(cIt.hasNext()){
      T item = (T)cIt.next();
      for(int i = 0; i < size; i++){
        if(item.equals(arr[i])){
          remove(i);
        }
      }
    }
    return true;
  }

  @Override
  public boolean retainAll(Collection<?> c) {
    T[] temp = (T[])new Object[size];
    Iterator cIt = c.iterator();
    while(cIt.hasNext()){
      T item = (T)cIt.next();
      for(int i = 0, j = 0; i < size; i++){
        if(item.equals(arr[i])){
          temp[j++] = arr[i];
        }
      }
    }
    this.arr = temp;
    return true;
  }

  @Override
  public void clear() {
    T[] empty = (T[]) new Object[2];
    this.arr = empty;
    size = 0;
  }

  @Override
  public T get(int index) {
    return arr[index];
  }

  @Override
  public T set(int index, T element) {
    arr[index] = element;
    return arr[index];
  }

  @Override
  public void add(int index, T element) {
    T[] temp = (T[]) new Object[size - index];
    for(int i = index, j = 0; i < size; i++){
      temp[j++] = arr[i];
    }
    size = index;
    add(element);
    appendArr(temp);
  }

  @Override
  public T remove(int index) {
    T toReturn = arr[index];
    int tempSize = size - index - 1;
    T[] temp = (T[]) new Object[tempSize];
    for(int i = index + 1, j = 0; i < size; i++){
      temp[j++] = arr[i];
    }
    size = index;
    appendArr(temp);
    return toReturn;
  }

  @Override
  public int indexOf(Object o) {
    int toReturn = -1;
    for(int i = 0; i < size; i++){
      if(arr[i].equals(o)){
        toReturn = i;
        break;
      }
    }
    return toReturn;
  }

  @Override
  public int lastIndexOf(Object o) {
    int toReturn = -1;
    for(int i = size - 1; i >= 0; i--){
      if(arr[i].equals(o)){
        toReturn = i;
        break;
      }
    }
    return toReturn;
  }

  @Override
  public ListIterator<T> listIterator() {
    return null;
  }

  @Override
  public ListIterator<T> listIterator(int index) {
    return null;
  }

  @Override
  public List<T> subList(int fromIndex, int toIndex) {
    return null;
  }
}
