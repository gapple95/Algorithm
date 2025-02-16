import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int S = 0;  // 비트마스킹으로 집합을 표현할 정수

        int M = Integer.parseInt(br.readLine());  // 연산 개수

        for (int i = 0; i < M; i++) {
            String[] input = br.readLine().split(" ");
            String command = input[0];
            int x = 0;
            if (input.length == 2) {
                x = Integer.parseInt(input[1]);
            }

            switch (command) {
                case "add":
                    S |= (1 << x);
                    break;
                case "remove":
                    S &= ~(1 << x);
                    break;
                case "check":
                    sb.append((S & (1 << x)) != 0 ? "1\n" : "0\n");
                    break;
                case "toggle":
                    S ^= (1 << x);
                    break;
                case "all":
                    S = (1 << 21) - 1;  // 21번째 비트까지 1로 설정
                    break;
                case "empty":
                    S = 0;
                    break;
            }
        }

        System.out.println(sb);  // 결과 출력
    }
}
