/*
 * 백준 14503 로봇 청소기
 * 
 * 메모리 
 * 시간 
 * 
 * 맵은 (0,0) ~ (N-1,M-1)
 * 청소기 움직임
 * 
 * 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
 * 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
 * 		2-1. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
 * 		2-2. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
 * 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
 * 		3-1. 반시계 방향으로 90도 회전한다.
 * 		3-2. 바라보는 방향을 기준으로 앞쪽칸이 청소되지 않은 빈 칸인
 * 			 경우 한 칸 전진한다.
 * 		3-3. 1번으로 돌아간다.
 * 
 * 
 * @입력 
 * 첫째 줄에 방의 크기 N과 M이 입력된다. (3<=N,M<=50)
 * 둘째 줄에 로봇청소기가 있는 칸의 좌표(r,c)와 처음 로봇 청소기가
 * 바라보는 방향 d가 입력된다.
 * d가 0인 경우 북쭉, 1인 경우 동쪽, 2인 경우 남쪽, 3인 경우 서쪽
 * 
 * 셋째 줄부터 N개의 줄에 각 장소의 상태를 나타내느 NxM개의 값이 
 * 한줄에 M개씩 입력
 * 이 값이 0인경우(i,j)가 청소되지 안흔 빈칸이고, 1인 경우벽이다.
 * 로봇이 있는 칸은 항상 빈칸이다.
 * 
 * @출력
 * 로봇 청소기가 작동을 시작한 후 작동을 멈출 때까지 청소하는 칸의 개수를 출력
 * 
 * @해결방안
 * 청소기 클래스를 만들고, 위치와 상태를 업데이트를 계속 진행한다.
 * 청소기가 들린곳, 즉 청소가 된곳은 boolean 타입의 따른 2차원 배열로 기록
 * 상우하좌 순으로 확인
 * 만약 모든 칸이 청소되어 있다면,
 * => 현재 보는 방향으로 뒤로 한칸가기
 * => 이때, 뒤로 못간다면 그 즉시 프로그램 종료
 * 만약 청소가 안 되어 있다면,
 * => 90도씩 회전하고, 청소되더 있으면 더 회전하낟.
 * => 청소가 안되어있는 부분을 만나면 그쪽으로 한 칸 전진
 * 
 * 그냥 한번에 돌면서 확인하면 안될려나..?
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static class Robot {
		int r, c, d;

		public Robot(int r, int c, int d) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
		}

		public void spinD() {
			d--;
			if (d < 0)
				d = 3;
		}

	}

	static int N, M, map[][], cnt;
	static boolean visited[][];
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };
	static Robot robot;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		map = new int[N][M];
		visited = new boolean[N][M];
		cnt = 0;

		st = new StringTokenizer(br.readLine());
		robot = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()),
				Integer.parseInt(st.nextToken()));

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		while (true) {
			// 1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
			if (!visited[robot.r][robot.c]) {
				visited[robot.r][robot.c] = true;
				cnt++;
			}

			// 현재 칸의 4칸을 확인
			boolean check = false;
			for (int d = 0; d < 4; d++) {
				int nr = robot.r + dy[d];
				int nc = robot.c + dx[d];

				// 빈칸인데, 들린적이 없다면
				if (map[nr][nc] == 0 && visited[nr][nc] == false) {
					check = true;
					break;
				}
			}

			// 2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
			if(!check) {
				// 2-1. 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면, 한 칸 후진하고 1번으로
				if(map[robot.r - dy[robot.d]][robot.c - dx[robot.d]] != 1) {
					robot.r -= dy[robot.d];
					robot.c -= dx[robot.d];
				}
				// 2-2. 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
				else {
					sb.append(cnt);
					break;
				}
			}
			// 3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
			else {
				// 3-1. 반시계 방향으로 90도 회전한다.
				robot.spinD();
				// 3-2. 바라보는 방향을 기준으로 앞쪽칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
				if(!visited[robot.r + dy[robot.d]][robot.c + dx[robot.d]] &&
						map[robot.r + dy[robot.d]][robot.c + dx[robot.d]] != 1) {
					robot.r += dy[robot.d];
					robot.c += dx[robot.d];
				}
				// 3-3. 1번으로 보낸다.
			}
		}
		System.out.println(sb);
	}

}