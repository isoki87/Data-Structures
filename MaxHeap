package com.leo;

import java.util.ArrayList;

public class MaxHeap<T extends Comparable<T>> {
    private ArrayList<T> heap;
    private int size;

    MaxHeap() {
        this.heap = new ArrayList<>();
        this.size = 0;
    }

    public int size(){
        return size;
    }

    private int findLeftChild(int index) {
        return 2 * index + 1;
    }

    private int findRightChild(int index) {
        return 2 * index + 2;
    }

    private int findParent(int index) {
        return (index - 1) / 2;
    }

    public T remove() {
        T toReturn = heap.get(0);
        swap(0, size - 1);
        heap.remove(size - 1);
        size--;
        removeHeapCheck(0);
        return toReturn;
    }

    public void add(T data) {
        heap.add(data);
        size++;
        addHeapCheck();
    }

    private void removeHeapCheck(int index) {
        if(findLeftChild(index) >= size || findRightChild(index) >= size){
            return;
        }
        T current = heap.get(index);
        T leftChild = heap.get(findLeftChild(index));
        T rightChild = heap.get(findRightChild(index));
        if(leftChild.compareTo(rightChild) >= 0){
            if(leftChild.compareTo(current) > 0){
                swap(index, findLeftChild(index));
                removeHeapCheck(findLeftChild(index));
            }
        } else if(rightChild.compareTo(leftChild) > 0){
            if(rightChild.compareTo(current) > 0){
                swap(index, findRightChild(index));
                removeHeapCheck(findRightChild(index));
            }
        }
    }

    private boolean largerThanChildren(int index) {
        T parent = heap.get(index);
        T leftChild = heap.get(findLeftChild(index));
        T rightChild = heap.get(findRightChild(index));
        return parent.compareTo(leftChild) > 0 && parent.compareTo(rightChild) > 0;
    }

    @SuppressWarnings("unchecked")
    public ArrayList<T> display(){
        return heap;
    }

    private void addHeapCheck() {
        boolean addGood = false;
        int current = size - 1;
        while(!addGood) {
            if (heap.get(current).compareTo(heap.get(findParent(current))) > 0) {
                this.swap(current, findParent(current));
                current = findParent(current);
            } else {
                addGood = true;
            }
        }
    }

    private void swap(int ind1, int ind2) {
        T temp = heap.get(ind1);
        heap.set(ind1, heap.get(ind2));
        heap.set(ind2, temp);
    }
}
