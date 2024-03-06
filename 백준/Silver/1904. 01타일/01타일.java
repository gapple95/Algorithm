import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 백준 1904 01타일
 * 
 * 메모리 
 * 시간 
 * 
 * 각각 0또는 1일 쓰여있는 낱장의 타일들이 있다.
 * 어느날 타일을 0이 쓰여진 낱장의 타일들을 붙여서 한 쌍으로 이루어진
 * 00타일들을 만들었따. 결국 현재 1하나만으로 이루어진 타일 또는 0타일을
 * 두 개 붙인 한 쌍의 00타일들만이 남게 되었다.
 * 
 * 그러므로 타일로 더 이상 크기가 N인 모든 2진 수열을 만들 수 없게 되었다.
 * 예를들어, N=1일 때 1만 만들 수 있고, N=2일 때는 00,11을 만들 수 있다.
 * (01, 10은 만들 수 없게 되었다.) 또한 N=4일 때는 0011, 0000, 1001,
 * 1100, 1111 등 총 5개의 2진 수열을 만들 수 있다.
 * 
 * 우리 목표는 N이 주어졌을때 만들 수 있는 모든 가짓수를 세는것이다.
 * 단 타일들은 무한히 많은 것으로 가정하자.
 * 
 * @입력
 * 첫 번째 줄에 자연수 N이 주어진다.(1~1,000,000)
 * 
 * @출력
 * 첫 번째 줄에 만들 수 있는 길이가 N인 모든 2진 수열의 개수를 15746으로
 * 나눈 나머지를 출력한다.
 * 
 * @해결방안
 * N=1 -> 1
 * N=2 -> 2
 * N=3 -> 3
 * N=4 -> 5
 * N=5 -> 8
 * N=6 -> 13
 * ... 피보나치 수열
 * 
 * 모률러 연산은 교환법칙이 성립한다.
 * 
 * 
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		int[] dp = new int[1000001];
		
		dp[1] = 1;
		dp[2] = 2;
		for (int i = 3; i <= n; i++) {
			dp[i] = (dp[i-1] + dp[i-2]) % 15746;
		}
		System.out.println(dp[n]);
	}
	
}