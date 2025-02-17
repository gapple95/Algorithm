import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, K, P, X;

    // 0부터 9까지의 숫자를 7세그먼트로 표현한 비트마스크 배열
    static int[] num = {
        0b1111110, // 0
        0b0110000, // 1
        0b1101101, // 2
        0b1111001, // 3
        0b0110011, // 4
        0b1011011, // 5
        0b1011111, // 6
        0b1110000, // 7
        0b1111111, // 8
        0b1111011  // 9
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        // 현재 층수 X를 K자리의 문자열로 변환
        String currentFloor = String.format("%0" + K + "d", X);

        int answer = 0;

        // 1부터 N까지의 모든 층에 대해 검사
        for (int i = 1; i <= N; i++) {
            if (i == X) continue; // 현재 층수는 제외

            // i를 K자리의 문자열로 변환
            String targetFloor = String.format("%0" + K + "d", i);

            int diffCount = 0; // 반전시켜야 하는 LED 개수

            // 각 자리수별로 비교
            for (int j = 0; j < K; j++) {
                int currentDigit = currentFloor.charAt(j) - '0';
                int targetDigit = targetFloor.charAt(j) - '0';

                // 현재 숫자와 목표 숫자의 비트 차이 계산
                diffCount += Integer.bitCount(num[currentDigit] ^ num[targetDigit]);
            }

            // 반전시켜야 하는 LED 개수가 P 이하인 경우만 카운트
            if (diffCount > 0 && diffCount <= P) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
