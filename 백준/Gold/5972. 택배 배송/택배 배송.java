import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static int N, M, min;
	
	static int[] dist; // 최단 거리 배열
	static List<Node>[] barns;
	
	static class Node implements Comparable<Node>{
		int idx, cost;
		
		public Node(int idx, int cost) {
			this.idx = idx;
			this.cost = cost;
		}

		@Override
		public int compareTo(Main.Node o) {
			return this.cost - o.cost;
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
	
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		min = Integer.MAX_VALUE;
		
		barns = new List[N+1];
		for (int i = 1; i <= N; i++) {
			barns[i] = new ArrayList<>();
		}
		
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());

			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			barns[A].add(new Node(B, C));
			barns[B].add(new Node(A, C));
		}
		
		dijkstra(1, N);
		
		sb.append(dist[N]);
		
		System.out.println(sb);
		
		br.close();
	}
	
	public static void dijkstra(int start, int end) {
		PriorityQueue<Node> pq = new PriorityQueue<>();		
		dist = new int[N+1];
		Arrays.fill(dist, Integer.MAX_VALUE);
		
		pq.add(new Node(start, 0));
		dist[start] = 0;
		
		while(!pq.isEmpty()) {
			Node cur = pq.poll();
			
			// 이미 최단 경로가 갱신된 경우 무시
			if(cur.cost > dist[cur.idx])
				continue;
			
			for(Node next : barns[cur.idx]) {
				int newCost = cur.cost + next.cost;
				if(newCost < dist[next.idx]) {
					dist[next.idx] = newCost;
					pq.add(new Node(next.idx, newCost));
				}
			}
		}
	}

}
