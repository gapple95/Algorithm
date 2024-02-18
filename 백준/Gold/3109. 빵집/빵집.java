import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int R, C, cnt = 0;
	static char[][] map;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine().trim());
		R = Integer.parseInt(st.nextToken()); // 크기
		C = Integer.parseInt(st.nextToken());
		map = new char[R][]; // map 만들어 두기
		visited = new boolean[R][C]; // 파이프라인 놓아진것 확인

		for (int i = 0; i < R; ++i) {
			map[i] = in.readLine().toCharArray();
		}
		makePipe();
		System.out.println(cnt);
	}

	// 전체 행마다 제어할 메소드
	private static void makePipe() {
		cnt = 0;

		for (int i = 0; i < R; i++) { // 윗행부터 파이프 놓기
			visited[i][0] = true; // 방문!
			dfs(i, 0);
		}
	}

	// 오른쪽위, 오른쪽, 오른쪽 아래 순서로 탐색하도록 --> 그리디적인 방향 선택
	static int[] dr = { -1, 0, 1 };

	// 각 칸마다 3방향 탐색할 메소드
	private static boolean dfs(int r, int c) {
		if (c == C - 1) { // 기저 조건
			cnt++; // 파이프 개수 추가
			return true; // return true 로 탐색을 종료
		}

		int nr;
		int nc = c + 1;

		for (int d = 0; d < 3; ++d) {
			// nr = 현재 행 위치값 + 델타값
			nr = r + dr[d];
			// 경계 체크, 방문여부, 건물 확인
			if (nr < 0 || nr >= R || nc < 0 || nc >= C || visited[nr][nc] || map[nr][nc] == 'x')
				continue;

			visited[nr][nc] = true; // 방문처리
			if (dfs(nr, nc)) { // 파이프 놓기 성공하면, 다른거 안하고 파이프 놓기 끝
				return true;
			}
//	         visited[nr][nc] = false;// 실패했던, 파이프 놨었던 흑적 되돌리는 부분
			// 되돌리지 않음 --> 이미 뒤의 선택지의 방향은 현재 보다 유리하지 않은 상황
			// 결국, 같은 상황 반복하는 행위

		}
		return false; // 3방향 모두 시도 했는데 재귀를 타지 못한 경우 --> 파이프 놓기 실패

	}

}