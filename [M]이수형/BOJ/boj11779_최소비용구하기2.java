import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj11779_최소비용구하기2 {
    static int N, M, startpoint, endpoint, total;
    static ArrayList<node>[] list;
    static int[] dist;
    static int[] parent;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        list = new ArrayList[N + 1];
        total = 0;
        for (int i = 0; i < list.length; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list[start].add(new node(end, weight));

        }
        st = new StringTokenizer(br.readLine());
        startpoint = Integer.parseInt(st.nextToken());
        endpoint = Integer.parseInt(st.nextToken());
        dist = new int[N + 1];
        parent = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dijkstra();
        Stack<Integer> stack = new Stack<>();
        int thispoint = endpoint;
        while (thispoint != startpoint) {
            stack.push(thispoint);
            total++;
            thispoint = parent[thispoint];
        }
        StringBuilder sb = new StringBuilder();
        sb.append(dist[endpoint] + "\n");
        sb.append((total + 1) + "\n");
        sb.append(thispoint);
        while (!stack.isEmpty()) {
            sb.append(" " + stack.pop());
        }
        System.out.println(sb);
    }

    public static void dijkstra() {
        PriorityQueue<node> pq = new PriorityQueue<>();
        boolean[] check = new boolean[N + 1];
        pq.add(new node(startpoint, 0));
        dist[startpoint] = 0;
        while (!pq.isEmpty()) {
            node cur = pq.poll();
            if (check[cur.end]) {
                continue;
            }
            check[cur.end] = true;
            for (node node : list[cur.end]) {
                if (dist[node.end] > dist[cur.end] + node.weight) {
                    dist[node.end] = dist[cur.end] + node.weight;
                    pq.add(new node(node.end, dist[node.end]));
                    parent[node.end] = cur.end;
                }
            }
        }
    }

    static class node implements Comparable<node> {
        int end;
        int weight;

        public node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }
        @Override
        public int compareTo(node o){
            return weight-o.weight;
        }
    }
}
