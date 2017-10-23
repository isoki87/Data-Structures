package com.leo;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Lelily on 10/12/2017.
 */
public class MyHashMap<K, V> {
    class Entry<K, V>{
        private K key;
        private V value;
        Entry<K, V> next;
        Entry<K, V> previous;

        Entry(K key, V value){
            this.key = key;
            this.value = value;
            this.next = null;
            this.previous = null;
        }
    }

    private Entry<K, V>[] map;
    private int size;

    @SuppressWarnings("unchecked")
    MyHashMap(){
        this.map = new Entry[16];
        this.size = 0;
    }

    private int hash(Object item, int capacity){
        return item.hashCode() % capacity;
    }


    public int size(){
        return size;
    }

    @SuppressWarnings("unchecked")
    public void clear(){
        this.map = new Entry[16];
        this.size = 0;
    }

    public boolean containsKey(Object key){
        int index = hash(key, map.length);
        Entry current = map[index];
        if(current == null){
            return false;
        } else {
            while(current != null){
                if(current.key.equals(key)){
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    public boolean containsValue(Object value){
        for(int i = 0; i < map.length; i++){
            if(map[i] != null){
                Entry current = map[i];
                while(current != null){
                    if(current.value.equals(value)){
                        return true;
                    }
                    current = current.next;
                }
            }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public V get(Object key){
        int index = hash(key, map.length);
        Entry current = map[index];
        while(current != null){
            if(current.key.equals(key)){
                return (V)current.value;
            } else {
                current = current.next;
            }
        }
        return null;
    }

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }


    public void put(K key, V value){
        int index = hash(key, map.length);
        if(needRehash()){
            rehash();
        }
        internalPut(key, value, map, index);
    }

    @SuppressWarnings("unchecked")
    private void internalPut(K key, V value, Entry[] arr, int index){
        Entry newItem = new Entry(key, value);
        if(arr[index] != null){
            arr[index] = newItem;
        } else {
            Entry current = arr[index];
            while(current != null){
                current = current.next;
            }
            newItem.previous = current.previous;
            current.previous.next = newItem;
            size++;
        }
    }

    private boolean needRehash(){
        double loadFactor = size / map.length;
        if(loadFactor > 0.75){
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    private void rehash(){
        Entry<K,V>[] newMap = new Entry[map.length * 2];
        for(int i = 0; i < map.length; i++){
            if(map[i] != null){
                Entry current = map[i];
                while(current != null){
                    int index = hash(current.key, newMap.length);
                    Entry currentClone = new Entry(current.key, current.value);
                    internalPut((K)currentClone.key, (V)currentClone.value, newMap, index);
                    current = current.next;
                }
            }
        }
        map = newMap;
    }

    @SuppressWarnings("unchecked")
    public Set<Map.Entry<K, V>> entrySet(){
        HashSet<Map.Entry<K, V>> toReturn = new HashSet<>();
        for(int i = 0; i < map.length; i++){
            if(map[i] != null){
                Entry current = map[i];
                while(current != null){
                    toReturn.add((Map.Entry<K, V>) current);
                    current = current.next;
                }
            }
        }
        return toReturn;
    }

    public void putAll(Map<? extends K, ? extends V> m){
        for(Map.Entry<? extends K, ? extends V> entry: m.entrySet()){
            put(entry.getKey(), entry.getValue());
        }
    }

    @SuppressWarnings("unchecked")
    public V remove(Object key){
        int index = hash(key, map.length);
        Entry current = map[index];
        while(current != null){
            if(current.key.equals(key)){
                current.previous.next = current.next;
                current.next.previous = current.previous;
                size--;
            } else {
                 current = current.next;
            }
        }
        return (V)current.value;
    }
}
