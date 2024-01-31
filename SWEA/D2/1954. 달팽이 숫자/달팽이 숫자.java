/*
 * SWEA 1954 달팽이숫자
 * 
 * 벽을 만나면 방향을 꺽기
 * 
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int n;
	static int[][] arr;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int T = Integer.parseInt(st.nextToken());

		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());
			arr = new int[n][n];
            
            if(n==1) {
                System.out.printf("#%d\n1\n",t);
                continue;
            }
                

			int cnt = 2;
			int x = 0;
			int y = 0;
			int d = 0;
			arr[y][x] = 1; // 첫행 넣어주기
			while(true) {
				// 범위를 벗어나면 방향 바꾸기
				if(x+dx[d] >= n || x+dx[d] < 0 || y+dy[d] >= n || y+dy[d] < 0) {
					d++;
					d = d % 4;
					continue;
				} else if(arr[y+dy[d]][x+dx[d]] != 0) { // 만약 가고자하는데에 이미 있다면 방향 바꾸기
					d++;
					d = d % 4;
					continue;
				}
				x += dx[d];
				y += dy[d];
				
				arr[y][x] = cnt;
				cnt++;
				if(cnt> n*n)
					break;
			}
			System.out.printf("#%d\n", t);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
		}

	}
}