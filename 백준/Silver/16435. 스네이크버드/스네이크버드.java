import java.util.Arrays;
import java.util.Scanner;

/* 
 * 백준 1643 스네이크버드
 * 
 * 메모리
 * 시간
 * 
 * 스네이크버드의 주요 먹이는 과일이며 과일 하나를 먹으면 길이가 1만큼 늘어납니다.
 * 과일들은 지상으로부터 일정 높이를 두고 떨어져 있으며 i(1<=i<=N) 번째 과일의 높이는 h_i입니다.
 * 스네이크버드는 자신의 길이보다 작거나 같은 높이에 있는 과일들을 먹을 수 있습니다
 * 스네이크버드의 처음 길이가 L일때 과일들을 먹어 늘릴 수 있는 최대 길이를 구하세요.
 * 
 * @입력
 * 첫 번째 ㄱ줄에 과일의 개수 N(1<=N<=1000)과 스네이크버드의 초기 길이 저수 L(1<=L<=10,000)
 * 이 주어진다.
 * 두 번째 줄에는 정수 h1,...,hN(1<=h_i<=10,000)이 주어진다.
 * 
 * @출력
 * 최대 길이를 출력
 * 
 * @해결방안
 * sort 이후 for문으로 먹어가면 L을 키어간다.
 * 
 */

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int N = s.nextInt();
		int L = s.nextInt();

		
		int fruit[] = new int[N];
		for(int i=0; i<N; i++) {
			fruit[i] = s.nextInt();
		}
		
		Arrays.sort(fruit);
		
		for(int i=0; i<N; i++) {
			if(fruit[i] <= L)
				L++;
		}
		System.out.println(L);
	}

}