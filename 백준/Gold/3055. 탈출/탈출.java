import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/* 
 * 백준 3055 탈출
 * 
 * 메모리 
 * 시간 
 * 
 * 숲의 지도는 R행 C열로 이루어져 있고, 비어있는곳은 '.', 물이 차있는 지역은 '*', 돌은 'X'로 표시되어있다.
 * 비버의 굴은 'D'로, 고슴도치의 위치는 'S'로 나타내어져있다.
 * 
 * 매분마다 고슴도치는 상하좌우 중 하나로 이동할 수 있다. 물도 매분마다 비어있는 칸으로 확장한다.
 * 물이 있는 칸과 인접해있는 비어있는 칸(적어도 한변을 공유)은 물이차게 된다. 물과 고슴도치는
 * 돌을 통과할 수 없다. 또, 고슴도치는 물로 차있는 구역으로 이동할 수 없고, 물도 비버의 소굴로 이동할 수 없다.
 * 
 * 고슴도치가 비버의굴로 이동하기 위해 필요한 최소 시간을 구하는 프로그램을 작성
 * 
 * 고슴도치는 물이 찰 예정인 칸으로 이동할 수 없다. 즉, 다음 시간에 물이 찰 예정인 칸으로 고슴도치는
 * 이동할 수 없다. 이동할 수 있으면 고슴도치가 물에 빠지기 때문이다.
 * 
 * @입력
 * 첫째 줄에 50보다 작은 R과 C가 주어진다.
 * 다음 R개 줄에는 숲의 지도가 주어지면 D와 S는 하나씩만 주어진다.
 * 
 * @출력
 * 첫재줄에 비버의 굴로 이동할 수 있는 가장 빠른 시간을 출력한다. 만약, 안전한게 비버의굴로
 * 이동할 수 없다며 "KAKTUS"를 출력한다.
 * 
 * @해결방안
 * 물은 Flood Fill 만들고,
 * 비버의 이동은 레벨별 BFS로 구현하여 만든다.
 * 각각 (50*50) *(50*50)의 시간복잡도로 1초내로는 가능할듯?
 * 
 * 물과 도치의 이동 visited는 따로 구현
 * 순서는 물을 먼저 Flood Fill하고, 그다음에 고슴도치의 이동
 * 
 * 희정이 아이디어
 * 큐는 하나로 사용하여 물과 고슴도치의 이동을 구현한다.
 * visited는 하나로 사용하여 물은 무조건 Flood Fill로 true(지도에서 .과 S일때 채우기)
 * 고슴도치는 false 인 곳만 골라서 이동. 물과 고슴도치를 구분하는 node 클래스를 구현
 * 더 빠르게 하기 위해 클래스 말고 int[]로 {r, c, 고슴도치:1/물:0, 이동거리}를 넣는다.
 * 
 * 시간복잡도는 50*50
 * 
 */

public class Main {

	static int R, C, map[][], min = Integer.MAX_VALUE, hasic[];
	static boolean visited[][];
	static List<int[]> water;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		map = new int[R][C];
		visited = new boolean[R][C];
		water = new ArrayList<>();

		for (int i = 0; i < R; i++) {
			String str = br.readLine();
			for (int j = 0; j < C; j++) {
				int tmp = str.charAt(j);
				if (tmp == '*')
					water.add(new int[] { i, j, 0 , 0});
				else if (tmp == 'S')
					hasic = new int[] { i, j, 1 , 0};

				map[i][j] = tmp;
			}
		}

		bfs();
	}

	public static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();
		for (int[] i : water) {
			q.offer(i);
		}
		q.offer(hasic);

		while (!q.isEmpty()) {
			int[] cur = q.poll();
			
			if(cur[2] == 1 && map[cur[0]][cur[1]] == 'D') {
				System.out.println(cur[3]);
				System.exit(0);
			}
				
			
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dy[d];
				int nc = cur[1] + dx[d];
				
				// 경계체크
				if(nr <0 || nc <0 || nr >= R || nc >= C)
					continue;
				if(map[nr][nc] == 'X') // 고슴도치도 물도 통과 못한다.
					continue;
				if (cur[2] == 1) { // 1: 고슴도치
					if(map[nr][nc] == '*') // 물이라면 통과 못한다.
						continue;
					if(visited[nr][nc])
						continue;
					visited[nr][nc] = true;
					q.offer(new int[] {nr, nc, 1, cur[3]+1});
					
				} else { // 0: 물
					if(map[nr][nc] == 'D' || map[nr][nc] == '*')
						continue;
					map[nr][nc] = '*';
					q.offer(new int[] {nr, nc, 0, 0}); // 물은 range가 필요없다.
				}
			}
		}
		System.out.println("KAKTUS");
	}

}