import java.util.Scanner;

/*
 * 백준 2961 도영이가 만든 맛있는 음식
 * 
 * 도영이의 앞에는 재료가 N개 있다.
 * 도영이는 각 재료의 신맛 S와 쓴맛 B를 알고 있다.
 * 여러 재료를 이용해서 요리할 때, 그 음식의 신맛은 
 * 사룡한 재료의 신맛의 곱이고, 쓴맛은 합이다.
 * 
 * 재료를 적절히 섞어서 요리의 신맛과 쓴맛의 차이를 작게 만들려고 한다.
 * 또 물을 요리라고 할 수는 없기 때문에, 적어도 재료 하나는 사용한다.
 * 
 * @입력
 * 첫째 줄에 재료의 개수 N (1<= N <=10)
 * 다음 줄 부터 N개 줄에는 재료의 신맛과 슨맛이 공백으로 구분
 * 신맛과 쓴맛은 모두 1000000000보다 작은 양의 정수이다.
 * 
 * @출력
 * 첫째 줄에 신맛과 쓴맛의 차이가 가장 작은 요리의 차이를 출력
 * 
 * @해결방안
 * static int[] 신맛과 쓴맛을 갖고(배열 두개)
 * 
 * 신맛과 쓴맛의 줄을 계속 받으면서 조합으로 값을 결정 후
 * 가장 작은 것을 찾기.
 * 1번을 넣고 2번을 넣나, 2번을 넣고 1번을 넣다 같다.
 * 
 * 부분배열로!
 * 
 * 신맛은 곱! 쓴맛은 합!
 * 
 */

public class Main {

	static int[] sin, sson;
	static int[] pSin, pSson;
	static int N;
	static boolean isSelected[];
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		N = s.nextInt();

		isSelected = new boolean[N];
		pSin = new int[N];
		pSson = new int[N];
		sin = new int[N];
		sson = new int[N];

		for (int i = 0; i < N; i++) {
			sin[i] = s.nextInt();
			sson[i] = s.nextInt();
		}

		
			ps(0);
		System.out.println(min);

	}

	public static void ps(int idx) {
		if (idx == N) { // 기저 조건
			
//			for(int i=0; i<N; i++)
//				System.out.print(isSelected[i]?"■":"□");
//			System.out.println();
			
			int cnt = 0;
			for(int i=0; i<N; i++) {
				if(!isSelected[i]) {
					cnt++;
				}
			}
			if(cnt == N)
				return;
			
			
			int tmp_sin = 1;
			int tmp_sson = 0;
			for (int i = 0; i < N; i++) {
				if (isSelected[i]) {
					tmp_sin *= sin[i];
					tmp_sson += sson[i];
				}
			}
			int tmp = Math.abs(tmp_sin - tmp_sson);

			if (tmp < min) {
				min = tmp;
			}
			return;
		} 
			
		isSelected[idx] = true;
		ps(idx + 1);
		isSelected[idx] = false;
		ps(idx + 1);
			

	}
}