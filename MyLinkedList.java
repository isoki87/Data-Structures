package com.leo;

import java.util.*;

/**
 * Created by Lelily on 10/12/2017.
 */
public class MyLinkedList<T> implements List{
    private Node head;
    private Node tail;
    private int size;

    class Node<T> {
        public Node next;
        public Node previous;
        private T data;

        Node(T data){
            next = null;
            previous = null;
            this.data = data;
        }

        public T get(){
            return data;
        }
    }

    @SuppressWarnings("unchecked")
    public MyLinkedList(){
        head = new Node((T)null);
        tail = new Node((T)null);
        head.next = tail;
        tail.previous = head;
        size = 0;
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
    public boolean contains(Object o){
        Node current = head.next;
        while(current != tail){
            if((current.get()).equals(o)) {
                return true;
            } else {
                current = current.next;
            }
        }
        return false;
    }

    @Override
    public Iterator iterator() {
        Iterator<T> iterator = new Iterator<T>(){
            int current = 0;
            @Override
            public boolean hasNext(){
                if(current > size){
                    return false;
                }
                return true;
            }
            @Override
            public T next(){
                return (T)get(current++);
            }
        };
        return iterator;
    }

    @Override
    public Object[] toArray() {
        Node current = head.next;
        T[] arr = (T[])new Object[size];
        int index = 0;
        while(current != tail){
            arr[index] = (T)current.get();
            index++;
            current = current.next;
        }
        return arr;
    }

    @Override
    public boolean add(Object o) {
        Node node = new Node(o);
        node.next = tail;
        node.previous = tail.previous;
        tail.previous.next = node;
        tail.previous = node;
        size++;
        return true;
    }

    @Override
    public boolean remove(Object o) {
        Node current = head.next;
        while(current != tail){
            if((current.get()).equals(o)){
                current.next.previous = current.previous;
                current.previous.next = current.next;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addAll(Collection c) {
        Iterator cIt = c.iterator();
        while(cIt.hasNext()){
            add(cIt.next());
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean addAll(int index, Collection c){
        Node tempTail = traverseAndStopAt(index);
        Node tempHead = tempTail.previous;
        Iterator cIt = c.iterator();
        while(cIt.hasNext()) {
            Node newNode = new Node(cIt.next());
            newNode.previous = tempHead;
            newNode.next = tempTail;
            tempHead.next = newNode;
            tempTail.previous = newNode;
            tempHead = newNode;
            size++;
        }
        return true;
    }

    private Node traverseAndStopAt(int index){
        int nodesPassed = 0;
        Node current = head.next;
        while(nodesPassed < index){
            current = current.next;
            nodesPassed++;
        }
        return current;
    }

    @Override
    public void clear() {
        head.next = tail;
        tail.previous = head;
        size = 0;
    }

    @Override
    public Object get(int index) {
        return traverseAndStopAt(index).get();
    }

    @Override
    public Object set(int index, Object element) {
        Node current = traverseAndStopAt(index);
        current.data = element;
        return current.get();
    }

    @Override
    public void add(int index, Object element) {
        Node current = traverseAndStopAt(index);
        Node newNode = new Node(element);
        newNode.next = current;
        newNode.previous = current.previous;
        current.previous.next = newNode;
        current.previous = newNode;
        size++;
    }

    @Override
    public Object remove(int index) {
        Node current = traverseAndStopAt(index);
        Node tempHead = current.previous;
        Node tempTail = current.next;
        tempHead.next = tempTail;
        tempTail.previous = tempHead;
        size--;
        return current.get();
    }

    @Override
    public int indexOf(Object o) {
        Node current = head.next;
        int index = 0;
        while(current != tail){
            if(current.get().equals(o)){
                return index;
            }
            current = current.next;
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(Object o) {
        Node current = tail.previous;
        int index = size - 1;
        while(current != head){
            if(current.get().equals(o)){
                return index;
            }
            current = current.previous;
            index--;
        }
        return -1;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ListIterator listIterator() {
        ListIterator<T> listIterator = new ListIterator(){
            Node current = head.next;
            int index = 0;
            boolean performedNext = false;

            @Override
            public boolean hasNext() {
                if(current == tail){
                    return false;
                }
                return true;
            }

            @Override
            public Object next() {
                Object toReturn = current.data;
                performedNext = true;
                current = current.next;
                index++;
                return toReturn;
            }

            @Override
            public boolean hasPrevious() {
                if(current == head){
                    return false;
                }
                return true;
            }

            @Override
            public Object previous() {
                Object toReturn = current.data;
                performedNext = false;
                current = current.previous;
                index--;
                return toReturn;
            }

            @Override
            public int nextIndex() {
                return index + 1;
            }

            @Override
            public int previousIndex() {
                return index - 1;
            }

            @Override
            public void remove() {
                if(performedNext){
                    current.previous.previous.next = current;
                    current.previous = current.previous.previous;
                    size--;
                } else {
                    current.next.next.previous = current;
                    current.next = current.next.next;
                    size--;
                }
            }

            @Override
            public void set(Object o) {
                if(performedNext){
                    current.previous.data = o;
                } else {
                    current.next.data = o;
                }
            }

            @Override
            public void add(Object o) {
                Node newNode = new Node(o);
                newNode.next = current.next;
                newNode.previous = current;
                current.next.previous = newNode;
                current.next = newNode;
                size++;
            }
        };
        return listIterator;
    }

    @SuppressWarnings("unchecked")
    @Override
    public ListIterator listIterator(int index) {
        ListIterator<T> listIterator = new ListIterator(){
            Node current = findCurrent(index);
            int currentIndex = index;
            boolean performedNext = false;

            private Node findCurrent(int index){
                Node toReturn = head.next;
                int count = 0;
                while(count < index){
                    toReturn = toReturn.next;
                    count++;
                }
                return toReturn;
            }

            @Override
            public boolean hasNext() {
                if(current == tail){
                    return false;
                }
                return true;
            }

            @Override
            public Object next() {
                Object toReturn = current.data;
                performedNext = true;
                current = current.next;
                currentIndex++;
                return toReturn;
            }

            @Override
            public boolean hasPrevious() {
                if(current == head){
                    return false;
                }
                return true;
            }

            @Override
            public Object previous() {
                Object toReturn = current.data;
                performedNext = false;
                current = current.previous;
                currentIndex--;
                return toReturn;
            }

            @Override
            public int nextIndex() {
                return currentIndex + 1;
            }

            @Override
            public int previousIndex() {
                return currentIndex - 1;
            }

            @Override
            public void remove() {
                if(performedNext){
                    current.previous.previous.next = current;
                    current.previous = current.previous.previous;
                    size--;
                } else {
                    current.next.next.previous = current;
                    current.next = current.next.next;
                    size--;
                }
            }

            @Override
            public void set(Object o) {
                if(performedNext){
                    current.previous.data = o;
                } else {
                    current.next.data = o;
                }
            }

            @Override
            public void add(Object o) {
                Node newNode = new Node(o);
                newNode.next = current.next;
                newNode.previous = current;
                current.next.previous = newNode;
                current.next = newNode;
                size++;
            }
        };
        return listIterator;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public boolean retainAll(Collection c){
        Node current = head.next;
        T[] arr = convertToArr(c);
        boolean isInArr = false;
        while(current != tail){
            for(int i = 0; i < arr.length; i++){
                if(current.get().equals(arr[i])){
                    isInArr = true;
                    break;
                }
            }
            if(isInArr) {
                current = current.next;
                isInArr = false;
            } else {
                current.previous.next = current.next;
                current.next.previous = current.previous;
                current = current.next;
                size--;
            }
        }
        return true;
    }

    @SuppressWarnings("unchecked")
    private T[] convertToArr(Collection c){
        T[] arr = (T[]) new Object[c.size()];
        int index = 0;
        Iterator cIt = c.iterator();
        while(cIt.hasNext()){
            arr[index] = (T)cIt.next();
            index++;
        }
        return arr;
    }

    @Override
    public boolean removeAll(Collection c) {
        Node current = head.next;
        T[] arr = convertToArr(c);
        boolean isInArr = false;
        while(current != tail){
            for(int i = 0; i < arr.length; i++){
                if(current.get().equals(arr[i])){
                    isInArr = true;
                    break;
                }
            }
            if(isInArr){
                current.previous.next = current.next;
                current.next.previous = current.previous;
                current = current.next;
                size--;
            } else {
                current = current.next;
                isInArr = false;
            }
        }
        return true;
    }

    @Override
    public boolean containsAll(Collection c) {
        Node current = head.next;
        Iterator cIt = c.iterator();
        while(cIt.hasNext()){
            if(contains(cIt.next())){
                continue;
            }
            return false;
        }
        return true;
    }

    @Override
    public Object[] toArray(Object[] a) {
        Object[] toReturn =  new Object[size];
        Node current = head.next;
        int index = 0;
        while(current != tail){
            toReturn[index++] = (Object) current.data;
            current = current.next;
        }
        return toReturn;
    }
}
