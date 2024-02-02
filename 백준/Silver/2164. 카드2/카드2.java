import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 백준 2164 카드2
 * 
 *  N장의 카드가 있다. 차례로 1~N까지의 번호가 붙어있으며,
 *  1번 카드가 제일 위에, N번카드가 제일 아래인 상태이다.
 *  
 *  같은 동작을 카드가 한 장 남을 때까지 반복하게 된다.
 *  우선, 제일 위에 있는 카드를 바닥에 버린다.
 *  그 다음, 제일 위에 있는 카드를 제일 아래에 있는 카드
 *  밑으로 옮긴다.
 *  
 *  N이 주어졌을 때 제일 마지막에 남게 되는 카드를 구해라
 *  
 *  @입력
 *  첫째 줄에 정수 N(1<=N<=500,000)
 *  
 *  @출력
 *  첫째 줄에 남게 되는 카드이 번호를 출력한다.
 *  
 *  @해결방안
 *  큐를 구현해서 de를 토글로 버리고 뒤에 넣기를 반복한다.
 * 	나중에 큐의 size가 1이되면 출력.
 * 
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());

		Queue<Integer> q = new LinkedList<>();
		for (int i = 1; i <= n; i++) {
			q.offer(i);
		}
		int cnt = 1;
		int tmp;
		while(!(q.size()==1)) {
			if(cnt == 1) // 카드를 바닥에 버린다.
				q.poll();
			else { // 카드앞장을 제일 아래로 옮긴다.
				tmp = q.poll();
				q.offer(tmp);
			}
			cnt ^= 1;
		}
		System.out.println(q.poll());
	}

}