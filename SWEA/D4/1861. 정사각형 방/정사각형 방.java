import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

/*
 * SWEA 1861 정사각형 방
 * 
 * N * N 방이 있을 때, 모든 방에 숫자가 적혀있다.
 * 상하좌우로 이동이 가능하고, 이때 이동할 방이 자신의 방보다
 * "정확히 1" 더 커야한다.
 * 어디방에 있어야 가장 많은 개수의 방을 이동할 수 있는가?
 * 
 * @입력
 * 첫 번째줄에 테스트케이스 T가 주어진다
 * 각 테스트 케이스의 첫 번째 줄에는 하나의 정수 N(1<=N<=10^3)
 * 다음 N개의 줄에는 i번째 줄에는 N개의 정수 A_(i, 1), ..., A_(j, N)
 * 1<=A_i,j<=N^2
 * A_(i,j)는 모두 서로 다른 수이다.
 * 
 * @출력
 * "#t "를 철력 후 처음에 출발하는 방 번호와 최대 몇 개의 방을
 * 이동할 수 있는지 출력한다.
 * 이동할 수 있는 방의 개수가 최대인 방이 여럿이라면 
 * 그 중에서 적힌 수가 가장 작은 것을 출력한다.
 * 
 * @해결방안
 * visited를 사용하여 중복이 없이 전체 완탐을 한다.
 * 하지만 큰숫자가 먼저 들리게 된다면, 작은 숫자에서 갈 수 없기 때문에
 * hashMap으로 인덱스와 좌표를 저장해주고
 * 작은 숫자부터 인덱스 순서대로 불러와서 bfs를 돌린다.
 * 
 * 
 * 
 */

public class Solution {

	static boolean[][] visited;
	static int[][] map;
	static Map<Integer, int[]> hm;
	static int x,y,N, max, cnt, max_i;
	
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			visited = new boolean[N][N];
			hm = new HashMap<>();
			max = Integer.MIN_VALUE;
			
			int tmp;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					tmp = Integer.parseInt(st.nextToken());
					map[i][j] = tmp;
					hm.put(tmp, new int[]{i,j});
				}
			}
			
			for(int i=1; i<=N*N; i++) {
				x = 0;
				y = 0;
				cnt = 1;
				bfs(i, 0);
			}
			
			System.out.print("#" + t + " ");
			System.out.println(max_i + " " + max);
		}
	}

	static void bfs(int idx, int sum) {
//		if(idx==N*N) {
//			max = Math.max(max, sum);
//		}
		
		Stack<int[]> q = new Stack<>();
		
		// Q에 루트를 삽입
		q.push(hm.get(idx));
		
		while(!q.isEmpty()) {
			// 큐의 첫 번째
			// cur[0] : y
			// cur[1] : x
			int[] cur = q.pop();
			
			// 갈수 있는곳 확인
			for(int d=0; d<4; d++) {
				y = cur[0] + dy[d];
				x = cur[1] + dx[d];
				
				// 범위를 벗어나면
				if(x<0 || x>=N || y<0 || y>=N)
					continue;;
				// 한번이라도 들렸다면
				if(visited[y][x])
					continue;
				// 정확히 1차이가 아니면
				if(map[y][x] != map[cur[0]][cur[1]] + 1)
					continue;
				visited[cur[0]][cur[1]] = true;
				q.push(new int[] {y,x});
				cnt++;
				
			}
			if(max < cnt) {
				max = cnt;
				max_i = idx;
			}
		}
	}
}
