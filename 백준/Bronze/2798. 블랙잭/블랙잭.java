import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 * 백준 2798
 * 
 * 카드의 합이 21을 넘지 않는 한도내에서 카드의 합을 최대한 크게 만드는 게임
 * 
 * N장의 카드를 모두 숫자가 보이도록 바닥에 놓는다.
 * 그런후 딜러는 숫자 M을크게 외친다.
 * 
 * 이제 플레이어는 제한된 시간 안에 N장의 카드중에서 3장의 카를 골라야한다. 블랙잭 변형 게임
 * 이기 때문에, 플레이어가 고른 카드의 합은 M을 넘지 않으면서 M과 최대한 가깝게 만들어야한다.
 * 
 * N장의 카드에 써져있는 숫자가 주어졌을 때, M을 넘지 않으면서 M에 최대한 가까운 3장의 합을 구해 출력하시오.
 * 
 * @입력
 * 첫째 줄에 카드의 개수 M(3<=N<=100)과 M(10<=M<=300,000)이 주어진다.
 * 둘째줄에는 카드에 스여있는 수가 주어지며, 이 값은 100,000을 넘지 않는 양의 정수이다.
 * "합이 M을 넘지 안흔ㄴ 카드 3장을 찾을 수 있는 입력으로 주어진다."
 * 
 * @출력
 * 첫째줄에 M을 넘지 않은면서 M에 최대한 가까운 카드 3장의 합을 출력한다.
 * 
 * @해결방안
 * nCm의 조합을 구하면서 21에 가까운 최댓값 구하기
 * 
 */

public class Main {
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int[] arr = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		int max = Integer.MIN_VALUE;
		// 3중포문
		for(int i=0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
//				if(i==j) continue;
				for (int k = j+1; k < N; k++) {
//					if(i==k && j==k) continue;
					if(arr[i]+arr[j]+arr[k] <= M)
						max = Math.max(max, arr[i]+arr[j]+arr[k]);
				}
				
			}
		}
		System.out.println(max);
	}

}