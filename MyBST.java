package com.leo;

/**
 * Created by Lelily on 10/13/2017.
 */
public class MyBST<T extends Comparable<T>> {
    class Node<T extends Comparable<T>> {
        private Node leftChild;
        private Node rightChild;
        private Node parent;
        private T data;

        Node(T data){
            this.data = data;
            this.leftChild = null;
            this.rightChild = null;
            this.parent = null;
        }

    }

    private Node root;
    private T[] display;
    private int size;
    private int displayIndex;

    MyBST(){
        this.root = null;
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public void clear(){
        this.root = null;
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public void add(T data){
        Node node = new Node(data);
        if(size == 0){
            root = node;
        }
        internalAdd(root, node);
    }

    private boolean isLeaf(Node node){
        if(node.leftChild == null && node.rightChild == null){
            return true;
        }
        return false;
    }

    private void internalAdd(Node current, Node node){
        if(current.data.compareTo(node.data) >= 0){
            if(isLeaf(current)){
                current.leftChild = node;
                node.parent = current;
                size++;
                return;
            }
            internalAdd(current.leftChild, node);
        } else {
            if(isLeaf(current)){
                current.rightChild = node;
                node.parent = current;
                size++;
                return;
            }
            internalAdd(current.rightChild, node);
        }
    }

    /*
    @SuppressWarnings("unchecked")
    public T delete(T data){
        Node toFind = new Node(data);
        Node current = root;
        boolean wentLeft = false;
        while(current != null){
            if(current.data.compareTo(toFind.data) == 0){
                if(isLeaf(current)){
                    current.data = null;
                } else if(current.leftChild == null){
                    current.rightChild.parent = current.parent;
                    if(wentLeft){
                        current.parent.leftChild = current.rightChild;
                    } else {
                        current.parent.rightChild = current.rightChild;
                    }
                } else if(current.rightChild == null){
                    current.leftChild.parent = current.parent;
                    if(wentLeft){
                        current.parent.leftChild = current.right
                    }
                }
            } else if (current.data.compareTo(toFind.data) > 0){
                current = current.leftChild;
            } else {
                current = current.rightChild;
            }
            if(current.data.compareTo(toFind.data) > 0){
                current = current.leftChild;
                wentLeft = true;
            } else {
                current = current.rightChild;
                wentLeft = false;
            }
        }
        return (T)toFind.data;
    }
    */

    public boolean isEmpty(){
        if(size == 0){
            return true;
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public T findMax(){
        Node current = root;
        while(current.rightChild != null){
            current = current.rightChild;
        }
        return (T) current.data;
    }

    @SuppressWarnings("unchecked")
    public T findMin(){
        Node current = root;
        while(current.leftChild != null){
            current = current.leftChild;
        }
        return (T) current.data;
    }

    @SuppressWarnings("unchecked")
    public T[] displayDescOrder(){
        this.display = (T[])new Object[size];
        this.displayIndex = 0;
        rearrangeDescDisplay(root);
        return display;
    }

    @SuppressWarnings("unchecked")
    private void rearrangeDescDisplay(Node node){
        if(node == null){
            return;
        }
        rearrangeDescDisplay(node.rightChild);
        display[displayIndex] = (T)node.data;
        displayIndex++;
        rearrangeDescDisplay(node.leftChild);
    }

    @SuppressWarnings("unchecked")
    public T[] displayAscOrder(){
        this.display = (T[])new Object[size];
        this.displayIndex = 0;
        rearrangeAscDisplay(root);
        return display;
    }

    @SuppressWarnings("unchecked")
    private void rearrangeAscDisplay(Node node){
        if(node == null){
            return;
        }
        rearrangeAscDisplay(node.leftChild);
        display[displayIndex] = (T)node.data;
        displayIndex++;
        rearrangeAscDisplay(node.rightChild);
    }



}
