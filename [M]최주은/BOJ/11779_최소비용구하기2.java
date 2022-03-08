package com.ssafy.algorithm.boj_11779_최소비용구하기2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	public static int N, M, start, end, MAX_COST = 1_000_000_000;
	public static int[][] map;
	public static Node[] cost;

	public static class Node {
		int price, num;
		boolean visited;

		public Node(int p, int n) {
			this.price = p;
			this.num = n;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		map = new int[N + 1][N + 1];
		cost = new Node[N + 1];

		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				map[i][j] = MAX_COST;
			}
		}

		for (int i = 0; i < M; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");

			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());

			map[s][d] = c;
		}

		// 시작도시와 끝도시를 기록
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		start = Integer.parseInt(st.nextToken());
		end = Integer.parseInt(st.nextToken());

		// dijkstra를 사용하기 위해 cost를 초기화한다.
		for (int i = 0; i <= N; i++) {
			cost[i] = new Node(map[start][i], start);
		}

		cost[start].visited = true;
		cost[start].price = 0;

		for (int i = 1; i < N; i++) {
			// 가장 작은 cost를 가진 노드를 찾는다.
			int min = MAX_COST;
			int num = 0;

			for (int j = 1; j <= N; j++) {
				if (!cost[j].visited && cost[j].price < min) {
					min = cost[j].price;
					num = j;
				}
			}

			cost[num].visited = true;
			
			// 노드와 연결된 노드의 cost를 비교 분석하여 삽입한다.
			for (int j = 1; j <= N; j++) {
				if (map[num][j] == MAX_COST || cost[j].visited)
					continue;

				int nCost = cost[num].price + map[num][j];
				if (nCost <= cost[j].price) {
					cost[j].price = nCost;
					if (num == cost[j].num) {
						cost[j].num = cost[j].num < num ? cost[j].num : num;
					} else
						cost[j].num = num;
				}
			}
		}
		
		Stack<Integer> s = new Stack<>();
		s.add(end);
		int num = end;
		while(num != start) {
			s.add(cost[num].num);
			num = cost[num].num;
		}
				
		System.out.println(cost[end].price);
		System.out.println(s.size());
		while(!s.isEmpty()) {
			System.out.printf("%d ",s.pop());
		}
	}
}
