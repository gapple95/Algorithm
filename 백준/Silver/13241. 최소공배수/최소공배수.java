import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long gcd = gcd(A, B); // 최대공약수 계산
        long lcm = (A / gcd) * B; // 최소공배수 계산 (오버플로우 방지)

        System.out.println(lcm);
    }

    // 유클리드 호제법을 이용한 최대공약수(GCD) 계산
    static long gcd(long a, long b) {
        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }
}
