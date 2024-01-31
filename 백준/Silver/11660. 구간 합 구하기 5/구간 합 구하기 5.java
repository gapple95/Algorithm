import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 
 * 2차원 배열 N*N의 표가 주어 질때, (x1,y1) ~ (x2,y2)의 모두 더한 값을
 * 나타내는 프로그램
 * 
 * @입력
 * 첫째 줄에 표의 크기 N과 합을 구해야하는 횟수 M이 주어진다.
 * (1<=N<=1024, 1<=M<=100000)
 * 둘째 줄부터 N개의 줄에는 N개의 줄에는 표에 채워져 있는 수가 1행부터 차례대로
 * 주어진다.
 * 다음 M개의 줄에는 네 개의 정수 x1,y1,x2,y2가 주어지며,
 * (x1, y1)부터 (x2,y2)의 합을 구해 출력해야한다.
 * 표에 채워져 있는 수는 1000보다 작거나 같은 자연수이다.
 * (x1<=x2, y1<=y2)
 * 
 * @출력
 * 총 M줄에 걸쳐 (x1, y1)부터 (x2,y2)까지 합을 구해 출력한다.
 * 
 * 
 * @문제해결
 * 2차원 배열의 값은 자신의 인덱스보다 낮은 값들의 합들
 * 구현은 (x,y-1) + (x-1, y) - (x-1,y-1) + (x,y)
 * 출력에 대한 구현은
 * (x2,y2) - (x1-1,y2) - (x2, y1-1) + (x1-1,y1-1)
 * 
 */

public class Main {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] arr = new int[N + 1][N + 1];    // 합이 저장된 배열

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = arr[i - 1][j] + arr[i][j - 1] - arr[i - 1][j - 1] + Integer.parseInt(st.nextToken());
            }
        }
        int x1, y1, x2, y2;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            x1 = Integer.parseInt(st.nextToken());
            y1 = Integer.parseInt(st.nextToken());
            x2 = Integer.parseInt(st.nextToken());
            y2 = Integer.parseInt(st.nextToken());

            System.out.println(arr[x2][y2] - arr[x2][y1 - 1] - arr[x1 - 1][y2] + arr[x1 - 1][y1 - 1]);
        }
	}

}