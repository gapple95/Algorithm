import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 백준 4083 트리
 * 
 * 메모리 
 * 시간 
 * 
 * 그래프는 정점과 간선으로 이루어져 있다. 두 정점 사이에 경로가
 * 있다면, 두 장점은 연결되어 있다고 한다.
 * 연결 요소는 모든 정점이 서로 연결되어 있는 정점의 부분집합이다.
 * 그래프는 하나 또는 그 이상의 연결 요소로 이루어져 있다.
 * 
 * 트리는 사이클이 없는 연결 요소이다. 트리에는 여러 성질이 있다.
 * 예를 들어, 트리는 정점이 n개, 간선이 n-1개있다. 또,
 * 임의의 두 정점에 대해서 경로가 유일하다.
 * 
 * 그래프가 주어졌을 때, 트리의 개수를 세는 프로그램을 작성하시오.
 * 
 * @입력
 * 입력은 여러 개의 테스트 케이스로 이루어져 있다.
 * 각 테스트 케이스의 첫째 줄에는 n<=500과 m<=n(n-1)/2을
 * 만족하는 정점의 개수 n과 간선의 개수 m이 주어진다. 다음 m개의
 * 줄에는 간선을 나타내는 두 개의 정수가 주어진다. 같은 간선은
 * 여러 번 주어지지 않는다. 정점은 1번부터 n번까지 번호가 매겨져
 * 있다. 입력의 마지막 줄에는 0이 두 개 주어진다.
 * 
 * @출력
 * 입력으로 주어진 그래프에 트리가 없다면 "No trees"를, 한개라면
 * "There is one tree."를 T개(T>1)라면 "A forest of
 * T trees."를 테스트 케이스 번호와 함께 출력한다.
 * 
 * @해결방안
 * find, union을 구현하여 사용한다.
 * find(a) == find(b)가 성립되면 싸이클이 발생된다고 한다.
 * 트리가 만들어지면 cnt++을 해주자.
 * 크루스칼 기법을 활용하여, 트리가 만들어진 정점들은 true로 해서
 * 트리에 안들어간 정점들을 다시 크루스칼 기법으로 트리로 만들어준다.
 * 총 트리를 만든 것을 cnt로 판별하여 출력
 * 
 */

public class Main {
	
	static class Edge {
		int from;
		int to;
		public Edge(int from, int to) {
			super();
			this.from = from;
			this.to = to;
		}
	}
	
	static int[] parents;
	static int N,M;
	static boolean[] cycleRoot;
	static Edge[] edgeList;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		int t = 0;

		while(true) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			if(N==0 && M == 0)
				break;
			
			sb.append("Case ").append(++t).append(": ");

			cycleRoot = new boolean[N+1];
			parents = new int[N+1];
			for (int i = 1; i <= N; i++) {
				parents[i] = i;
			}
			
			// 간선리스트 만들기
			edgeList = new Edge[M];
			
			for (int i = 0; i < M; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				edgeList[i] = new Edge(a, b);
			}
			
			for (Edge edge : edgeList) {
				if(!union(edge.from, edge.to))
					cycleRoot[find(edge.from)] = true;
			}
			
			int cnt=0;
			for (int i = 1; i <= N; i++) {
				if(find(i)==i) {
					if(cycleRoot[i])
						continue;
					cnt++;
				}
			}
			
//			System.out.println("cnt : " + cnt);
			switch (cnt) {
			case 0:
				sb.append("No trees.\n");
				break;
			case 1:
				sb.append("There is one tree.\n");
				break;
			default:
				sb.append("A forest of ").append(cnt).append(" trees.\n");
				break;
			}
		}
		System.out.println(sb);
	}

	public static int find(int a) {
		if(a == parents[a]) 
			return a;
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		

		
		// 같은 루트라면?
		if(rootA == rootB)
			return false;
		
		parents[rootA] = rootB;
		
		if(cycleRoot[rootA]) cycleRoot[rootB] = true;
		
		return true;
		
	}
}