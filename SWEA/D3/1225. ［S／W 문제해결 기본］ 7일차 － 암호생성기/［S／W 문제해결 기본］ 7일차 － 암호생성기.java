import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 
 * SWEA 1225 암호생성기
 * 
 * 다음 주어진 조건에 따라 n개의 수를 처리하면 8자리의 암호를
 * 생성할 수 있다.
 * 
 * 8개의 숫자를 입력받고,
 * 첫 번째 숫자를 1감혼 뒤, 맨뒤로
 * 다음 첫 번째 수는 2 감소한뒤
 * 그 다음 첫 번째는 3을 감소하고 뒤
 * 그 다음 수는 4
 * 그 다음수는 5
 * 이와같은 작업을 한 싸이클이라고한다.
 * 숫자가 감소할때 0보다 작아지는 경우 0으로 유지,
 * 프로그램은 종료된다. 이 때 8자리 숫자값이 암호가 된다.
 * 
 * @제약 사항
 * 주어지는 각수는 integer 범위를 넘지 않는다.
 * 마지막 암호 배열은 모두 한 자리 수 구성되어 있다.
 * 
 * @입력
 * 각 테스트 케이스의 첫 줄에는 테스트 케이스의 번호
 * 그 다음 줄에는 8개의 데이터가 주어진다.
 * 
 * @출력
 * #부호와 테스트 케이스 번호를 출력하고,
 * 공백 문자 후 8자리 숫자 출력
 * 
 * @해결 방안
 * 한 싸이클 1~5를 반복하는 while(true)문을 구현
 * if으로 감소해서 0이될때 배열을 출력
 * Queue는 ArrayQueue로 구성
 * 
 */

public class Solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int t = 1; t<=10 ; t++) {
			String T = br.readLine();
			Queue<Integer> q = new ArrayDeque<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				q.offer(Integer.parseInt(st.nextToken()));				
			}
			
			
			int cnt=1;
			int tmp;
			while(true) {
				if(cnt>5)
					cnt=1;
				tmp = q.poll();
				tmp -= cnt;
				if(tmp<=0)
					tmp=0;
				q.offer(tmp);

				if(tmp==0)
					break;
				cnt++;
			}
			
			System.out.printf("#%d ", t);
			for(int i=0; i<7;i++)
				System.out.printf("%d ",q.poll());
			System.out.printf("%d\n",q.poll());
		}
		
		
	}

}