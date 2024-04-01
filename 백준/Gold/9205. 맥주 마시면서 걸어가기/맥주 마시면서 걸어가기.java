import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 9205 맥주 마시면서 걸어가기
 * 
 * 메모리 
 * 시간 
 * 
 * 맥주 한박스에는 맥주가 20개
 * 목이 마르면 안되기 떄문에 50미터당 한병씩 마신다.
 * 
 * 편의점에 들렸을 때, 빈병은 버리고 새맥주병을 살 수 있다.
 * 
 * 박스에 들어있는 맥주는 20병을 넘을 수 없다.
 * 편의점을 나선 직후에도 50미터를 가기전에 맥주 한 병을 마셔야한다.
 * 
 * 편의점, 상근이네 집, 펜타포트 락 페스티벌의 좌표가 주어진다.
 * 페스티벌에 도착할 수 있는지 구하는 프로그램
 * 
 *  @입력
 *  첫째 줄에 테스트 케이스 t(<=500)
 *  각 테스트 케이스 첫째 줄에 맥주를 파는 편의점의 개수 n(0~100)
 *  다음 n+2개 줄에는 상근이네 집, 편의점, 펜타포트 락 페스티벌의 좌표가
 *  두 정수 x와 y로 이루어져 있다. -32768<=x,y<=32767
 *  
 *  직사각형 모양으로 생긴 도시이다. 두 좌표 사이의 거리는 x좌표의 차이+y좌표의 차이이다.
 *  
 *  @출력
 *  각 테스트 케이스에 행복하게 페스티벌에가면 happy
 *  갈수없으면 sad
 * 
 */
public class Main {

	static int[][] pos;
	static boolean[] visited;
	static int N;
	static int mapSize = 65536;
	static int life;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			life = 20;

			N = Integer.parseInt(br.readLine());

			pos = new int[N + 2][2];
			visited = new boolean[N+2];

			for (int i = 0; i < N + 2; i++) {
				st = new StringTokenizer(br.readLine());
				pos[i][1] = Integer.parseInt(st.nextToken());
				pos[i][0] = Integer.parseInt(st.nextToken());
			}

			bfs();
		}
	}

	static void bfs() {
		Queue<int[]> q = new ArrayDeque<>();

		q.offer(new int[] { pos[0][0], pos[0][1]});

		while (!q.isEmpty()) {

			int cur[] = q.poll();

			if (cur[0] == pos[N + 1][0] && cur[1] == pos[N + 1][1]) {
				System.out.println("happy");
				return;
			}

			for (int i = 1; i < N+2; i++) {
				// 도달할 수 있으면,
				int dis = calD(cur[0], cur[1], pos[i][0], pos[i][1]); 
				if(dis <= 1000) {
					if(visited[i])
						continue;
					visited[i] = true;
					q.offer(new int[] {pos[i][0], pos[i][1]});
				}
			}
		}
		System.out.println("sad");
	}

	static int calD(int a_y, int a_x, int b_y, int b_x) {
		return Math.abs(a_y - b_y) + Math.abs(a_x - b_x);
	}
}