import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int[] sticks = new int[3];

        // 입력 받기
        for (int i = 0; i < 3; i++) {
            sticks[i] = scanner.nextInt();
        }
        scanner.close();

        // 막대 길이 정렬 (오름차순)
        Arrays.sort(sticks);

        // 삼각형 조건을 만족하지 않는 경우 조정
        if (sticks[2] >= sticks[0] + sticks[1]) {
            sticks[2] = sticks[0] + sticks[1] - 1;
        }

        // 삼각형 둘레 출력
        System.out.println(sticks[0] + sticks[1] + sticks[2]);
    }
}
