import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * SWEA 1238 Contract
 * 
 * 메모리
 * 시간
 * 
 * 비상연락망과 연락을 시작하는 당번에 대한 정보가 주어질 떄,
 * 가장 나중에 연락을 받게 되는 사람 중
 * 번호가 가장 큰 사람을 구하는 함수를 작성하시오.
 * 
 * @제약사항
 * 연락 인원은 최대 100명 번호는 1이상 100이하이다.
 * 중간중간 비어있는 번호가 있을 수 있따.
 * 단 한명이 다수의 사람에게 연락이 가능한 경우
 * 항상 다자 간 통화를 통해 동시에 전달한다.
 * 비상연락망 정보는 사전에 공유되며 한 번 연락을 받은
 * 사람은 다시 받을 일은 없다.
 * 
 * @입력
 * 10개의 테스트 케이스가 주어진다.
 * 각 테스트케이스의 첫 번쨰 줄에는 입력 받는
 * 데이터의 길이와 시작점이 주어진다.
 * 
 * 그 다음주에 입력 받는 데이터는
 * from , to, from, to...의 순서대로 받는다.
 * 
 * @출력
 * #부호와 함께 테스트 케이스의 번호를 출력하고,
 * 공백 문자 후 테스트 케이스에 대한 답을 출력
 * 
 * @해결방안
 * BFS로 해결한다. Queue로 구현을 하고
 * Queue의 크기가 0일때 가장 큰 값을 출력.
 * 인접리스트로 BFS 구현
 * 
 * # 전화를 가장 늦게 받는 사랑 중! 가장 큰 번호
 * 
 */

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int t = 1; t <= 10; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());

			List<Integer>[] list = new List[101];
			boolean[] visited = new boolean[101];
			for (int i = 0; i < list.length; i++) {
				list[i] = new ArrayList<>();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N / 2; i++) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());

				list[from].add(to);
//				System.out.println(from + "\t->\t" + to);
			}

			// BFS
			Queue<Integer> q = new ArrayDeque<>();
			q.offer(s);

			List<Integer> last = new ArrayList<>();;
			int size = q.size();

			int max = Integer.MIN_VALUE;
			while (!q.isEmpty()) {
				int cur = q.poll();
				
				for (int i : list[cur]) {
					if (visited[i])
						continue;
					visited[i] = true;
					q.offer(i);
					last.add(i);
				}
				size--;
				if (size == 0) {
					if(q.isEmpty())
						break;
					size = q.size();
					max = Integer.MIN_VALUE;
					for(int i: last)
						if(i>max)
							max = i;
					last = new ArrayList<>();
				}
			}

			System.out.println("#" + t + " " + max);
		}
	}

}
