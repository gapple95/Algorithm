import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * 백준 17135 캐슬 디펜스
 * 
 * 메모리 33196
 * 시간 176
 * 
 * 캐슬 디펜스는 성을 향해 몰려오는 적을 잡는 턴 방식의 게임이다.
 * 게임이 진행되는 곳은 크기가 NxM인 격자판으로 나타낼수있다.
 * 격자판은 1x1 크기의 칸으로 나누어져 있고, 각 칸에 포함된 적의 수는 최대
 * 하나이다.
 * 
 * 격자판의 N번행의 바로 아래(N+1번행)의 모든 칸에는 성이 있다.
 * 
 * 성을 적에게 지키기 위해 궁수 3명을 배치하려고한다. 궁수는 성이 있는 칸에
 * 배치할 수 있고, 하나의 칸에는 최대 1명의 궁수만 있을 수 있다. 각각의 턴마다 
 * 궁수는 적 하나를 공격할 수 있고, 모든 궁수는 동시에 공겨한다.
 * 궁수가 공격하는 적은 거리가 D이하인 적 중에서 가장 가까운 적이고, 그러한 적이
 * 여럿일 경우에는 가장 왼쪽에 있는 적을 공격한다. 같은 적이 여러 궁수에게
 * 공격당할 수 있다. 공격받은 적은 게임에서 제외된다. 궁수의 공격이 끝나면,
 * 적은 아래로 한칸 이동하며, 성이 있는 칸으로 이동한 경우에는 게임에서 제외된다.
 * 모든 적이 격자판에서 제외되면 게임이 끝난다.
 * 
 * 거리는 |r1-r2| + |c1-c2|
 * 
 * @입력
 * 첫째 줄에 격자판 해으이 수 N, 열의 수 M, 궁수의 공격 거리 제한 D가 주어진다.
 * 둘째 줄부터 N개의 줄에는 격자판의 상태가 주어진다. 0은 빈칸, 1은 적이 있는칸이다.
 * 
 * @출력
 * 첫째 줄에 궁수의 공격으로 제거할 수 있는 적의 최대 수를 출력한다.
 * 
 * @해결방안
 * HashSet을 사용하여 궁수가 공격한 적의 좌표를 저장 -> 중복은 제거가 되기 때무에
 * Set의 크기가 처치한 적의 수가 된다.
 * 적의 수를 카운트 후, 해당 적의 좌표를 0으로 수정.
 * 특별히 적이 내려 오게 끔 하지말고, 궁수가 직접 올라가면서 잡기. 그렴 map의 변경이
 * 많이 일어나지는 않을꺼같다.
 * 턴은 N개만큼만 돌리기. -> 나중에 기저조건을 잘 정하면 최적화 가능할꺼같다.
 * 
 * 열의 개수에서 3개를 선택하는 조합을 구현해야한다. 최대 15_C_3
 * 
 */

public class Main {

	static int N, M, D;
	static int[][] map, tmp_map;
	static Map<int[], Integer> enemy;
	static int[] dx = {-1,0,1};
	static int[] dy = {0,-1,0};
	static boolean[][] visited;
	static boolean[] selected;
	static int[] picked;
	static int max = Integer.MIN_VALUE;
	static int idx;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		enemy = new HashMap<>();
		
		map = new int[N+1][M];
		tmp_map = new int[N+1][M];
		
		int tmp;
		int en = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j <M; j++) {
				tmp = Integer.parseInt(st.nextToken());
				map[i][j] = tmp;
//				if(tmp == 1) {
//					enemy.put(en++, new int[] {i,j});
//				}
			}
		}
		
		picked = new int[3];
		selected = new boolean[M];
		
		comb(0,0);
		
		System.out.println(max);
		
	}
	
	public static void comb(int idx, int start) {
		if(idx == 3) {
			for(int i=0; i<N; i++) {
				System.arraycopy(map[i], 0, tmp_map[i], 0, M);
			}
//			System.out.println(Arrays.toString(picked));
			int cnt = 0;
			for(int i=N;i>=1; i--) {
//				System.out.println(i);
				bfs(i, picked[0]);
				bfs(i, picked[1]);
				bfs(i, picked[2]);	
				
				for(int[] key: enemy.keySet()) {
					if(tmp_map[key[0]][key[1]] == 1) {
						cnt++;
						tmp_map[key[0]][key[1]] = 0;
					}
				}
//				print(tmp_map);
				enemy.clear();
//				System.out.println("cur cnt : " + cnt);
			}
			max = Math.max(max,  cnt);
//			System.out.println("cur max : " + max);
			
			return;
		}
		
		for(int i=start; i<M; i++) {
			picked[idx] = i;
			comb(idx+1, i+1);
		}
	}
	
	// D range안에 발견한다면 적 쏘기.
	public static void bfs(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r,c});
		visited = new boolean[N+1][M];
		visited[r][c] = true;
		int size = q.size();
		int d = 0;
		while(!q.isEmpty()) {
			if(size ==0) {
				size = q.size();
				d++;
				if(d==D)
					break;
			}
			int[] cur = q.poll();
			
			for (int dir = 0; dir < 3; dir++) {
				int nr = cur[0] + dy[dir];
				int nc = cur[1] + dx[dir];

				// 현재 행보다 높거나 벗어나거나
				if(nr >= r || nr <0 || nc < 0 || nc >= M)
					continue;
				if(visited[nr][nc])
					continue;
				if(tmp_map[nr][nc] == 1) {
					enemy.put(new int[] {nr, nc}, idx++);
					return;
				}
				q.offer(new int[] {nr, nc});
			}
			size--;
		}
	}
	
	public static void print(int[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}