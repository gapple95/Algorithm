import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 * 
 *  * 백준 15650 N과 M(2)
 * 
 * 자연수 N과 M이 주어졌을 때, 아래 조건을 만족하는 길이가 M인
 * 수열을 모두 구하는 프로그램을 작성하시오.
 * 
 * * 1부터 N까지 자연수 중에서 중복 없이 M개를 고른 수열
 * * 고른 수열은 오름차순이어야 한다.
 * 
 * @입력
 * 첫째 줄에 자연수 N과 M이 주어진다. (1<=N<=M<=8)
 * 
 * @출력
 * 한 줄에 하나씩 문제의 조건을 만족하는 수열을 출력
 * 중복되는 수열은 여러 번 출력하면 안되며, 각 수열은
 * 공백으로 구분해서 출력
 * 
 * 수열은 사전 순으로 증가하는 순서로 출력해야한다.
 * 
 * @해결 방안
 * 조합을 구현하여 풀기.
 * def(int idx, int start);
 * 
 */

public class Main {
	
	static int[] arr;
	static int[] pick;
	static int N, M;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pick = new int[M];
        arr = new int[N];
        for(int i=0; i<N; i++)
        	arr[i] = i+1;
        comb(0, 0);
	}
	
	public static void comb(int idx, int start) {
		if(idx == M) { // M개의 조합이 꽉찼을 때.
			for(int i: pick) {
				System.out.print(i + " ");
			}
			System.out.println();
			return;
		} else { // 유도패턴
			// arr에 들어있는 모든 배열들을 한번씩 꺼내서 사용
			// 조합의 특성상 한번 사용된 숫자 그 이후는 안쓴다. start 사용
			for(int i=start; i<N; i++) {
				// 해당 자릿수를 꺼낸다. idx가 1이면 첫번째 자릿수 ..
				pick[idx] = arr[i];
				comb(idx+1, i+1);
			}
		}
	}

}