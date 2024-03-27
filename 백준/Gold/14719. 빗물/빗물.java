import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 14719 빗물
 * 
 * 메모리 
 * 시간 
 * 
 * 2차원 세계에 블록이 쌓여있다. 비가 왔을 때 빗물이 고이는
 * 곳이 있을 때, 고이는 빗물의 총량은 얼마일까?
 * 
 * @입력
 * 첫 째줄에 2차원 세계의 세로기 H와 가로길이 W가 주어진다.
 * H, W(1~500)
 * 
 * 두 번째 줄에는 블록이 쌓인 높이를 의미하는 0~H이하의 정수가
 * 2차원 세계의 맨 왼쪽 위치부터 차례대로 W개 주어진다.
 * 
 * 따라서 블록 내부의 빈 공간이 생길 수 없다. 또 2차원 세계의
 * 바닥은 항상 막혀있다고 가정하여도 좋다.
 * 
 * @출력
 * 한칸당 용량으 1이다. 고이는 빗물의 총량을 출력하여라.
 * 빗물이 전혀 고이지 않을 경우 0을 출력하여라.
 * 
 * @해결방안
 * visited를 활용한 floodFill을 활용하자.
 * 아래부터 활용하여 자신의 가로줄만을 채우는 것.
 * 가로줄에 벽이 없없다면 그 줄은 사용 못한다고 봐야한다.
 * 
 * 낮은 시작점들을 따로 저장해서 돌려보자.
 * 벽은1, 빈공간 0, 물은 2?
 * 
 * 
 */

public class Main {

	static int H, W, map[][];
	static boolean checked[][];

	static int[] dx = { -1, 1 };
	static Queue<int[]> queue = new ArrayDeque<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		List<int[]> list = new ArrayList<>();

		H = Integer.parseInt(st.nextToken());
		W = Integer.parseInt(st.nextToken());

		map = new int[H][W + 2];

		int height = 0;
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= W; i++) {
			height = Integer.parseInt(st.nextToken());
			for (int j = 0; j < height; j++) {
				map[j][i] = 1;
				list.add(new int[] { j, i });
			}
		}
		
		int cnt = 0;

		for (int[] brick : list) {
			
			int nc = brick[1] - 1;
			int move = 0;
			// 좌
			while (true) {
				if(nc <= 0) {
					for (int i = 0; i < move; i++) {
						cnt--;
						map[brick[0]][nc] = 0;
						nc++;
					}
					break;
				}
				if(map[brick[0]][nc] == 2)
					break;
				if(map[brick[0]][nc] == 0) {
					cnt++;
					move++;
					map[brick[0]][nc] = 2;
					nc--;
				}
				else if (map[brick[0]][nc] == 1)
					break;
			}
			
			nc = brick[1] + 1;
			move = 0;
			// 우
			while (true) {
				if(nc > W) {
					for (int i = 0; i < move; i++) {
						cnt--;
						map[brick[0]][nc] = 0;
						nc--;
					}
					break;
				}
				if(map[brick[0]][nc] == 2)
					break;
				if(map[brick[0]][nc] == 0) {
					cnt++;
					move++;
					map[brick[0]][nc] = 2;
					nc++;
				}
				else if (map[brick[0]][nc] == 1)
					break;
			}
		}

		System.out.println(cnt);
	}

}