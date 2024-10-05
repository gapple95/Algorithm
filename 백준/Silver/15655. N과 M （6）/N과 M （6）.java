/*
 * N과M6
 * 
 * 메모리 
 * 시간 
 * 
 * # 문제
 * N개의 자연수와 M이 주어질때 조건을 만족하는 길이가 M인 수열을 모두 구하시오
 * * N개의 자연수 중에서 M개를 고른 수열
 * * 고른 수열은 오름차순이어야한다.
 * 
 * # 입력
 * 첫째 줄에 N과 M이 주어진다.
 * 1<= M <= N <= 8
 * 둘쨰 줄에 N개의 수가 주어진다. 입력은로 주어지는 수는 10,000보다 작거나 같은 자연수
 * 
 * # 출력
 * 한 줄에 하나씩 문제의 조건을 만조갛느 수열을 출력
 * 중복되는 수열을 여러번 출력하면 안됨
 * 각 수열은 공백으로 구분
 * 
 * 수열은 사전순으로 증가하는 순서로 출력
 * 
 * # 생각
 * 수열에 순서가 필요해졌으므로, 조합 문제이다.
 * 마찬가지로  nubers 배열을 갖고 풀면 될듯
 * 
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M,numbers[],picked[];
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		numbers = new int[N];
		picked = new int[M];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(numbers);
		
		comb(0,0);
		
		System.out.println(sb);
	}
	
	public static void comb(int idx, int start) {
		if(idx == M) {
			for(int i: picked)
				sb.append(i).append(" ");
			sb.append("\n");
			return;
		}
		
		for(int i=start; i<N; i++) {
			picked[idx] = numbers[i];
			comb(idx+1, i+1);
		}
	}

}