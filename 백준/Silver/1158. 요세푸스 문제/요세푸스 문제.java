import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/* 
 * 백준 1158 요세푸스 문제
 * 
 * 1번부터 N번까지 N명의 사람이 원을 이루면서 앉아있고, 양의 정수 K(<=N)가 주어진다.
 * 이제 순서대로 K번째 사람을 제거한다. 한사람이 제거되면 남은 사람들로 이루어진 원을 따라
 * 이 과정을 계속해 나간다.
 * 이과정은 N명의 사람이 모두 제거될 때까지 계속된다. 원에서 사람들이 제거되는 순서를(N,K)-요세푸스 순열
 * 이라고 한다. 예를 들어(7,3)-요세푸스 순열은 <3,6,2,7,5,1,4>이다
 * 
 * N과 K가 주어지면(N,K)-요세푸스 순열을 구하는 프로그램을 작성하시오.
 * 
 * @입력
 * 첫째 줄에 N과 K가 빈 칸을 사이에 두고 순서대로 주어진다(1<=K<=N<=5000)
 * 
 * @출력
 * 예제와 같이 요세푸스 순열을 출력한다.
 * 
 * @해결방안
 * %를 활용하여 배열을 순회하며 배열의 크기가 사라질 때까지 출력
 * boolean[] 배열 활용
 * 
 */

public class Main {

	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] arr = new int[N];
		int[] answer = new int[N];
		boolean[] isSelected = new boolean[N];
		int count = 0;
		int pick_cnt = 1;
		int idx = 0;
		
		for (int i = 0; i < N; i++) {
			arr[i] = i+1;
		}
		
		while(count < N) {
			idx = idx % N;
			if(isSelected[idx]) {
				idx++;
				continue;
			}
			if(pick_cnt%K==0) { // K번째마다 선택
				answer[count++] = arr[idx];
				isSelected[idx] = true;
			}
			idx++;
			pick_cnt++;
		}
		System.out.print("<");
		for (int i = 0; i < N; i++) {
			if(i == N-1) System.out.print(answer[i]);
			else System.out.print(answer[i] + ", ");
		}
		System.out.print(">");
	}

}