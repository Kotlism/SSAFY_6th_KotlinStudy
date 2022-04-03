package algo2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj20924_트리의기둥과가지 {
    static int N, R;
    static ArrayList<node>[] tree;
    static boolean[] check;
    static boolean[] treeroot;

    static class node {
        int end;
        int weight;

        public node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];
        check = new boolean[N + 1];
        treeroot = new boolean[N + 1];
        for (int i = 0; i < tree.length; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            //트리라 단방향인줄알았는데 양방향으로 해야함
            tree[start].add(new node(end, weight));
            tree[end].add(new node(start, weight));
        }
        int giga = findgiga(R);
        System.out.println(findrootlength(R, giga) + " " + findlength(giga));
    }

    public static int findgiga(int root) {
        Queue<Integer> q = new LinkedList<>();
        q.add(root);
        check[root] = true;
        int temp = root;
        //이부분이 발목잡음 처음에 바로 기가노드일수있음
        if (tree[root].size() > 1) {
            return root;
        }
        while (!q.isEmpty()) {
            int now = q.poll();
            for (node n : tree[now]) {
                if (!check[n.end]) {
                    temp = n.end;
                    q.add(n.end);
                    treeroot[n.end] = true;
                    check[n.end] = true;
                    //또한 체킹을 해줄때 treeroot에 넣고 리턴시켜야함
                    if (tree[n.end].size() > 2) {
                        return n.end;
                    }
                }
            }
        }
        return temp;
    }

    public static int findlength(int giga) {
        check = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        check[giga] = true;
        q.add(giga);
        int[] temp = new int[N + 1];
        while (!q.isEmpty()) {
            int now = q.poll();
            for (node n : tree[now]) {
                if (!check[n.end] && !treeroot[n.end]) {
                    temp[n.end] += temp[now] + n.weight;
                    q.add(n.end);
                    check[n.end] = true;
                }
            }
        }
        int max = 0;
        for (int i = 0; i < N + 1; i++) {
            max = Integer.max(max, temp[i]);
        }
        return max;
    }

    public static int findrootlength(int root, int giga) {
        check = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        check[root] = true;
        q.add(root);
        int[] temp = new int[N + 1];
        while (!q.isEmpty()) {
            int now = q.poll();
            if (now == giga) {
                break;
            }
            for (node n : tree[now]) {
                if (!check[n.end] && treeroot[n.end]) {
                    temp[n.end] += temp[now] + n.weight;
                    q.add(n.end);
                    check[n.end] = true;
                }
            }

        }
        int max = 0;
        for (int i = 0; i < N + 1; i++) {
            max = Integer.max(max, temp[i]);
        }
        return max;
    }
}
