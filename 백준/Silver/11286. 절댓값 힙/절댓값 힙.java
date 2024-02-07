import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 백준 11286 절댓값힙
 * 
 * 절댓값 힙은 다음과 같은 연산을 지원하는 자료구조이다.
 * 1. 배열에 정수 x(x!=0)를 넣는다.
 * 2. 배열에서 절댓값이 가장 작은 값을 출력하고, 그 값을 배열에서 제거한다.
 *    절댓값이 가장 작은 값이 여러개 일 때는, 가장 작은 수를 출력하고,
 *    그 값을 배열에서 제거한다.
 *    
 * 프로그램은 처음에 비어있는 배열에서 시작하게 된다.
 * 
 * @입력
 * 첫째줄에 연산의 개수N(1<=N<=100,000)이 주어진다.
 * 다음 N개의 줄에는 연산에 대한 정보를 나타내는 정수 x가 주어진다.
 * 만약 x가 0이 아니라면 배열에 x라는 값을 넣는(추가하느)연산이고, x가
 * 0이라면 배열에서 절댓값이 가장 작은 값을 출력하고 그 값을 배열에서 제거하는
 * 경우이다. 입력되는 정수는 -2^31보다 크고, 2^31보다 작다.
 * 
 * @출력
 * 입력에서 0이 주어진 회수만큼 답을 출력한다. 만약 배열이 비어 있는 경우
 * 인데 절댓값이 가장 작은 값을 출력하라고 한 경우에는 0을
 * 출력하면 된다.
 * 
 * @해결방안
 * 힙을 구현한다..? primaryQueue의 comparable을 수정
 * 
 */

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(br.readLine());
		int cmd;
		
		PriorityQueue<Integer> pq = new PriorityQueue<>((a,b)-> {
			if(Math.abs(a) == Math.abs(b))
				return a - b;
			else
				return Math.abs(a) - Math.abs(b);
		});
		
		for (int i = 0; i < N; i++) {
			cmd = Integer.parseInt(br.readLine());
			if(cmd == 0) {
				if(pq.isEmpty()) {
					System.out.println(0);
					continue;
				}
				System.out.println(pq.poll());
			} else {
				pq.add(cmd);
			}
		}
	}

}