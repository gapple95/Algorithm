import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 17471 게리맨더링
 * 
 * 메모리 
 * 시간 
 * 
 * 백준시는 N개의 구역으로 나누어져 있고, 구역은 1번부터 N번까지 번호가 매겨져 있다.
 * 구역을 두 개의 선거구로 나눠야 하고, 각 구역은 두 선거구 중 하나에 포함되어야 한다.
 * 선거구는 구역을 적어도 하나 포함해야 하고, 한 선거구에 포함되어 있는 구역은
 * 모두 연결되어 있어야 한다. 구역 A에서 인접한 구역을 통해서 구역 B로 갈 수 있을 때,
 * 두 구역은 연결되어 있다고 한다. 중간에 통하는 인접한 구역은 0개 이상이여하고,
 * 모두 같은 선거구에 포함된 구역이어야 한다.
 * 
 * 공평하게 선거구를 나누기 위해 두 선거구에 포함된 인구의 차이를 최소로 하려고 한다.
 * 백준시의 정보가 주어 졌을 때, 인구 차이의 최솟값을 구해보자.
 * 
 * @입력
 * 첫째 줄에 구역의 개수 N이 주어진다. 둘째 줄에 구역의 인구가 1번 구역부터 N번구역까지
 * 순서대로 주어진다. 인구는 공백으로 구분되어져 있다.
 * 셋째 줄부터 N개의 줄에 각 구역과 인접한 구역의 정보가 주어진다. 각 정보의 첫 번째 정수는
 * 그 구역과 인접한 구역의 수이고, 이후 인접한 구역의 번호가 주어지다.
 * 모든 값은 정수로 구분되어져 있다.
 * 
 * 구역 A가 구역 B와 인접하면 구역 B도 구역 A와 인접하다. 인접한 구역이 없을 수도 있다.
 * 
 * @출력
 * 첫째 줄에 백준시를 두 선거구로 나누었을 때, 두 선거구의 인구 차이의 최솟값을 출력한다.
 * 두 선거구로 나눌수 없는 경우에는 -1을 출력한다.
 * 
 * @제한
 * 2<=N<=10
 * 1<=구역의 인구 수 <= 100
 * 
 * @해결방안
 * 간선의 weight가 주어지지 않았기 때문에 MST 문제는 아니다!
 * 두 개의 집합을 구해서 최솟값을 구하는 알고리즘을 만들어야한다.
 * 
 * 부분집합으로 전체 경우의 수를 구하고, 최솟값을 비교하자.
 * 
 */

public class Main {

	static class Town {

		int to;
		Town next;

		public Town(int to, Town next) {
			super();
			this.to = to;
			this.next = next;
		}

	}

	static int N;
	static int[] people;
	static boolean[] selected;
	static boolean[] visited;
	static int min = Integer.MAX_VALUE;
	static Town[] list;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		N = Integer.parseInt(br.readLine());
		list = new Town[N + 1];
		selected = new boolean[N + 1];
		visited = new boolean[N + 1];
		people = new int[N + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			int tmp = Integer.parseInt(st.nextToken());
			for (int j = 0; j < tmp; j++) {
				int b = Integer.parseInt(st.nextToken());
				list[i] = new Town(b, list[i]);
				list[b] = new Town(i, list[b]);
			}
		}

		subset(1);

		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min);
	}

	public static void subset(int idx) {
		if (idx == N + 1) {
			// 몇개의 지역구가 선택되었는지
			int cnt = 0;
			for (int i = 1; i <= N; i++) {
				if (selected[i])
					cnt++;
			}

			// 아예 모든 선택이 되거나, 안되면 스킵
			if (cnt == 0 || cnt == N) {
				return;
			}

//			// 선택된 지역구 : A
//			for (int i = 1; i <= N; i++) {
//				System.out.print((selected[i] ? people[i] : "X") + " ");
//			}
//			System.out.print("\t");
//
//			// 선택된 지역구 : B
//			for (int i = 1; i <= N; i++) {
//				System.out.print((!selected[i] ? people[i] : "X") + " ");
//			}
//			System.out.println();

			int sumA = 0;
			for (int i = 1; i <= N; i++) {
				if (selected[i]) {
					sumA = bfsA(i);
					break;
				}
			}

			// 선택된 지역구가 전부 못 돈다면.. 그대로 스킵
			if (sumA == -1)
				return;

			int sumB = 0;
			for (int i = 1; i <= N; i++) {
				if (!selected[i]) {
					sumB = bfsB(i);
					break;
				}
			}

			// 선택 안된 지역구가 전부 못 돈다면.. 그대로 스킵
			if (sumB == -1)
				return;

			min = Math.min(min, Math.abs(sumA - sumB));

//			System.out.println("Current Min : " + min);

			return;
		}
		selected[idx] = true;
		subset(idx + 1);
		selected[idx] = false;
		subset(idx + 1);
	}

	// 선택된 지역구
	public static int bfsA(int v) {
		int sum = 0;
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(v);
		System.arraycopy(selected, 0, visited, 0, selected.length);

		// 선택된 지역구 갯수
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if (visited[i])
				cnt++;
		}

		visited[v] = false;

		while (!q.isEmpty()) {
			int cur = q.poll();
			sum += people[cur];
			for (Town temp = list[cur]; temp != null; temp = temp.next) {
				if (!visited[temp.to])
					continue;
				visited[temp.to] = false;
				q.offer(temp.to);
			}
			cnt--;
		}

		if (cnt == 0)
			return sum;
		else
			return -1;
	}

	// 선택 안 된 지역구
	public static int bfsB(int v) {
		int sum = 0;

		Queue<Integer> q = new ArrayDeque<>();
		q.offer(v);
		System.arraycopy(selected, 0, visited, 0, selected.length);

		// 선택 안 된 지역구 갯수
		int cnt = 0;
		for (int i = 1; i <= N; i++) {
			if (!visited[i])
				cnt++;
		}

		visited[v] = true;

		while (!q.isEmpty()) {
			int cur = q.poll();
			sum += people[cur];
			for (Town temp = list[cur]; temp != null; temp = temp.next) {
				if (visited[temp.to])
					continue;
				visited[temp.to] = true;
				q.offer(temp.to);
			}
			cnt--;
		}

		if (cnt == 0)
			return sum;
		else
			return -1;
	}

}