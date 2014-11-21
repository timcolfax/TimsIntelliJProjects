package org.colfax.org.colfax.interview_prep;

import java.util.Iterator;

/**
 * Created by colfax on 11/18/2014.
 */
public class RudimentaryStack<T> implements Iterable<T>{

    Node head;
    int size = 0;

    private class Node<T>{
        T val;
        Node next;
        public Node(T v){
            val = v;
        }
    }

    public void push(T v){
        Node n = new Node(v);
        n.next = head;
        head   = n;
        size++;
    }

    public T pop(){
        if(head == null) return null;
        T result = (T)head.val;
        head     = head.next;
        size--;
        return result;
    }

    public T peek(){
        if(head == null) return null;
        return (T)head.val;
    }

    public boolean isEmpty(){
        return (size == 0);
    }

    public Iterator<T> iterator(){
        return new RStackIterator(head);
    }

    private class RStackIterator implements Iterator<T>{
        Node current;
        public RStackIterator(Node n){
            current = n;
        }

        @Override
        public boolean hasNext(){
            return (current != null);
        }

        @Override
        public T next(){
            T result = (T)current.val;
            current = current.next;
            return result;
        }

        @Override
        public void remove() {
            //...
        }
    }

    public static <T> void main(String[] args){
        RudimentaryStack s = new RudimentaryStack();
        s.push(-1);
        s.push(3);
        s.push(5);
        s.push(-18);
        s.push(32);
        s.push(1);
        s.push(55);
        s.push(34);
        s.push(2111232232);
        s.push(-2111232232);

        Iterator<T> it = s.iterator();
        while(it.hasNext())
            System.out.print(it.next() + ", ");
        for(int i = 0; i < 4; i++)
            System.out.print(s.pop() + ", ");
        System.out.println("");
    }
}
