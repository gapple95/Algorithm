import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


/*
 * SWEA 4012 요리사
 * 
 * 메모리
 * 시간
 * 
 * 두 명의 손님에게 음식을 제공하려고 한다.
 * 두 명의 손님은 식성이 비슷하기 때문에, 최대한 비슷한 맛의 음식을 만들어 내야한다.
 * N개의 식재료가 있다.
 * 
 * 식재료들을 각각 N/2개씩 나누어 두 개의 요리를 하려고 한다.(N은 짝수이다.)
 * 이때, 각각의 음식을 A음식, B음식이라고 하자.
 * 비슷한 맛의 음식을 만들기 위해서는 A음식과 B음식의 맛의 차이가 최소가 되도록 재료를 배분해야한다.
 * 음식의 맛은 음식을 구성하는 식재료들의 조합에 따라 다르게 된다.
 * 
 * 식재료 i는 식재료j와 같이 요리하게 되면 궁합이 잘 맞아 시너지 S_ij가 발생한다.(1<=i<=N, 1<=j<=N, i!=j)
 * 각 음식의 맛은 음식을 구성하는 식재료들로부터 발생하는 시너지 S_ij들의 합이다.
 * 시너지 S_ij가 주어질때 두 음식의 간의 맛의 차이가 최소가 되는 경우를 찾고 그 최솟값을
 * 출력하는 프로그램을 작성하라.
 * 
 * @제약사항
 * 식재료의 수는 4<= N <= 16이하의 짝수이다.
 * 시너지 Sij는 1<= S <= 20,000 정수
 * i와 j가 같은 경우는 없다. 입력에서는 0으로 주어진다.
 * 
 * @입력
 * 총테스트 케이스의 개수 T
 * T번째 테스트 케이스의 N
 * N*N 크기의 맛 테이블이 주어진다.
 * 
 * @출력
 * #T 최솟값 출력
 * 
 * @해결방안
 * 절반을 구하는 조합을 구하고, 그 나머지들도 바로 계산 => 결국 nC_n/2 인셈..
 * 조합으로 나오는 배열의 음식값을 결정하는 함수 필요
 * 
 */

public class Solution {

	static int[][] S;
	static int[] picked;
	static boolean[] check;
	static int N, min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			min = Integer.MAX_VALUE;

			N = Integer.parseInt(br.readLine());

			picked = new int[N / 2];
			S = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					S[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			comb(0, 0);

			System.out.println("#" + t + " " + min);
		}
	}

	public static void comb(int idx, int start) {
		if (idx == N / 2) {
			check = new boolean[N];
			for(int i=0; i<N/2; i++) {
				check[picked[i]] = true;
			}
			int[] unPicked = new int[N/2];

			int cnt = 0;
			for(int i=0; i<N; i++) {
				if(!check[i])
					unPicked[cnt++] = i;
			}
			
//			System.out.println("picked : " + Arrays.toString(picked));
//			System.out.println("unPicked : " + Arrays.toString(unPicked));
//			System.out.println();
			
			min = Math.min(min, Math.abs(cal(picked)-cal(unPicked)));
			return;
		}
		for (int i = start; i < N; i++) {
			picked[idx] = i;
			comb(idx + 1, i + 1);
		}
	}

	/**
	 * 
	 * @param a 구하고자하는 음식의 인덱스가 들어있는 배열
	 * @return 해당 재료들의 시너지 합
	 */
	public static int cal(int[] a) {
		int sum = 0;
		int sum_unP = 0;
		
//		System.out.println(Arrays.toString(a));
		
		for (int i = 0; i < a.length; i++) {
			for (int j = 0; j < a.length; j++) {
				sum += S[a[i]][a[j]];
			}
		}
		
//		System.out.println(sum);
		
		return Math.abs(sum - sum_unP);
	}
}
