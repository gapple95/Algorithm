import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            // 0 0 0 입력 시 종료
            if (a == 0 && b == 0 && c == 0)
                break;

            // 가장 긴 변 찾기 (정렬 없이 직접 비교)
            int max = Math.max(a, Math.max(b, c));
            int sum = a + b + c - max; // 나머지 두 변의 합

            // 삼각형 성립 조건 (가장 긴 변 < 나머지 두 변의 합)
            if (max >= sum) {
                sb.append("Invalid");
            } else if (a == b && b == c) {
                sb.append("Equilateral");
            } else if (a == b || b == c || a == c) {
                sb.append("Isosceles");
            } else {
                sb.append("Scalene");
            }

            sb.append("\n");
        }

        System.out.print(sb);
        br.close();
    }
}
