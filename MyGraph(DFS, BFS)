package com.leo;

import java.util.ArrayList;

/**
 * Created by Lelily on 10/14/2017.
 */
public class MyGraph {
    private int[][] map;
    private ArrayList<Integer> traveled;
    private ArrayList<Integer> queue;
    private int current;

    public boolean isConnectedBFS(int n1, int n2){
        if(n1 == n2){
            return true;
        }
        if(traveled.contains(n1)){
            return isConnectedBFS(queue.get(current++), n2);
        }
        traveled.add(n1);
        int[] toQueue = showConnections(n1);
        for(int i = 0; i < toQueue.length; i++){
            if(traveled.indexOf(toQueue[i]) == -1){
                queue.add(toQueue[i]);
                System.out.println(toQueue[i] + " added");
            }
        }
        if(current == queue.size()){
            return false;
        }
        return isConnectedBFS(queue.get(current++), n2);
    }

    public boolean isConnectedDFS(int n1, int n2){
        if(n1 == n2){
            return true;
        }
        traveled.add(n1);
        int[] connections = showConnections(n1);
        for(int i = 0; i < connections.length; i++){
            if(!traveled.contains(connections[i]) && isConnectedDFS(connections[i], n2)) {
                return true;
            }
        }
        return false;
    }




    MyGraph(){
        this.map = new int[10][10];
        this.traveled = new ArrayList<>();
        this.queue = new ArrayList<>();
        this.current = 0;
    }


    public void add(int node, int[] connections){
        for(int i = 0; i < connections.length; i++){
            map[node][i] = connections[i];
        }
    }

    public int[] showConnections(int node){
        int[] toReturn = new int[10];
        for(int i = 0; i < map[node].length; i++){
            toReturn[i] = map[node][i];
        }
        toReturn = omitZero(toReturn);
        return toReturn;
    }

    private int[] omitZero(int[] arr){
        int zeroCount = 0;
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == 0){
                zeroCount++;
            }
        }
        int[] toReturn = new int[arr.length - zeroCount];
        for(int i = 0, j = 0; i < arr.length; i++){
            if(arr[i] == 0){
                continue;
            } else {
                toReturn[j] = arr[i];
                j++;
            }
        }
        return toReturn;
    }




}
