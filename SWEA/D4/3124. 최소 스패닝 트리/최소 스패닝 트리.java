import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/* 
 * SWEA 3124 최소 스패닝 트리
 * 
 * 메모리 
 * 시간 
 * 
 * 그래프가 주어졌을 대, 그 그래프의 최소 스패닝 트리를 구하는 프로그램을 작성하시오.
 * 최소 신장 트리란, 주어진 그래프의 모든 정점들을 연결하는 부분 그래프 중에서 
 * 그 가중치의 합이 최소인 트리를 말한다.
 * 
 * @입력
 * 가장 첫 번째 줄에는 전체 test case의 수 (1~10)가 주어진다.
 * 각 케이스의 첫째 줄에는 정점의 개수 V(1~100,000)와 간선의 개수 E (1~200,000)
 * 주어진다.
 * 다음 E개의 줄에는 각 간선에 대한 정보를 나타내는 세 정수 A,B,C가 주어진다.
 * A번 정점, B번 정ㅈ머이 가중치 C인 간선으로 연결되어 있다는 의미이다.
 * C는 음수 일 수도 있으며, 절대값이 1,000,000을 넘지 않는다.
 * 
 * @출력
 * 각 테스트 케이스 수 #t 와 최소 스패팅 트리의 가중치를 출력한다.
 * 
 * @해결방안
 * MST를 구현한다.
 * FindUnion을 이용하여 MST를 구현
 * 0. 간선 리스트를 만들고, 정렬한다.
 * 1. make-set
 * 2. 정렬된 간선 하나씩 꺼내어 신장트리 생성
 * 2-1. 간선리스트를 하나씩 꺼내어 Union을 한다.
 *      이 때, 싸이클이 발생하면 다른 간선으로.
 * 2-2. weight를 중첩한다.
 * 2-3. 돌면서 모든 정점(V)보다 하나 작을 때까지 돌기 
 * 
 */

public class Solution {

	// 간선리스트 노드 클래스
	static class Node implements Comparable<Node> {
		int from, to;
		long weight;

		public Node(int from, int to, long weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Node o) {
			return Long.compare(this.weight, o.weight);
		}

	}

	static int[] parents;
	static PriorityQueue<Node> queue; // 간선 정보 저장

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			// 간선 리스트
			queue = new PriorityQueue<>();
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				long c = Long.parseLong(st.nextToken());

				queue.offer(new Node(a, b, c));
			}

			// 1. make-set
			parents = new int[V + 1];
			for (int i = 1; i <= V; i++) {
				parents[i] = i;
			}

			// 간선리스트를 하나씩 꺼내어 신장트리 만들기
			long weight = 0;
			while (!queue.isEmpty()) {
				Node cur = queue.poll(); // 가중치가 가장 작은 간선
				// 부모노드가 다를때만 (사이클X)
				if (find(cur.from) != find(cur.to)) {
					union(cur.from, cur.to);
					weight += cur.weight;
				}
			}
			System.out.println("#" + t + " " + weight);
		}
	}

	public static int find(int a) {
		if (a == parents[a])
			return a;
		return parents[a] = find(parents[a]);
	}

	public static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);

		if (rootA == rootB)
			return false;

		if(rootA < rootB) {
			parents[rootB] = rootA;
		} else {
			parents[rootA] = rootB;
		}
		return true;
	}
}