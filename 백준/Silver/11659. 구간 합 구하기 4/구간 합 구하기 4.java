import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

/* 
 * 수 N개가 주어졌을 때, i번째 수부터 j번째 수까지 합을 구하는 프로그램을 작성하시오.
 * 
 * @입력
 * 첫째 줄에 수의 개수 N과 합을 구해야하는 횟수 M이 주어진다.
 * 둘째 줄에는 N개의 수가 주어진다. N<=1000 자연수
 * 셋째 줄부터 M개의 줄에는 합을 구해야하는 구간 i와 j가 주어진다.
 * 
 * @출력
 * 총 M개의 줄에 입력으로 주어진 i번째 수부터 j번째 수까지 합을 출력한다.
 * 
 * for문을 이용한 2중 for문으로 접근 => Fail 시간초과
 * 메모라이제이션
 * 미리 배열에 연속된 합을 저장하면서, 필요하면 그때 꺼내서 쓸수있게
 * i,j까지라면 해당 배열에서 i와 j의 값을 갖고와 빼기만 하면 끝!
 * 
 */

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner s = new Scanner(System.in);
		
		int N = s.nextInt();
		int M = s.nextInt();
		
		int[] arr = new int[N+1];	// 입력값을 저장할 배열
		int[] arr2 = new int[N+1];	// 연속된 합을 저장할 배열
		
		int tmp;
		for (int i = 1; i <= N; i++) {
			tmp = s.nextInt();
			arr[i] = tmp;
			arr2[i] = tmp + arr2[i-1];	
		}
		
		for (int i = 0; i < M; i++) {
			int a = s.nextInt();
			int b = s.nextInt();
			
			System.out.println(arr2[b] - arr2[a-1]);
		}
	}

}