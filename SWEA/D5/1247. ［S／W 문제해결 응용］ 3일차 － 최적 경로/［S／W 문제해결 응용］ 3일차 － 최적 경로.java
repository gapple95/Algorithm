import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/* 
 * SWEA 1247 최적경로
 * 
 * 김대리는 회사에서 출발하여 냉장고 배달을 위해 N명의 고객을 방문하고
 * 자신의 집에 돌아가려한다.
 * 회사와 집의 위치, 그리고 각 고객의 위치는 이차원 정수 좌표(x,y)로
 * 주어지고 (0<=x<=100, 0<=y<=100)
 * 
 * 두위치 x1,y1 / x2,y2의 사이의 거리는 |x1-x1| + |y1-y2|으로 계산된다.
 * 회사에서 출발하여 N명의 고객을 모두 방문하고 집으로 돌아오는 경로 중 가장
 * 짧은 것을 찾으려 한다.
 * 
 * 회사와 집의 좌표가 주어지고, 2명에서 10명 사이의 고객 좌표가 주어질 때,
 * 회사에서 출발해서 이들을 모두 방문하고 집에 돌아가는 경로중 총 이동거리가
 * 가장 짧은 경로를 찾는 프로그램을 작성하라.
 * 
 * 이동거리만 밝히면 된다.
 * 
 * 완탐으로 찾아도 좋다.
 * 
 * @제약사항
 * 고객의 수 N은 2<=N<=10이다.
 * 회사의 좌표, 집의 좌표를 포함한 모든 N+2개의 좌표는 서로 다른 위치에 있으며 좌표의 값은 0이상 100 이하의 정수로 이루어진다.
 *
 * @입력
 * 가장 첫줄은 전체 테스트케이스의 수이다.
 * 두 줄에 테스트 케이스 하나씩이 차례로 주어진다.
 * 
 * 각 테스트케이스의 첫째 줄에는 고객의 수 N이 주어진다.
 * 둘째줄에는 회사의 좌표, 집의 좌표, N명의 고객의 좌표가 차례로 나열된다.
 * 좌표는 (x,y)쌍으로 구성되는데 입력에서는 x와 y가 공백으로 구분되어 제공된다.
 * 
 * @출력
 * 총 10줄에 10개의 테스트 케이스 각각에 대한 답을 출력
 * "#t"로 시작하고 공백을 하나 둔 다음 최단 경로의 이동거리를 기록한다.
 * 
 * @해결방안
 * N+2개의 배열을 만든 후 완탐 => 순열
 * 회사를 기점으로 모든 지점들을 완탐, 마지막에 집까지의 거리를 계산해서
 * 제일 작은 값을 반환
 * 
 * 
 */

public class Solution {

	static int[][] list;
	static boolean[] visited;
	static int N;
	static int[] home = new int[2];
	static int[] company = new int[2];
	static int min;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int T = Integer.parseInt(br.readLine());

		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			list = new int[N][2];
			visited= new boolean[N];
			min = Integer.MAX_VALUE;
			
			st = new StringTokenizer(br.readLine());
			company[0] = Integer.parseInt(st.nextToken());
			company[1] = Integer.parseInt(st.nextToken());
			
			home[0] = Integer.parseInt(st.nextToken());
			home[1] = Integer.parseInt(st.nextToken());
			
			for(int i=0; i<N; i++) {
				list[i][0] = Integer.parseInt(st.nextToken());
				list[i][1] = Integer.parseInt(st.nextToken());
			}
			
//			for (int i = 0; i < N; i++) {
//				System.out.printf("%d \t %d", list[i][0],list[i][1]);
//				System.out.println();
//			}
			
			perm(0,0,company);
			System.out.print("#"+t + " ");
			System.out.println(min);
		}
		
	}
	
	public static void perm(int idx, int sum, int[] pre) {
		if(idx==N) {
			min = Math.min(min, cal(pre, home)+sum);
			return;
		}
		for(int i=0; i<N; i++) {
			if(visited[i]) continue;
			visited[i] = true;
			//로직
			perm(idx+1, sum+cal(pre,list[i]), list[i]);
			visited[i] = false;
		}
	}
	
	public static int cal(int[] a, int[] b) {
		return Math.abs(a[0]-b[0]) + Math.abs(a[1]-b[1]);
	}
	
	
}