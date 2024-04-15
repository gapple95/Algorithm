/*
 * 백준 16401 과자 나눠주기
 * 
 * 메모리 
 * 시간 
 * 
 * M명의 조카에게 동등하게 막대과자의 길이를 제공하자.
 * 막대과자는 여러조각으로 나눠질수 있지만 과자를 하나로 합칠 순 없다.
 * 모든 조카에게 같은 길이의 막대과자를 주면서 최대의 길이가 되어야한다.
 * 만약 최대길이만큼 못 나눠준다면 0을 출력
 * 
 * @입력
 * 조카의 수 M(1~1000000), 과자의 수(1~1000000)이 주어진다.
 * 둘째줄에는 과자 N개의 길이 L1~Ln이 공백으로 주어진다.
 * 과자의 길이는 1~1000000000이다.
 * 
 * @해결방안
 * 이분탐색 문제
 * 과자의 최소 길이는 1이다.
 * 가장 큰 과자의 길이는 배열의 가장 끝 대충 MAX라고 치자.
 * 이 둘의 가운데 MID값이 있을 때, 그것들을 모든 배열에 나누고
 * 몫들을 다 더하면 최대 몇명의 조카들에게 나눠줄수 있는지 값이 나온다.
 * 이 떄,
 * 1. 몫들이 M보다 크면?
 * 		MID값에 1더하고 그것을 LEFT로 사용하자.
 * 		=> MID값이 더 커질수록 과자의 크기는 줄어들기 때문!
 * 2. 몫들이 M보다 작으면?
 * 		MID값에 1빼고 그것을 RIGHT로 사용하자.
 * 		=> 마찬가지로 과자의 크기를 늘려서 맞춰주는 것이다.
 * 
 * 이를 반복하다보면(LEFT >RIGHT를 넘어갈 떄까지)
 * M과 몫(최대 몇명의 조카들에게 나눠줄수있는지)이
 * 같아지는 순간의 MID값이 과자의 최대 크기가 된다.
 * 이 떄 반복이 끝났는데도 다르면 나눠줄수 없다고 판별해서 0
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());
		int[] snacks = new int[N];
		int max = -1;
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i <N; i++) {
			snacks[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, snacks[i]);
		}
		
		
		int left = 1, right=max;
		int mid = (right + left) / 2; // 과자의 크기
		int cnt;
		int result = 0;
		while(left<=right) {
			mid = (right + left) / 2;
			cnt = 0;
			for (int i = 0; i < snacks.length; i++) {
				cnt += snacks[i] / mid;
			}
			
			// 만약 cnt가 M보다 크면?
			// 과자가 생각보다 잘게 나눈것, 크기를 더 늘려줘야한다.
			if(cnt >= M) {
				left = mid + 1;
				result = mid;
			} 
			// cnt가 M보다 작으면
			// 과자가 생각보다 더 길어진것, 크기를 줄여줘야한다.
			else if(cnt < M) {
				right = mid - 1;
			}
			
		}
		sb.append(result);
		System.out.println(sb);
	}

}