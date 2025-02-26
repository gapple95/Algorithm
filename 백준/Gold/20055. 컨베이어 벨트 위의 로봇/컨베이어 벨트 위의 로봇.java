import java.io.*;
import java.util.*;

public class Main {
    static int N, K; // 벨트 길이, 종료 조건 (내구도 0 개수)
    static int[] belt; // 벨트 내구도
    static boolean[] robots; // 로봇 위치 저장
    static int step = 0; // 단계 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        belt = new int[2 * N];
        robots = new boolean[N]; // 로봇은 N칸까지만 존재

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            belt[i] = Integer.parseInt(st.nextToken());
        }

        while (true) {
            step++;

            // 1. 벨트 회전
            rotateBelt();

            // 2. 로봇 이동 (뒤에서부터 확인)
            moveRobots();

            // 3. 로봇 올리기
            placeRobot();

            // 4. 내구도 체크 (종료 조건)
            if (countBroken() >= K) break;
        }

        System.out.println(step);
    }

    // 1. 벨트 회전
    static void rotateBelt() {
        int lastDurability = belt[2 * N - 1]; // 마지막 벨트 내구도 저장

        // 벨트 한 칸씩 뒤로 이동
        for (int i = 2 * N - 1; i > 0; i--) {
            belt[i] = belt[i - 1];
        }
        belt[0] = lastDurability; // 마지막 원소를 첫 번째로 이동

        // 로봇도 한 칸씩 이동
        for (int i = N - 1; i > 0; i--) {
            robots[i] = robots[i - 1];
        }
        robots[0] = false; // 1번 칸에는 로봇을 올릴 예정이므로 비움
        robots[N - 1] = false; // N번 칸 도착하면 내려야 함
    }

    // 2. 로봇 이동
    static void moveRobots() {
        for (int i = N - 2; i >= 0; i--) { // 뒤에서부터 이동
            if (robots[i] && !robots[i + 1] && belt[i + 1] > 0) { // 앞에 로봇이 없고, 내구도가 남아있으면 이동
                robots[i] = false;
                robots[i + 1] = true;
                belt[i + 1]--; // 이동하면 내구도 감소
            }
        }
        robots[N - 1] = false; // N번 칸에 도착한 로봇은 즉시 내려야 함
    }

    // 3. 로봇 올리기
    static void placeRobot() {
        if (belt[0] > 0) { // 1번 칸의 내구도가 남아있다면 로봇을 올림
            robots[0] = true;
            belt[0]--; // 로봇을 올리면 내구도 감소
        }
    }

    // 4. 내구도 0인 칸 개수 세기
    static int countBroken() {
        int count = 0;
        for (int durability : belt) {
            if (durability == 0) count++;
        }
        return count;
    }
}
