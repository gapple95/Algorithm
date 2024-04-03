import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 16236 아기 상어
 * 
 * 메모리 
 * 시간 
 * 
 * NxN 크기에 물고기 M마리와 아기 상어 1마리가 있다.
 * 한칸에는 물고기가 최대 1마리 존재
 * 
 * 아기 상어와 물고기는 모두 크기를 가지고 있고, 이 크기는 자연수이다.
 * 가장 처음엔 아기 상어의 크기는 2이고, 아기 상어는 1총에 상하좌우로 한칸씩 이동
 * 
 * 아기 상어는 자신의 크기보다 큰 물고기 있는 칸은 지나갈 수 없고, 나머지 칸은 모두
 * 지나갈 수 있다.
 * 아기 상어는 자신의 크기보다 작은 물고기만 먹을 수 있다.
 * 즉, 크기가 같은 물고기는 먹을 수 없지만, 그 물고기가 있는 칸은 지나갈 수 있다.
 * 
 * 아기 상어가 어디로 이동할지 결정하는 방법은 아래와 같다.
 * 
 * * 더 이상 먹을 수 있는 물고기가 공간에 없다면 아기 상어는 엄에게 도움을 요청
 * * 먹을 수 있는 물고기가 1마리라면, 그 물고기를 먹으러 간다.
 * * 먹을 수 있는 물고기가 1마리보다 많다면, 거리가 가장 가까운 물고기를 먹으러간다.
 * * 거리는 아기 상어가 있는 칸에서 물고기가 있는 칸으로의 이동, 지나야하는 칸의
 *   개수의 최솟값이다.
 * * 거리가 가까운 물고기가 많다면, 가장 위에 있는 물고기, 그러한 물고기가 여러마리면,
 *   가장 왼쪽에 있는 물고기를 먹는다.
 *   
 * 
 * 아기상어의 이동은 1초, 물고기를 먹는데 걸리는 시간은 없다.
 * 먹으면 그 칸은 빈칸
 * 
 * 아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1증가한다.
 * 예를 들어, 크기가 2인 아기 상어는 물고기를 2마리 먹으면 크기가 3이된다.
 * 
 * 공간의 상태가 주어졌을 때, 아기 상어가 몇 초 동안 상어에게 도움을 요청하지 않고,
 * 물고기를 잡아먹을 수 있는지 구하는 프로그램을 작성하시오.
 * 
 * @입력
 * 첫째 줄에 공간의 크기 N(2~20)
 * 들재 줄부터 N개의 줄에 공간의 상태가 주어진다.
 * 공간의 상태는 0,1,2,3,4,5,6,9로 이루어져 있고, 아래와 같다.
 * 
 * 0: 빈 칸
 * 1, 2, 3, 4, 5, 6: 칸에 있는 물고기의 크기
 * 9: 아기 상어의 위치
 * 
 * @출력
 * 아기 상어가 엄마 상어에게 도움을 요청하지 않고 물고기를 잡아먹을 수 있는 시간을 출력
 * 
 */

public class Main {

	static class Fish {
		int r, c, size, eat;

		public Fish(int r, int c, int size, int eat) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.eat = eat;
		}

		public void eatFish() {
			eat++;
			if (eat == size) {
				eat = 0;
				size++;
			}
		}

	}

	static int N, map[][], time;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static List<Fish> fishList;
	static PriorityQueue<Fish> target_list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		N = Integer.parseInt(br.readLine());

		int shark_r = 0;
		int shark_c = 0;

		map = new int[N][N];
		fishList = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				map[i][j] = tmp;

				if (tmp >= 1 && tmp <= 6) {
					fishList.add(new Fish(i, j, tmp, 0));
				}

				if (tmp == 9) {
					shark_r = i;
					shark_c = j;
					map[i][j] = 0;
				}
			}
		}

		Fish s = new Fish(shark_r, shark_c, 2, 0);

		target_list = new PriorityQueue<>((o1, o2) -> {
			if (o1.r > o2.r)
				return 1;
			else if (o1.r < o2.r)
				return -1;
			else {
				if (o1.c > o2.c)
					return 1;
				else if (o1.c < o2.c)
					return -1;
				else
					return 0;
			}
		});

		findFish(s);

		System.out.println(time);
	}

	public static void findFish(Fish start) {
		Queue<Fish> q = new ArrayDeque<>();
		boolean[][] visited = new boolean[N][N];

		visited[start.r][start.c] = true;
		map[start.r][start.c] = 0;
		q.offer(start);
		int size = q.size();
		boolean check = false;

		int level = 0;

		while (!q.isEmpty()) {
			if (size == 0) {

				size = q.size();
				level++;

				// 레벨안에 먹을것이 있었다면
				if (check) {
					// 상어를 다시 받기
					Fish shark = target_list.poll();
					map[shark.r][shark.c] = 0;

					time += level;
					level = 0;

//					System.out.println("# " + time + ", shark level : " + shark.eat + "/" + shark.size);
//					for (int i = 0; i < N; i++) {
//						for (int j = 0; j < N; j++) {
//							if (shark.r == i && shark.c == j)
//								System.out.print("■" + " ");
//							else
//								System.out.print(map[i][j] + " ");
//						}
//						System.out.println();
//					}
//					System.out.println();

					// 초기화
					q.clear();
					target_list.clear();
					visited = new boolean[N][N];
					visited[shark.r][shark.c] = true;
					check = false;

					// 다시 q에 넣고, 레벨별로 가장 가까운곳 찾으러 가기
					q.offer(shark);
					size = q.size();
				}
			}

			Fish cur = q.poll();

			for (int d = 0; d < 4; d++) {
				int nr = cur.r + dy[d];
				int nc = cur.c + dx[d];

				if (nr < 0 || nc < 0 || nr >= N || nc >= N)
					continue;

				if (map[nr][nc] > cur.size)
					continue;

				if (visited[nr][nc])
					continue;
				visited[nr][nc] = true;

				q.offer(new Fish(nr, nc, cur.size, cur.eat));

				// 먹이 물고기 발견
				if (map[nr][nc] != 0 && map[nr][nc] < cur.size) {
					check = true;
					target_list.offer(new Fish(nr, nc, cur.eat + 1 == cur.size?cur.size + 1:cur.size, cur.eat + 1 == cur.size?0:cur.eat + 1));
				}

			}
			size--;
		}
	}
}