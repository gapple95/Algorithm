import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

/* 
 * 백준 7576 토마토
 * 
 * 메모리
 * 시간
 * 
 * 토마토는 M x N 크기의 상자의 칸에 하나씩 넣어 창고에 보관한다.
 * 토마토들 중에는 잘 읽은것도 있지만, 아직 익지 않은 토마토들도 있을 수 있다.
 * 보관 후 하루가 지나면 익은 토마토들의 인접한 곳에 있는 익지 않은 토마토들은 익은 토마토의 영향을 받아
 * 익게된다. 하나의 토마토의 인접한 곳은 왼쪽, 오른쪽, 앞, 뒤 네 바향에 있는 토마토를 의미한다.
 * 대각선 방향에 있는 토마토들에게는 영향을 주지 못하며, 토마토가 혼자 저절로 익는 경우은 없다고 가정한다.
 * 
 * 철수는 창고에 보관된 토마토들이 며칠이 지나면 다 익게되는지, 그 최소 일수를 알고 싶다.
 * 상자의 일부 칸에는 토마토가 들어있지 않을 수도 있다.
 * 
 * @입력
 * 첫줄에는 상자의 크기 두 정수 M,N이 주어진다. M은 상자의 가로 칸의 수, N은 상자의 세로칸의 수를 나타낸다.
 * 2<= M,N <=1,000이다. 둘째 줄부터는 하나의 상자에 저장된 토마토들의 정보가 주어진다.
 * 하나의 줄에는 토마토 상태가 M개의 정수로 주어진다.
 * 정수 1은 익은 토마토, 정수 0은 익지 않은 토마토, 정수 -1은 토마토가 들어있지 않은 칸이다.
 * 
 * 토마토가 하나 이상 있는 경우만 주어진다.
 * 
 * @출력
 * 토마토가 모두 익을때까지의 최소 날짜를 출력한다.저장될때부터 모든 토마토가 익어있는 상태라면 0
 * 토마토가 모두 익지는 못하는 상황이면 -1을 출력해야한다.
 * 
 * @해결방안
 * 최대 1000*1000이 되는 경우에는 최대 2000번 안에 된다.
 * 그렇다면 1,000 * 1,000 * 2,000 = 2,000,000,000
 * 최악으로는 20억은 해야하므로 완탐은 불가능.
 * 
 * 배열의 값을 받아올때 익은 토마토의 좌표를 q에 넣기
 * 배열이 끝나면
 * q에 있는 익은 토마토의 주변을 꺼내면서 주변 토마토를 넣어주기
 * 계속해서 visited로 true를 해주기.
 * 
 * 최대로 돌릴수 있는 것은 N+M-2;
 * 최대로 돌렸는데 안익은 토마토가 있다. 바로 끝내고 -1출력
 * 
 * 
 */

public class Main {

	static Map<int[], Integer> fresh = new HashMap<>();
	static boolean[][] visited;
	static int[][] map;

	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		int num = 0;
		int answer = 0;
		map = new int[N][M];
		visited = new boolean[N][M];

		Queue<int[]> q = new ArrayDeque<>();

		int tmp;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				tmp = Integer.parseInt(st.nextToken());
				if (tmp == 1) {
					q.offer(new int[] { i, j });
					visited[i][j] = true;
				} else if(tmp == 0){
					num++;
				}
				map[i][j] = tmp;
			}
		}

		int size = 0;
		
		while (!q.isEmpty() && num!=0) {
			if(size == 0) {
				size = q.size();
				answer++;
			}
			int[] current = q.poll();
			

			for (int d = 0; d < 4; d++) {
				int _y = current[0] + dy[d];
				int _x = current[1] + dx[d];
				if (_y < 0 || _y >= N || _x < 0 || _x >= M)
					continue;
				
				if(visited[_y][_x])
					continue;

				if (map[_y][_x] == 0) { // 안 익은 토마토라면
					map[_y][_x] = 1;
					visited[_y][_x] = true;
					q.offer(new int[] { _y, _x });
					num--;
				}
			}
			size--;
		}

		if (num != 0)
			System.out.println(-1);
		else
			System.out.println(answer);
	}
}