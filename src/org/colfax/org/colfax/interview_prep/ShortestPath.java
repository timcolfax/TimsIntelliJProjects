package org.colfax.org.colfax.interview_prep;

import java.util.*;

/**
 * Created by colfax on 11/17/2014.
 */
public class ShortestPath {
    boolean[] marked;
    int[] edgeTo;
    int s;

    public ShortestPath(Graph G, int s){
        // Init local vars.
        marked = new boolean[G.V()];
        edgeTo = new int[G.V()];
        this.s = s;
        // Perform BFS on G beginning at s.
        bfs(G, s);
    }

    private void bfs(Graph G, int s){
        marked[s] = true;
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(s);
        while(!q.isEmpty()) {
            int v = q.remove();
            for(int w : G.adj(v)){
                if(!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    q.add(w);
                }
            }
        }
    }

    public boolean hasPathTo(int w){
        return marked[w];
    }

    public Stack<Integer> pathTo(int w){
        if(!hasPathTo(w)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for(int x = w; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    private static class Graph{
        private final int V;
        private int E;
        private ArrayList<Integer>[] adj;

        public Graph(int v){
            V = v;
            E = 0;
            adj = (ArrayList<Integer>[]) new ArrayList[V];
            for(int i = 0; i < V; i++)
                adj[i] = new ArrayList<Integer>();
        }

        public int V(){return V;}
        public int E(){return E;}

        public void addEdge(int v, int w){
            adj[v].add(w);
            adj[w].add(v);
            E++;
        }

        public Iterable<Integer> adj(int v){
            return adj[v];
        }
    }

    public static void main(String[] args){
        Graph G = new Graph(11);
        G.addEdge(0, 1);
        G.addEdge(0, 2);
        G.addEdge(0, 3);
        G.addEdge(1, 6);
        G.addEdge(2, 3);
        G.addEdge(2, 4);
        G.addEdge(2, 5);
        G.addEdge(3, 5);
        G.addEdge(3, 6);
        G.addEdge(5, 7);
        G.addEdge(5, 8);
        G.addEdge(9, 10);

        ShortestPath sp = new ShortestPath(G, 0);
        Stack<Integer> p = sp.pathTo(4);
        System.out.print("(0, 4): ");
        while(p != null && !p.isEmpty())
            System.out.print(p.pop() + ", ");
        System.out.println("END");

        p = sp.pathTo(10);
        System.out.print("(0, 10): ");
        while(p != null && !p.isEmpty())
            System.out.print(p.pop() + ", ");
        System.out.println("END");
    }
}
