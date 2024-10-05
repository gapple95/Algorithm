/*
 * N과M5
 * 
 * 메모리 
 * 시간 
 * 
 * # 문제
 * N개의 자연수와 자연수 M이 주어질때 아래 조건을 만족하는 길이가 M인 수열
 * N개의 자연수는 모두 다른 수이다.
 * 
 * # 입력
 * 첫째 줄에 N과 M이 주어진다.
 * 1<= M <= N <= 8
 * 둘째 줄에 N개의 수가 주어진다. 입력으로 주어지는 수는 10,000보다 작거나 같은 수이다.
 * 
 * # 출력
 * 한줄에 하나씩 수열 출력
 * 중복되는 수열은 출력 X
 * 각 수열은 공백으로 구분
 * 수열은 사전순
 * 
 * # 풀이
 * 이번에는 N개의 크기의 배열으 만들어서 사용
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static int N,M,picked[], numbers[];
	static boolean[] isSelected;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sb = new StringBuilder();
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		picked = new int[M];
		
		isSelected = new boolean[N];
		numbers = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(numbers);
		
		perm(0);
		
		System.out.println(sb);
	}

	public static void perm(int idx) {
		if(idx == M) {
			for(int i: picked)
				sb.append(i).append(" ");
			sb.append("\n");
			
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(isSelected[i])
				continue;
			isSelected[i] = true;
			picked[idx] = numbers[i];
			perm(idx+1);
			isSelected[i] = false;
		}
	}
}