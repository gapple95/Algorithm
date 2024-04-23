import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.TreeMap;

/*
 * 백준 21939 문제 추천 시스템 Version1
 * 
 * 메모리 
 * 시간 
 * 
 * 대비 문제는 "문제번호, 난이도"로 정리 되어있다.
 * 
 * 명령어는 총 3가지
 * recommend x : x가 1인 경우 추천문제 리스트에서 가장 어려운 문제의번호 출력
 *               만약 가장 어려운 문제가 여러개라면 문제 번호가 큰것 출력
 *               x가 -1인 경우 추천 문제 리스트에서 가장 쉬운 문제의 번호를 출력
 *               만약 가장 쉬운 문제가 여러 개라면 문제번호가 작은 것으로 출력
 *               => List 정렬로 풀기.
 *               => 정렬과 우선순위큐로 풀기에는 시간초과가 무조건 난다.
 *               -> TreeMap이라는 자료구조를 사용해야한다!
 * 
 * add P L : 추천문제 리스트에 난이도가 L인 문제 번호 P를 추가(추천 문제 리스트에
 *           없는 문제 번호 P만 입력으로 주어진다.
 *           이전에 추천 문제 리스트에 있던 문제 번호가 다른 난이도로 다시 들어 올
 *           수 있다.
 * 
 * solved P : 추천 문제 리스트에서 문제 번호 P를 제거한다.(추천 문제 리스트에 있는
 *            문제 번호 P만 입력으로 주어진다.)
 * 
 * 
 * 삭제연산에 있어서 해당 데이터를 삭제하려면 O(N)의 시간 복잡도가 소요되기 때문에
 * 이를 O(logN)이 되게끔 구현해야한다. => Map 활용
 * 문제는 우선순위가 제일 큰것과 제일 작은것을 해야하기 때문에, 어쩔수 없이 TreeMap을
 * 사용해야한다. 정렬과 우선순위큐처럼 Comparable로 우선순위를 정하고 firstKey()와
 * lastKey()를 사용해야한다.
 * 
 */

public class Main {

	static class Problem implements Comparable<Problem> {
		int number, difficulty;

		public Problem(int number, int difficulty) {
			super();
			this.number = number;
			this.difficulty = difficulty;
		}

		@Override
		public int compareTo(Problem o) {
			if (difficulty > o.difficulty)
				return -1;
			else if (difficulty < o.difficulty)
				return 1;
			else {
				if (number > o.number)
					return -1;
				else if(number < o.number)
					return 1;
				else
					return 0;
			}
		}
	}

	static TreeMap<Problem, Integer> problems_key;
	static TreeMap<Integer, Problem> problems_value;
	static int N, M;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		long startTime = System.currentTimeMillis();
		
		problems_key = new TreeMap<>();
		problems_value = new TreeMap<>();

		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int P = Integer.parseInt(st.nextToken());
			int L = Integer.parseInt(st.nextToken());
			Problem tmpP = new Problem(P, L);
			problems_key.put(tmpP, P);
			problems_value.put(P, tmpP);
		}

		M = Integer.parseInt(br.readLine());
		String cmd;
		int level;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			cmd = st.nextToken();
			int x = Integer.parseInt(st.nextToken());
			switch (cmd) {
			case "recommend": {
				if (x == 1) {
					sb.append(problems_key.firstKey().number).append("\n");
				} else {
					sb.append(problems_key.lastKey().number).append("\n");
				}

				break;
			}
			case "add": {
				level = Integer.parseInt(st.nextToken());
				Problem tmpP = new Problem(x, level);
				problems_key.put(tmpP, x);
				problems_value.put(x, tmpP);
				break;
			}
			case "solved": {
				Problem findP = problems_value.get(x);
				problems_value.remove(x);
				problems_key.remove(findP);
				break;
			}
			}
		}

		System.out.println(sb);
//		System.out.println("문제 추천 시스템 실행 시간: " + (System.currentTimeMillis() - startTime) + "밀리초");
	}

}