import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long n = Long.parseLong(br.readLine());
        
        // 수행 횟수 계산
        long result = (n * (n - 1) * (n - 2)) / 6;

        // 출력
        System.out.println(result);
        System.out.println(3); // 최고차항의 차수: O(N^3)
    }
}
