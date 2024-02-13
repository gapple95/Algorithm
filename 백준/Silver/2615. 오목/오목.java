import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 2615 오목
 * 
 * 19 * 19 판에 오목에 누가 이겼는지 알려주는 프로그램
 * 입력으로 바둑판의 어떤 상태가 주어졌을 때, 검은색이 이겼는지, 흰색이 이겼는지
 * 또는 아직 승부가 결정되지 않았는지를 판단하는 프로그램.
 * 동시에 이기거나 두군데 이상에서 동시에 이기는 경우는 입력 X
 * 
 * @입력
 * 19줄에 각 줄마다 19개의 숫자로 표현, 검은 바둑알은 1, 흰 바둑알은2, 알이 없는
 * 자리는 0, 한칸씩 띄어서 표시
 * 
 * @출력
 * 검은색이 이겼다면 1, 흰색이 이겼다면 2, 아직 승부가 결정되지 않았다면 0 출력
 * 다음 줄에는 검은색 또는 흰색이 이겼을 경우 연속된 다섯 개의 바둑알 중에서
 * 가장 왼쪽에 있는 바둑알("연속된 다섯 개의 바둑알이 세로로 놓인 경우, 그중 가장 
 * 위에 있는 것)의 가로줄 번호와, 세로줄 번호를 순서대로 출력한다.
 * 
 * @해결방안
 * 왼쪽 위부터 차례대로 알을 확인하고, 알의 왼쪽위, 위, 왼쪽, 오른쪽위에 같은 돌이 없고,
 * 아래로 4개를 만족하는 돌을 찾아 출력
 * 
 */

public class Main {
	
	static int[] dx = {-1,-1,0,-1};
	static int[] dy = {0,-1,-1,1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int[][] map = new int[21][21];
		for (int i = 1; i <= 19; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= 19; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for (int i = 1; i <= 19; i++) {
			for (int j = 1; j <= 19; j++) {
				if(map[j][i] != 0) {
					for(int d=0; d<4; d++) {
						// 범위를 벗어난다면
						if(j+dy[d] < 0 || j+dy[d] >20 || i+dx[d] < 0 || i+dx[d] > 20)
							continue;
						// 만약 왼쪽 왼쪽위 위 오른쪽위가 같다면
						if(map[j][i] == map[j+dy[d]][i+dx[d]]) {
							continue;
						} else {
							int cnt = 0;
							// 한방향으로 숫자 새보기
							while(true) {
								// 범위를 벗어난다면
								if(cnt>5)
									break;
								if(j-(dy[d] * cnt) < 0 || j-(dy[d] * cnt) > 20 || i-(dx[d] * cnt) < 0 || i-(dx[d] * cnt) > 20)
									break;
								if(map[j][i] == map[j - (dy[d] * cnt)][i - (dx[d] * cnt)])
									cnt++;
								else
									break;
							}
							if(cnt==5) {
								System.out.println(map[j][i]);
								System.out.println((j) + " " + (i));
								System.exit(0);
							}
								
						}
					} // d end;
				} // if end;
			} // j end;
		} // i end;
		System.out.println(0);
	}
}