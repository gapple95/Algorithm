import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int[][] dp = new int[10001][4];

        // 초기값 설정 (dp[0]은 필요 없음)
        dp[1][1] = 1;
        dp[2][1] = 1; dp[2][2] = 1;
        dp[3][1] = 1; dp[3][2] = 1; dp[3][3] = 1;

        // DP 테이블 채우기
        for (int i = 4; i <= 10000; i++) {
            dp[i][1] = dp[i - 1][1]; // 1만 사용하는 경우
            dp[i][2] = dp[i - 2][1] + dp[i - 2][2]; // 2를 포함하는 경우
            dp[i][3] = dp[i - 3][1] + dp[i - 3][2] + dp[i - 3][3]; // 3을 포함하는 경우
        }

        int K = Integer.parseInt(br.readLine());

        for (int k = 0; k < K; k++) {
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n][1] + dp[n][2] + dp[n][3]).append("\n"); // dp[n][0] 제거
        }

        System.out.print(sb);
        br.close();
    }
}
