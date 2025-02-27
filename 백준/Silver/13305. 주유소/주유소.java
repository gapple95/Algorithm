import java.util.Scanner;

/* 
 * 백준 13305 주유소
 * 
 * 메모리
 * 시간
 * 
 * 어떤 나라에 N개의 도시가 있다. 이 도시들은 일직선 도로 위에 있다.
 * 제일 왼쪽 도시에서 제일 오른쪽의 도시로 자동차를 이용하여 이동
 * 하려고 한다. 도로길이의 단위는 km를 사용한다.
 * 
 * 처음 출발할 때 자동차에는 기름이 없어서 주요소에서 기름을
 * 넣고 출발하여야한다. 기름통의 크기는 무제한이어서 얼마든지
 * 많은 기름을 넣을 수 잇다. 1km마다 1리터의 기름을 사용한다.
 * 
 * 각 도시마다 단 하나의 주유소가 있으며, 도시 마다 주유소의
 * 리터당 가격은 다를 수 있다. 가격 단위는 원을 사용한다.
 * 
 * 각 도시에 있는 주요소의 기름 가격과 각 도시를 연결하는
 * 도로의 길이를 입력으로 받아 제일 왼쪽 도시에서 제일 오른쪽
 * 도시로 이동하는 최소의 비용을 계산하는 프로그램을 작성하시오.
 * 
 * @입력
 * 첫 번째 줄에는 도시의 개수를 나타내는 정수 N(2<=N<=100,000)
 * 이 주어진다. 다음 줄에는 인접한 두 도시를 연결하는 도로의 길이가
 * 제일 왼쪽 도로부터 N-1개의 자연수로 주어진다. 다음 줄에는
 * 주요소의 리터당 가격이 제일 왼쪽 도시부터 순서대로 N개의
 * 자연수로 주어진다.
 * 전체 도로의 길이는 1이상 1,000,000,000 이하의 자연수이며
 * 리터당 가격은 1이상 1,000,000,000 이하의 자연수이다.
 * 
 * @출력
 * 제일 왼쪽도시에서 제일 오른쪽 도시로 가는 최소 비용을 출력
 * 
 * @해결방안
 * 탐욕기법을 사용하여, 현재에서 다음 도시로 가는 최소한을 구하고,
 * 다음 도시의 기름값이 더 비싸면 현재 도시에서 더 충전한다.
 * 
 * 
 */

public class Main {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int N = s.nextInt();
		long[] road = new long[N-1];
		for (int i = 0; i < road.length; i++) {
			road[i] = s.nextInt();
		}
		long[] gas = new long[N];
		for (int i = 0; i < gas.length; i++) {
			gas[i] = s.nextInt();
		}
		
		long money = 0;
		long idx = 0;
		while(idx<N-1) {
			// 14점따리
//			if(gas[idx] >= gas[idx+1]) {
//				money += gas[idx] * road[idx];
//			} else { // 지금 도시의 가스가 더 쌀때
//				money += gas[idx] * (road[idx] + road[idx+1]);
//				idx++;
//			}
//			idx++;
			
			long min_idx=idx;
			long min_gas=gas[(int)idx];
			// 다음 도시들의 주유소의 값들을 다 비교
			for(int i=(int)idx; i<N-1; i++) {
				if(min_gas <= gas[i]) {
					idx = i + 1;
					money += min_gas * road[i];
				} else {
					idx = i;
					break;
				}
			}
			
		}
//		System.out.println(idx);
		System.out.println(money);
	}

}