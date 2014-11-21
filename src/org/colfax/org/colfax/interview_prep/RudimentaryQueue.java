package org.colfax.org.colfax.interview_prep;

import java.util.Iterator;

/**
 * Created by colfax on 11/18/2014.
 */
public class RudimentaryQueue implements Iterable<Integer>{
    // Linked list
    // Oldest ----> Newest
    // Careful: Update both Old and New when either transitions between none/one or vice versa.
    Node oldest;
    Node newest;
    int N;
    private class Node{
        int value;
        Node next;
        public Node(int v){
            value = v;
        }
    }

    public void enqueue(int v){
        if(newest == null)
            oldest = newest = new Node(v);
        else{
            Node n = new Node(v);
            newest.next = n;
            newest = n;
        }
        N++;
    }

    public Integer dequeue(){
        if(oldest == null) return null;
        Integer result = oldest.value;
        if(oldest.next == null)
            oldest = newest = null;
        else
            oldest = oldest.next;
        N--;
        return result;
    }

    public Iterator<Integer> iterator(){
        return new QIterator(oldest);
    }

    private class QIterator implements Iterator<Integer>{
        Node current;
        public QIterator(Node n){
            current = n;
        }
        @Override
        public boolean hasNext(){
            return (current != null);
        }
        @Override
        public Integer next(){
            Integer result = current.value;
            current = current.next;
            return result;
        }
        @Override
        public void remove() {

        }
    }

    public static void main(String[] args){
        RudimentaryQueue q = new RudimentaryQueue();
        q.enqueue(-1);
        q.enqueue(3);
        q.enqueue(5);
        q.enqueue(-18);
        q.enqueue(32);
        q.enqueue(1);
        q.enqueue(55);
        q.enqueue(34);
        q.enqueue(2111232232);
        q.enqueue(-2111232232);
        for(int i : q)
            System.out.print(i + ", ");
        System.out.println("");

        System.out.print(q.dequeue() + ", ");
        System.out.print(q.dequeue() + ", ");
        System.out.print(q.dequeue() + ", ");
        System.out.print(q.dequeue() + ", ");
        System.out.print(q.dequeue() + ", ");
        System.out.print(q.dequeue() + ", ");
        System.out.print(q.dequeue() + ", ");
        System.out.print(q.dequeue() + ", ");
        System.out.print(q.dequeue() + ", ");
        System.out.print(q.dequeue() + ", ");
        System.out.print(q.dequeue() + ", ");
        System.out.print(q.dequeue() + ", ");
        System.out.print(q.dequeue() + ", ");
        System.out.print(q.dequeue() + ", ");
        System.out.print(q.dequeue() + ", ");
        System.out.print(q.dequeue() + ", ");
    }
}
