package org.colfax.org.colfax.interview_prep;

import java.util.Iterator;

/**
 * Created by colfax on 11/18/2014.
 */
public class Bag_GenericIterable<T> implements Iterable<T>{

    Node root;

    private class Node{
        T val;
        Node next;
        public Node(T v){
            val = v;
        }
    }

    public void add(T v){
        Node n    = new Node(v);
        n.next    = root;
        root      = n;
    }

    @Override
    public Iterator<T> iterator() {
        return new GenericBagIterator(root);
    }

    private class GenericBagIterator<T> implements Iterator<T>{
        Node current;

        public GenericBagIterator(Node r){
            current = r;
        }

        @Override
        public boolean hasNext() {
            return (current != null);
        }

        @Override
        public T next() {
            T result = (T)current.val;
            current  = current.next;
            return result;
        }

        @Override
        public void remove() {
            //
        }
    }

    public static <T> void main(String[] args){
        Bag_GenericIterable b = new Bag_GenericIterable();
        b.add(-1);
        b.add(3);
        b.add(5);
        b.add(-18);
        b.add(32);
        b.add(1);
        b.add(55);
        b.add(34);
        b.add(2111232232);
        b.add(-2111232232);

        Iterator<T> it = b.iterator();
        while(it.hasNext())
            System.out.print(it.next() + ", ");
        System.out.println("");
    }
}
