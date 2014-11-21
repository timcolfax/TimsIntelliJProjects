package org.colfax.org.colfax.interview_prep;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by colfax on 11/17/2014.
 */
public class BST<Key extends Comparable<Key>, Value> {
    /**
     * A BST has a Node, containing a K/V pair, a size N, and L & R sub-nodes.
     * The tree supports Get, Size, and Put operations.
     */
    Node root;
    private class Node{
        Key K;
        Value V;
        int N;
        Node L;
        Node R;
        public Node(Key k, Value v, int n){
            K = k;
            V = v;
            N = n;
        }
    }

    public Value get(Key k){
        return get(root, k);
    }

    private Value get(Node n, Key k){
        if(n == null) return null;
        int cmp = k.compareTo(n.K);
        if(cmp < 0) return get(n.L, k);
        else if(cmp > 0) return get(n.R, k);
        else return n.V;
    }

    public int size(){
        return size(root);
    }

    private int size(Node n){
        return (n != null) ? n.N : 0;
    }

    public void put(Key k, Value v){
        root = put(root, k, v);
    }

    private Node put(Node n, Key k, Value v){
        if(n == null) return new Node(k, v, 1);

        int cmp = k.compareTo(n.K);
        if(cmp < 0) n.L = put(n.L, k, v);
        else if(cmp > 0) n.R = put(n.R, k, v);
        else n.V = v;

        n.N = size(n.L) + size(n.R) + 1;

        return n;
    }

    public void print(){
        int count = 0;
        int level = -1;
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);

        System.out.println("\n\n");
        while(!q.isEmpty()){
            Node tmp = q.remove();
            System.out.print(tmp.V + "||");
            count++;
            if(tmp.L != null)
                q.add(tmp.L);

            if(tmp.R != null)
                q.add(tmp.R);

            if((int)Math.log(count) > level) {
                System.out.println("");
                level++;
            }
        }
    }

    public static void main(String[] args){
        BST<String, Integer> bst = new BST<String, Integer>();
        bst.put("05", 5);
        bst.put("08", 8);
        bst.put("06", 6);
        bst.put("09", 9);
        bst.put("02", 2);
        bst.put("01", 1);
        bst.put("03", 3);
        bst.print();
    }
}
