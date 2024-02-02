import java.util.Scanner;

/*
 * Spot mart
 * 
 * 퇴근길에 스팟마트에 들러 과자 두 봉지를 사서 양 손에 하나씩
 * 들고 가려고 한다.
 * 
 * 스팟마트에는 N개의 과자 봉지가 있으며, 각 과자 봉지는 a_i그램의
 * 무게를 가진다.
 * 배가 많이 고픈 한빈이는 최대한 양이 많은(무게가 많이 나가는) 과자
 * 봉지를 고르고 싶으나, 과자 두 봉지의 무게가 M그램을 초과하면
 * 무거워서 과자를 들고 다닐 수 없다.
 * 들고 다닐 수 있는 최대 무게 합을 출력하라.
 * 
 * @입력
 * 테스트케이스 TC가 주어진다.
 * 각 테스트 케이스는 자연수 N(2<=N<=1000, 2<=M<=2000000)
 * 이후 N개의 줄에 각 과자봉지의 무게가 주어진다(1<=a<=1000000)
 * 
 * 
 * @출력
 * "#t" 띄고 한빈이가 들고 갈 수 있는 과자 봉지의 무게 합 최대를 출려갛라.
 * 만약 들고갈 방법이 없는경우 -1을 출력한다.
 * 
 * 
 * @문제해결
 * 조합을 구하고 거기서 가장 큰 max를 구하자.
 * 조합의 합이 M그램을 넘기면 건너뛰자.
 * 무조건 두손 다 들어야한다.
 * 
 */

public class Solution {

	static int N,M;
	static int[] pick, arr;
	static int max;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		int T = s.nextInt();
		for(int t=1; t<=T; t++) {
			N = s.nextInt();
			M = s.nextInt();
			
			pick = new int[2];
			arr = new int[N];
			
			max = -1;
			for(int i=0; i<N; i++) {
				arr[i] = s.nextInt();
			}
			
			comb(0,0,0);
			System.out.printf("#%d %d\n",t,max);
		}
	}

	public static void comb(int idx, int start, int sum) {
		if(idx==2) {
			if(sum>max  && sum<=M)
				max = sum;
			return;
		} else {
			for(int i=start; i<N; i++) {
				pick[idx] = arr[i];
				comb(idx+1, i+1, sum+arr[i]);
			}
		}
	}
}