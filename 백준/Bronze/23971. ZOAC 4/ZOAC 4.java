import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int H = sc.nextInt(); // 행 개수
        int W = sc.nextInt(); // 열 개수
        int N = sc.nextInt(); // 행 간격
        int M = sc.nextInt(); // 열 간격

        // 올림(ceil) 연산 적용하여 행과 열 개수 계산
        int rowCount = (H + N) / (N + 1);
        int colCount = (W + M) / (M + 1);

        // 최종 결과 출력
        System.out.println(rowCount * colCount);

        sc.close();
    }
}
