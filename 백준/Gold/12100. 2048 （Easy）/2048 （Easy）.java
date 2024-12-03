import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, answer;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(arr, 0);

        System.out.println(answer);
    }

    public static void dfs(int[][] arr, int depth) {
        if (depth == 5) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    answer = Math.max(answer, arr[i][j]);
                }
            }
            return;
        }

        dfs(up(deepCopy(arr)), depth + 1);
        dfs(down(deepCopy(arr)), depth + 1);
        dfs(left(deepCopy(arr)), depth + 1);
        dfs(right(deepCopy(arr)), depth + 1);
    }

    public static int[][] deepCopy(int[][] arr) {
        int[][] result = new int[N][N];
        for (int i = 0; i < N; i++) {
            result[i] = arr[i].clone();
        }
        return result;
    }

    public static int[][] up(int[][] arr) {
        int[][] result = deepCopy(arr);
        boolean[][] merged = new boolean[N][N];

        for (int j = 0; j < N; j++) {
            for (int i = 1; i < N; i++) {
                if (result[i][j] == 0) continue;
                int value = result[i][j];
                int row = i;

                while (row > 0 && result[row - 1][j] == 0) {
                    result[row - 1][j] = value;
                    result[row][j] = 0;
                    row--;
                }

                if (row > 0 && result[row - 1][j] == value && !merged[row - 1][j]) {
                    result[row - 1][j] *= 2;
                    result[row][j] = 0;
                    merged[row - 1][j] = true;
                }
            }
        }

        return result;
    }

    public static int[][] down(int[][] arr) {
        int[][] result = deepCopy(arr);
        boolean[][] merged = new boolean[N][N];

        for (int j = 0; j < N; j++) {
            for (int i = N - 2; i >= 0; i--) {
                if (result[i][j] == 0) continue;
                int value = result[i][j];
                int row = i;

                while (row < N - 1 && result[row + 1][j] == 0) {
                    result[row + 1][j] = value;
                    result[row][j] = 0;
                    row++;
                }

                if (row < N - 1 && result[row + 1][j] == value && !merged[row + 1][j]) {
                    result[row + 1][j] *= 2;
                    result[row][j] = 0;
                    merged[row + 1][j] = true;
                }
            }
        }

        return result;
    }

    public static int[][] left(int[][] arr) {
        int[][] result = deepCopy(arr);
        boolean[][] merged = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                if (result[i][j] == 0) continue;
                int value = result[i][j];
                int col = j;

                while (col > 0 && result[i][col - 1] == 0) {
                    result[i][col - 1] = value;
                    result[i][col] = 0;
                    col--;
                }

                if (col > 0 && result[i][col - 1] == value && !merged[i][col - 1]) {
                    result[i][col - 1] *= 2;
                    result[i][col] = 0;
                    merged[i][col - 1] = true;
                }
            }
        }

        return result;
    }

    public static int[][] right(int[][] arr) {
        int[][] result = deepCopy(arr);
        boolean[][] merged = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = N - 2; j >= 0; j--) {
                if (result[i][j] == 0) continue;
                int value = result[i][j];
                int col = j;

                while (col < N - 1 && result[i][col + 1] == 0) {
                    result[i][col + 1] = value;
                    result[i][col] = 0;
                    col++;
                }

                if (col < N - 1 && result[i][col + 1] == value && !merged[i][col + 1]) {
                    result[i][col + 1] *= 2;
                    result[i][col] = 0;
                    merged[i][col + 1] = true;
                }
            }
        }

        return result;
    }
}
