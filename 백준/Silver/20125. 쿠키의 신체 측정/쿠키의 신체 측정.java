import java.io.*;

public class Main {
    static int N;
    static char[][] grid;
    static int heartX, heartY;  // 심장 위치

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        grid = new char[N][N];

        // 입력 받기
        for (int i = 0; i < N; i++) {
            grid[i] = br.readLine().toCharArray();
        }

        // 1. 심장 찾기
        findHeart();

        // 2. 팔, 허리, 다리 길이 계산
        int leftArm = countLeftArm();
        int rightArm = countRightArm();
        int waist = countWaist();
        int[] legs = countLegs();

        // 출력
        System.out.println((heartX + 1) + " " + (heartY + 1)); // 심장 위치 (1-based index)
        System.out.println(leftArm + " " + rightArm + " " + waist + " " + legs[0] + " " + legs[1]);
    }

    // 1. 심장 찾기
    static void findHeart() {
        for (int i = 1; i < N - 1; i++) { // 위쪽 끝부분 제외
            for (int j = 1; j < N - 1; j++) { // 왼쪽 끝, 오른쪽 끝 제외
                if (grid[i][j] == '*' && grid[i][j - 1] == '*' && grid[i][j + 1] == '*' && grid[i + 1][j] == '*') {
                    heartX = i;
                    heartY = j;
                    return;
                }
            }
        }
    }

    // 2. 왼팔 길이 계산
    static int countLeftArm() {
        int count = 0;
        for (int j = heartY - 1; j >= 0; j--) {
            if (grid[heartX][j] == '*') count++;
            else break;
        }
        return count;
    }

    // 3. 오른팔 길이 계산
    static int countRightArm() {
        int count = 0;
        for (int j = heartY + 1; j < N; j++) {
            if (grid[heartX][j] == '*') count++;
            else break;
        }
        return count;
    }

    // 4. 허리 길이 계산
    static int countWaist() {
        int count = 0;
        for (int i = heartX + 1; i < N; i++) {
            if (grid[i][heartY] == '*') count++;
            else break;
        }
        return count;
    }

    // 5. 다리 길이 계산
    static int[] countLegs() {
        int leftLeg = 0, rightLeg = 0;
        int waistEnd = heartX + countWaist(); // 허리 끝나는 위치

        for (int i = waistEnd + 1; i < N; i++) {
            if (grid[i][heartY - 1] == '*') leftLeg++;
            if (grid[i][heartY + 1] == '*') rightLeg++;
        }
        return new int[]{leftLeg, rightLeg};
    }
}
