import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 굴다리 길이
        int M = Integer.parseInt(br.readLine()); // 가로등 개수

        st = new StringTokenizer(br.readLine());
        int[] lamps = new int[M];

        for (int i = 0; i < M; i++) {
            lamps[i] = Integer.parseInt(st.nextToken());
        }

        // 이진 탐색을 위한 범위 설정
        int left = 1, right = N, result = N;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (canLightAll(N, lamps, mid)) { // 해당 높이 `mid`로 모든 구간을 비출 수 있는지 확인
                result = mid; // 가능하면 결과 업데이트
                right = mid - 1; // 더 작은 값이 가능한지 확인
            } else {
                left = mid + 1; // 불가능하면 높이를 증가
            }
        }

        System.out.println(result);
    }

    // 높이 `H`로 모든 길을 비출 수 있는지 확인하는 함수
    static boolean canLightAll(int N, int[] lamps, int H) {
        int covered = 0; // 현재 비출 수 있는 위치

        for (int lamp : lamps) {
            if (covered < lamp - H) { // 현재 비출 수 없는 곳이 생긴 경우
                return false;
            }
            covered = lamp + H; // 현재 가로등이 비출 수 있는 범위
        }

        return covered >= N; // 끝까지 비출 수 있으면 true
    }
}
