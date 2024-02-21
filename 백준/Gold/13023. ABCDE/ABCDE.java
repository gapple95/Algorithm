import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/*
 * 백준 13023 ABCDE
 * 
 * 시간
 * 메모리
 * 
 * BOJ 알고리즘 캠프에는 총 N명이 참가하고 있다. 사람들은 0번 부터 N-1번으로 번호가 매겨져 있고,
 * 일부 사람들은 친구이다.
 * 
 * 오늘은 다음과 같은 친구 관계를 가진 사람 A,B,C,D,E가 존재하는지 구해보려고한다.
 * 
 * A는 B와 친구다.
 * B는 C와 친구다.
 * C는 D와 친구다.
 * D는 E와 친구다.
 * 
 * 위와 같은 친구 관계가 존재하는지 안하는지 구하는 프로그램을 작성하시오.
 * 
 * @입력
 * 첫째줄에 사람의 수 N(5<= N <= 2,000)과 친구 관계의 수(1<= M <= 2,000)이 주어진다.
 * 둘째 줄부터 M개의 줄에는 정수 a와 b가 주어지며, a와 b가 친구라는 뜻이다.
 * (0<=a,b<=N-1, a!=b) 같은 친구 관계가 두 번 이상 주어지는 경우는 없다.
 * 
 * @출력
 * 문제의 조건에 맞는 A,B,C,D,E가 존재하면 1을 없으면 0을 출력한다.
 * 
 * @해결방안
 * 1. dfs로 깊이가 5인 친구를 찾는다.
 *    - 찾자마자 1을 출력하고 종료
 *    - 최악은 마지막에 찾기
 *    - 그래프를 작성하고 (M) 각각 N개안에 5개를 확인해야한다.
 *    - 시간 복잡도는 5^N * M = 5^2000 * 2000으로 딱봐도 넘은거 같음
 *    - 가지치기로 depth가 4일 때 확인하여 바로 출력하고 종료하기.
 *    - 아니더라도 그냥 나와버리기.
 * 
 * 2. 서로소 집합을 구현하고, 길이가 5이상인 집합이 찾기
 *    - 찾자마자 1을 출력하고 종료
 *    - 처음 서로소 집합 만들고(N) find는 한번만 N 나머지 M개만큼 만들기
 *    - 마지막으로 탐색 N
 *    - 시간 복잡도는 N + N * M + N = 2000 * (2+ 2000) = 4008000?
 *    => 요 아이디어는 한명이 한꺼번에 5명과 친구라면 틀린다. 아래는 반례
 *    	    6 5
			0 1
			0 2
			0 3
			0 4
			0 5
	
 *  결론. DFS로 depth가 4가 되면 가지를 치자! -> 조건에 부합하면 바로 1 출력후 종료
 *  
 */

public class Main {

	static int N, M;
	static List<Integer>[] friends;
	static boolean[] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		friends = new List[N];

		// 배열리스트 만들기
		for (int i = 0; i < N; i++) {
			friends[i] = new ArrayList<>();
		}

		// 간선 넣기
		int a, b;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			friends[a].add(b);
			friends[b].add(a);
		}

		for (int i = 0; i < N; i++) {
			visited = new boolean[N];
			visited[i] = true;
			dfs(i, 0);
		}
		System.out.println(0);
	}

	public static void dfs(int i, int level) {
		visited[i] = true;
		if (level == 4) {
			System.out.println(1);
			System.exit(0);
		}
		if (level > 4)
			return;
		for (int node : friends[i]) {
			if (visited[node])
				continue;
			visited[node] = true;
			dfs(node, level + 1);
			visited[node] = false;
		}
	}
}