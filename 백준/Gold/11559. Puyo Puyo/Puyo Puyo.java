import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/*
 * 백준 11559 뿌요뿌요
 * 
 * 메모리 
 * 시간 
 * 
 * 필드에 여러 가지 색깔의 뿌요를 놓는다.
 * 중력의 영향을 받아 아래에 바닥이나 다른 뿌요가 나올때까지 아래로 떨어진다.
 * 
 * 뿌요가 놓고난후, 같은색 뿌요가 4개이상 상하좌우로 연결되어 있으면 연결된 같은 색
 * 뿌요들이 한꺼번에 없어진다. 이때 1연쇄가 시작
 * 
 * 뿌요가 사라지면 위에 다른 뿌요들이 있다면, 역시 중력의 영향을 받아 차례대로 아래로 떨어진다.
 * 
 * 아래로 떨어지고 또 4개이상 모이게되면 또 터진다.
 * 다시 터짐을 반복할때마다 1연쇄씩 늘어난다.
 * 
 * 터질쑤 있는 뿌요가 여러 그룹이 있다면 동시에 터져야 하고, 여러 그룹이 터지더라도
 * 한번의 연쇄가 추가된다.
 * 
 * 
 * @입력
 * 총 12개의 줄에 필드의 정보가 주어지며, 각 줄에는 6개의 문자가 있다.
 * 이때 .은 빈공간이고, .이 아닌것은 각각의 색깔의 뿌요를 나타낸다.
 * R은 빨강, G는 초록, B는 파랑, P는 보라, Y는 노랑
 * 입력으로 주어지는 필드는 뿌요들이 전부 아래로 떨어진 뒤의 상태이다. 즉, 뿌요 아래에 빈칸이
 * 있는 경우는 없다.
 * 
 * @출력
 * 현재 주어진 상황에서 몇몇 연쇄가 되는지 출력한다. 하나도 터지지 않는다면 0을 출력한다.
 * 
 * @해결방안
 * 1. 12*6의 맵을 선언 및 입력
 * 	1-1. 입력은 그냥 char를 int로 형변환하여 저장
 * 2. 전체를 스캔하면서 floodfill 방식으로 터질 뿌요들 스캔
 * 	2-1. 한번이라도 터지면 cnt++ => boolean으로 체크
 * 	2-2. 다 터지고, 뿌요들을 아래로.
 * 	2-3. 그리고 다시 뿌요터짐
 * 		2-3-1. 이때 다시 cnt++ 
 * 	2-4. 터짐과 중력을 중력동안 안떨어지면 break으로 나오기
 * 
 */

public class Main {

	static int[][] map;
	static boolean[][] visited;
	static boolean cnt_check;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		

		map = new int[12][6];
		int cnt = 0;

		String str;
		for (int i = 0; i < 12; i++) {
			str = br.readLine();
			for (int j = 0; j < 6; j++) {
				map[i][j] = str.charAt(j);
			}
		}

		while (true) {
			cnt_check = false;

			// 중력
			gravity();

			// 뿌요 폭발
			visited = new boolean[12][6];
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if(visited[i][j] || map[i][j] == '.')
						continue;
					bomb(i, j);									
				}
			}
//			print();
			if(cnt_check)
				cnt++;
			else
				break;
		}


		sb.append(cnt);
		System.out.println(sb);
	}
	
	public static void bomb(int r, int c) {
		Queue<int[]> q = new ArrayDeque<>();
		
		boolean check[][] = new boolean[12][6];
		
		q.offer(new int[] {r, c, map[r][c]});
		visited[r][c] = true;
		check[r][c] = true;
		int cnt = 1;
		
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			
			for (int d = 0; d < 4; d++) {
				int nr = cur[0] + dy[d];
				int nc = cur[1] + dx[d];
				
				if(nr < 0|| nc <0 || nr >= 12 || nc >=6)
					continue;

				if(cur[2] != map[nr][nc])
					continue;
				
				if(visited[nr][nc])
					continue;
				visited[nr][nc] = true;
				check[nr][nc] = true;
				
				cnt++;
				q.offer(new int[] {nr, nc, cur[2]});
				
			}
		}
		
		if(cnt >=4 ) {
			cnt_check = true;
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j < 6; j++) {
					if(check[i][j])
						map[i][j] = '.';
				}
			}
		}
	}

	public static void gravity() {
		for (int i = 0; i < 6; i++) {
			for (int j = 11; j >= 0; j--) {
				// 빈칸이라면
				if (map[j][i] == '.') {
					// 위의 칸 갖고오기
					int k = j;
					while (true) {
						if (k == 0)
							break;
						if (map[--k][i] != '.') {
							map[j][i] = map[k][i];
							map[k][i] = '.';
							break;
						}
					}
				}
			}
		}
	}

	public static void print() {
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				System.out.print((char) map[i][j]);
			}
			System.out.println();
		}
		System.out.println();
	}
}