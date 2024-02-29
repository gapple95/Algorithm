import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*
 * SWEA 1767 프로세서 연결하기
 * 
 * 메모리 
 * 시간 
 * 
 * 가로세로 N개의 cell이 있을때
 * 1개의 cell에는 1개의 core혹은 1개의 전선이 올 수 있다.
 * 가장 자리에는 전원이 흐르고 있다.
 * 
 * Core와 전원을 연결하는 전선은 직선으로만 설치가 가능하며,
 * 전선은 절대로 교차해서는 안된다.
 * 초기 상태로는 아래와 같이 전선을 연결하기 전 상태의 멕시노스 정보가 주어진다.
 * => 멕시노스의 가장자리에 위치한 Core는 전원이 연결된것으로 간주한다.
 * 
 * 최대한 많은 Core에 전원을 연결하였을 경우, 전선 길이의 합을 구하고자 한다.
 * 전선길이의 합이 최소가 되는 값을 구하라.
 * 
 * @제약 사항
 * N (7~12)
 * core는 최소 1개이상 12개이하
 * 최대한 많은 Core를 연결해도, 전원이 연결되지 않는 Core가 존재할 수 있다.
 * 
 * @입력
 * 가장 첫 줄에는 테스트 케이스 t
 * 각 테스트 케이스의 첫 줄에는 N값이 주어지며, 
 * 0은 빈셀, 1은 코어이다.
 * 
 * @출력
 * #X를 찍고 한 칸 띄고, 정답을 출력한다.
 * 
 * @해결방안
 * 상하좌우 그대로 5^12승의 경우의 수를 다 완탐-> 백트래킹을 사용해야한다.
 * 
 */

public class Solution {

	static int N, max, totalCnt, min, map[][]; // 멕시노스 크기, 최대코어수, 비가장자리코어수, 최소전선길이합, 멕시노스셀정보
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static ArrayList<int[]> list; // 비가장자리 코어 리스트

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int T = Integer.parseInt(br.readLine());

		for (int t = 1; t <= T; t++) {
			sb.append("#").append(t).append(" ");
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			list = new ArrayList<>();
			max = 0;
			totalCnt = 0;
			min = Integer.MAX_VALUE;

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());

					// 비가장자리 코어 리스트에 담기
					if (i > 0 && i < N - 1 && j > 0 && j < N - 1 && map[i][j] == 1) {
						list.add(new int[] { i, j });
						totalCnt++;
					}
				}
			} // 멕시노스 셀 정보 입력 및 비가장자리 코어리스트 생성

			go(0, 0, 0);
			sb.append(min).append("\n");
		}
		System.out.println(sb);

	}
	
	static boolean isAvailable(int r, int c, int d) { // r,c 위치에서 d방향으로 전선놓기가 가능한지 체크
		int nr = r;
		int nc = c;
		while(true) {
			nr += dr[d];
			nc += dc[d];
			if(nr < 0 || nr >=N || nc<0 || nc>=N)
				return true;
			if(map[nr][nc] > 0)
				return false;
		}
	}
	
	static void go(int index, int cCnt, int lCnt) { // 현재 코어로 전선처리 시도, cCnt : 코어갯수, lCnt : 전선 길이의 합
		
		if(cCnt+(totalCnt - index) < max) // 가지치기 : 현재까지 모은 것이 나머지 잔여 코어를 연결한다고 쳐도 결국 최적해를 이길순 없으니 빠져나오기
			return;
		
		if(index == totalCnt) {
			if(max < cCnt) {
				max = cCnt;
				min = lCnt;
			} else if (max == cCnt) {
				if(min > lCnt) {
					min = lCnt;
				}
			}
			return;
		}
		
		int[] cur = list.get(index);
		int r = cur[0];
		int c = cur[1];
		
		for (int d = 0; d < 4; d++) {
			if(isAvailable(r, c, d)) {
				int len =  setStatus(r, c, d, 2); // 전선 놓기
				go(index+1, cCnt+1, lCnt+len);
				setStatus(r, c, d, 0); // 놓았던 전선 지우기
			}
		}
		
		// 전선 놓지 않기
		go(index+1, cCnt, lCnt);
	}

	static int setStatus(int r, int c, int d, int s) { // r,c 위치(코어위치)에서 d방향으로 s(2:전선, 0:빈칸)로 상태 set
		int nr = r;
		int nc = c;
		int cnt = 0;
		while (true) {
			nr += dr[d];
			nc += dc[d];
			if (nr < 0 || nr >= N || nc < 0 || nc >= N)
				break;
			map[nr][nc] = s;
			cnt++; // 처리한 빈칸의 개수
		}
		return cnt;
	}
}
