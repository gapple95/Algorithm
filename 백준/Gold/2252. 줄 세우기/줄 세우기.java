import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 2252 줄 세우기
 * 
 * 메모리
 * 시간
 * 
 * N명의 학생들을 키 순서대로 줄을 세우려고 한다. 각 학생의 키를 직접 재서 정렬하면
 * 간단하겠지만, 마땅한 방법이 없어서 두 학생의 키를 비교하는 방법을 사용하기로 하였다.
 * 그나마도 모든 학생들을 다 비교해 본것이 아니고, 일부 학생들의 키만을 비교해 보았다.
 * 
 * 일부 학생들의 키를 비교한 결과가 주어졌을 때, 줄을 세우는 프로그램을 작성하시오.
 * 
 * @입력
 * 첫째 줄에 N(1<=N<32,000), M(1<=M<=100,000)이 주어진다. M은 키를 비교한 회수이다.
 * 다음 M개의 줄에는 키를 비교한 두 학생의 번호 A,B가 주어진다. 이는 학생 A가 학생 B의 앞에
 * 서야 한다는 의미이다.
 * 
 * 학생들의 번호는 1번부터 N번이다.
 * 
 * @출력
 * 첫째 줄에 학생들을 앞에서부터 줄을 세운 결과를 출력한다. 답이 여러 가지인 경우에는 아무거나 출력한다.
 * 
 * @해결방안
 * 위상정렬을 활용한다..
 * 노드들을 받을 때 받는 간선들을 count하고,
 * 받는 간선이 없는 노드를 선정하여 위상정렬 알고리즘을 수행한다.
 * 선정된 정점은 간선을 없애고 출력
 * 
 */

public class Main {
	
	static class Node {
		Node next;
		int to;
		int me;
		public Node(int me, int to, Node next) {
			super();
			this.me = me;
			this.next = next;
			this.to = to;
		}
		
		public Node(int to) {
			this.to = to;
			this.me = to;
		}
		
		@Override
		public String toString() {
			return "Node [next=" + next + ", to=" + to + "]";
		}
		
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		boolean[] visited = new boolean[N+1];
		Node[] list = new Node[N+1];
		for (int i = 1; i <= N; i++) {
			list[i] = new Node(i);
		}
		
		// 진입차수 저장 배열
		int[] count = new int[N+1];
		
		int from, to;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			from = Integer.parseInt(st.nextToken());
			to = Integer.parseInt(st.nextToken());
			list[from] = new Node(from, to, list[from]);
			count[to]++;
		}
//		for (Node node : list) {
//			System.out.println(node);
//		}
//		
//		for (int i = 1; i <= N; i++) {
//			System.out.println(count[i]);
//		}

		// 0. 큐 선언
		Queue<Integer> q = new ArrayDeque<>();
		
		// 1. 큐에 진입간선이 0인 정점 넣기
		for (int i = 1; i <= N; i++) {
			if(count[i] == 0) {
				q.offer(i);
				visited[i] = true;
			}
		}
		
		int cnt = 0;
		while(!q.isEmpty()) {
			// 2. 큐에서 값 빼기
			int cur = q.poll();
			cnt++;
			
//			System.out.println(n.me + ", " + nextTo);
			System.out.print(cur + " ");
			
//			count[nextTo]--;
//			
//			for (int i = 1; i <= N; i++) {
//				if(count[i] == 0) {
//					q.offer(i);
//					count[i] = -1;
//				}
//			}
			
			// 인접 정점을 갖고와서 적용
			for (Node temp = list[cur]; temp != null; temp = temp.next ) {
				count[temp.to]--; 
			}
			
			// 0인 부분을 다시 q에 넣기
			for (int i = 1; i <= N; i++) {
				if(count[i] == 0) {
					if(visited[i]) continue;
					visited[i] = true;
					q.offer(i);
					count[i] = -1;
				}
			}
		}
		if(cnt<N) {
			System.out.println("싸이클 발생");
			System.exit(0);
		}
	}

	
}