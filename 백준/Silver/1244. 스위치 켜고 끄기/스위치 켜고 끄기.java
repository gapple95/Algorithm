/*
 * 1부터 연속적으로 번호가 붙어있는 스위치들이 있다.
 * 스위치는 켜져 있거나 꺼져있는 상태이다.
 * '1'은 스위치가 켜져있음, '0'은 꺼져있음이다.
 * 
 * 학생 몇명을 뽑아 학생들에게 1 이상이고 스위치 개수 이하인
 * 자연수를 하나씩 나누어주었다. 학생들은 자신의 성별과 받은 수에 
 * 따라 아래와 같은 방식으로 스위치를 조작하게된다.
 * 
 * 1. 남학생은 스위치 번호가 자기가 받은 수의 배수이면,
 * 그 스위치의 상태를 바꾼다.(toggle)
 * ex) 남학생이 3을 받았다면, 3번 6번 스위치극 토글
 *  
 * 2. 여학생은 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로
 *    좌우가 대칭이면서 가장 많은 스위치를 포함 하는 구간을
 *    찾아서, 그 구간에 속한 스위치를 모두 토글. 이 때 구간에
 *    속한 스위치 개수는 항상 홀수가 된다.
 * 입력으로 스위치들의 처음 상태가 주어지고, 각 학생의 성별과 받은
 * 수가 주어진다.
 * 학생들은 입력되는 "순서대로" 자기의 성별과 받은 수에 따라
 * 스위치의 상태를 바꾸었을 때, 스위치들의 마지막 상태를 
 * 출력하는 프로그램을 만드시오
 * 
 * @입력
 * 첫째 줄에는 스위치 개수가 주어진다. 개수는 100이하의 양의 정수
 * 두째 줄에는 각 스위치 상태가 주어진다. 켜져있으면 1, 꺼져있으면
 * 0이고 사이에 빈칸이 하나씩 있다.
 * 셋째 줄에는 학생수가 주어진다. 학생수는 100이하의 양의 정수
 * 넷째 줄부터 마지막 줄까지 한 줄에 한 학생의 성별, 학생이 받은 수
 * 가 주어진다. 남학생은 1, 여학생은 2로 표시하고, 학생이 받은
 * 수는 스위치 개수 이하인 양의 정수이며 빈칸으로 나뉘어져있다.
 * 
 * @출력
 * 스위치의 상태를 1번부터 마지막 스위치까지 "한줄에 20개씩 출력"
 * 스위치 사이는 빈칸
 * 
 * 입력되는 순서이기 때문에 1,2(남자,여자)로 나뉘어 arr을 
 * 바로 적용한다.
 * 남자는 idx * n(n<=스위치갯수) 반복문으로 해결
 * 여자는 dx={-1,1} 에다가 반복문으로 dx[d] * n(n<=스위치갯수)
 * 조건문으로 그 둘이 다를때 나오기
 * 출력은 스위치갯수만큼 반복문하다가 20count에 \n
 * 
 */

import java.util.Scanner;

public class Main {
	public static int[] dx= {-1,1};

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		
		int n = s.nextInt(); // 스위치수
		
		int[] arr = new int[n+1];
		
		for (int i = 1; i <= n; i++) { // 스위치 상태
			arr[i] = s.nextInt();
		}
		
		int student = s.nextInt(); // 사람수
		
		
		int man;
		int num;
		for (int i = 0; i < student; i++) {
			man = s.nextInt();
			num = s.nextInt();
			if(man == 1) { // 남자 일 때
				// 문자열 tmp[1]을 정수화한 후 배열이기 때문에 1
				for (int j = num; j <= n; j+=num) {
					arr[j] = toggle(arr[j]);
				}
			} else if(man == 2) { // 여자 일때
				arr[num] = toggle(arr[num]);
				for (int j = 1; j <= n; j++) {
					// 검색 범위를 벗어 난다면?
					if((num + j*dx[0]<1 || num + j*dx[0]>n) || (num + j*dx[1]<1 || num + j*dx[1]>n))
						break;
					// 범위를 넓혀가며 토글
					if(arr[num + j*dx[0]] == arr[num + j*dx[1]]) {
						arr[num + j*dx[0]] = toggle(arr[num + j*dx[0]]);
						arr[num + j*dx[1]] = toggle(arr[num + j*dx[1]]);
					} else {
						break;
					}
				}
			}
		}
		
		//출력
		for (int i = 1; i <= n; i++) {
			System.out.print(arr[i] + " ");
			if(i%20==0) {
				System.out.println();
			}
		}
	}
	
	public static int toggle(int n) {
		if(n == 1)
			return 0;
		else if(n == 0)
			return 1;
		return 0;
	}

}