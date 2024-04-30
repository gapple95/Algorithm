import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 1976 여행 가자
 * 
 * 메모리 
 * 시간 
 * 
 * 도시 N개가 있고 임의의 두 도시 사이에는 길이 있을 수도, 없을 수도 있다.
 * 여행 일정이 주어졌을때, 이 여행 경로가 가능한것인지 알아보자.
 * 중간에 다른 도시를 경유해서 여행을 가도 되고, 같은 도시를 여러번 방문 하는
 * 것도 가능하다.
 * 예를들어 A-B, B-C, A-D, B-D, E-A의 길이 있고
 * 여행계획이 E C B C D라면 E-A-B-C-B-C-B-D라는 여행 경로를 통해
 * 목적을 달성할 수 있다.
 * 
 * @입력
 * 첫 줄에 도시의 수 N이 주어진다. N은 200이하
 * 둘째 줄에 여행 계획에 속한 도시들의 수 M이 주어진다. M은 1000이하이다.
 * 다음 N개의 줄에는 N개의 정수가 주어진다.
 * i번째 줄의 j번째 수는 i번 도시와 j번 도시의 연결 정보를 의미한다.
 * 1이면 연결된것이고, 0이면 연결이 되지 않은 것이다.
 * A와 B가 연결되었으면, B와 A도 연결되어 있다. 마지막 줄에는 여행 계획이
 * 주어진다. 
 * 
 * 도시의 번호는 1부터 N까지 차례대로 매겨져 있다.
 * 
 * @출력
 * 첫 줄에 가능하면 YES 불가능하면 NO를 출력한다.
 * 
 * @아이디어
 * 그래프탐색을 진행하며, 탐색직전 visited배열을 초기화. =>갔던곳도 갈수있다.
 * 만약 자신을 제외한 모든곳을 돌았는데도 원하는 곳을 못간다면 NO
 * 다 돌아서 여행계획만큼 되었다면 YES
 * 
 * 여행계획은 int 배열로 하고, 찾으면 idx를 ++, 그리고 visited배열도 초기화
 * 
 * 
 */

public class Main {

	static int N, M, plan[], idx=0;
	static boolean check;
	static List<Integer>[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());

		plan = new int[M];
		list = new List[N];

		for (int i = 0; i < N; i++) {
			list[i] = new ArrayList<>();
			list[i].add(i);
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				if (tmp == 1) {
					list[i].add(j);
					list[j].add(i);
				}
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			plan[i] = Integer.parseInt(st.nextToken()) - 1;
		}

		
		while(true) {
			if(idx + 1 == M)
				break;
			if(!bfs(plan[idx]))
				break;
		}
		

		sb.append(idx + 1==M?"YES":"NO");

		System.out.println(sb);
	}

	public static boolean bfs(int start) {
		Queue<List<Integer>> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N];
		q.offer(list[start]);
		
		while(!q.isEmpty()) {
			for(int i: q.poll()) {
				if(visited[i])
					continue;
				visited[i] = true;
				
				if(plan[idx+1] == i) {
					idx++;
					return true;
				}
				q.offer(list[i]);
			}
		}
		
		return false;
	}
	
}