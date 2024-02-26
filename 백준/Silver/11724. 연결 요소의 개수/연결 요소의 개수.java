import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/* 
 * 백준 11724 연결 요소의 개수
 * 
 * 메모리
 * 시간
 * 
 * 방향 없는 그래프가 주어졌을 때, 연결 요소(Connected Componet)의 개수를 구하는 프로그램을 작성하시오.
 * 
 * @입력
 * 첫째 줄에 정점의 개수 N과 간선의 개수 M이 주어진다.(1<=N<=1,000, 0<=M<=N(N-1)/2)
 * 둘째 줄부터 M개의 줄에 간선의 양 끝점 u와 v가 주어진다.(1<=u,v<=N, u!=v) 같은 간선은 한번만 주어진다.
 * 
 * @해결방안
 * 간선 리스트로 find-union 기법을 활용해 트리를 완성한다.
 * 
 */

public class Main {

	
	
	static int[] parents;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		
		for (int i = 1; i <= N; i++) {
			parents[i]  = i;
		}
		
		int cnt = N;
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			if(union(u,v))  cnt--;
		}
		
		System.out.println(cnt);
//		System.out.println(Arrays.toString(parents));
		
	}

	public static int find(int a) {
		if(a == parents[a])
			return a;
		return parents[a] = find(parents[a]);
	}
	
	public static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		
		// 싸이클 발생
		if(rootA==rootB)
			return false;
		
		parents[rootB] = rootA;
		
		return true;
	}
	
}