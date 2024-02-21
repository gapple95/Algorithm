import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 1260 DFS와 BFS
 * 그래프를 DFS로 탐색한 결과와 BFS로 탐색한 결과를 출력하는 프로그램을 작성하시오. 단, 방문할
 * 수 있는 정점이 여러 개인 경우에는 정점 번호가 작은 것을 먼저 방문하고, 더 이상 방문 할 수 있는 점이 없는 경우
 * 종료한다. 정점 번호는 1번부터 N번까지이다.
 * 
 * @입력
 * 첫째 줄에 정점의 개수 N(1이상 1,000이하) , 간선의 개수M(1이상 10,000이하), 탐색을 시작할 정점의 번호 V가 주어진다
 * 다음 M개의 줄에는 간선이 연결하는 두 정점의 번호가 주어진다. 어떤 두 정점 사이에 여러 개의 간선이
 * 있을수도 있다. 입력으로 주어지는 간선은 양방향이다.
 * 
 * @출력
 * 첫째 줄에 DFS를 수행한 결과를, 그 다음 줄에는 BFS를 수행한 결과를 출력한다. V부터 방문된 점을 순서대로 출력하면 된다.
 * 
 * @해결방안
 * ArrayList로 구현한 BFS, DFS를 구현하여 각줄에 출력한다.
 * 단, 조건중에 정점이 여러개인 경우 그중 가장 작은 것을 먼저 방문한다고 하니, 항상 sort를 생각하자.
 * 
 * BFS는 우선순위 큐를 => 가장 작은 것을 노드로 출력하기 위하여
 * DFS는 스택으로 풀기 => 스택에 넣을때 큰것을 먼저.
 * 
 */

public class Main {
	
	static int N,M,v;
	static int[][] graph;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		v = Integer.parseInt(st.nextToken());
		
		graph = new int[N+1][N+1];
		
		int from,to;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			graph[from][to] = graph[to][from] = 1;
		}
		dfs(v);
		System.out.println();
		bfs(v);
	}

	public static void bfs(int v) {
		Queue<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[graph.length+1];
		q.offer(v);
		visited[v] = true;
		
		while(!q.isEmpty()) {
			int current = q.poll();
			System.out.print(current + " ");
			
			// 인접 정점들 확인
			for (int i = 1; i <= N; i++) {
				if(graph[current][i] != 0 && !visited[i]) {
					q.offer(i);
					visited[i] = true;
				}
			}
		}
	}
	
	public static void dfs(int v) {
		Deque<Integer> stack = new ArrayDeque<>();
		boolean[] visited = new boolean[graph.length+1];
		stack.push(v);
//		visited[v] = true;
		while(!stack.isEmpty()) {
			int current = stack.pop();
			if(visited[current])continue;
			visited[current] = true;
			System.out.print(current + " ");
			
			// 스택에는 거꾸로 넣어줘야 작은 순서대로 간다.
			for(int i=N; i>=1; i--) {
				if(graph[current][i] != 0 && !visited[i]) {
					stack.push(i);
//					visited[i] = true;
				}
			}
		}
	}
}