import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static class Fireball {
        int m, s, d;

        public Fireball(int m, int s, int d) {
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }

    static int N, M, K;
    static List<Fireball>[][] map;
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new ArrayList[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            map[r][c].add(new Fireball(m, s, d));
        }

        while (K-- > 0) {
            moveFireballs();
            splitFireballs();
        }

        System.out.println(getTotalMass());
    }

    static void moveFireballs() {
        List<Fireball>[][] newMap = new ArrayList[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                newMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (Fireball fb : map[i][j]) {
                    int nx = (i + dy[fb.d] * fb.s % N + N) % N;
                    int ny = (j + dx[fb.d] * fb.s % N + N) % N;
                    if (nx == 0) nx = N;
                    if (ny == 0) ny = N;
                    newMap[nx][ny].add(fb);
                }
            }
        }
        map = newMap;
    }

    static void splitFireballs() {
        List<Fireball>[][] newMap = new ArrayList[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                newMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j].size() > 1) {
                    int sum_m = 0, sum_s = 0;
                    boolean isOdd = false, isEven = false;

                    for (Fireball fb : map[i][j]) {
                        sum_m += fb.m;
                        sum_s += fb.s;
                        if (fb.d % 2 == 0) isEven = true;
                        else isOdd = true;
                    }

                    sum_m /= 5;
                    sum_s /= map[i][j].size();

                    if (sum_m > 0) {
                        int[] newDirs = (isOdd && isEven) ? new int[]{1, 3, 5, 7} : new int[]{0, 2, 4, 6};
                        for (int d : newDirs) {
                            newMap[i][j].add(new Fireball(sum_m, sum_s, d));
                        }
                    }
                } else if (!map[i][j].isEmpty()) {
                    newMap[i][j] = new ArrayList<>(map[i][j]);
                }
            }
        }
        map = newMap;
    }

    static int getTotalMass() {
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (Fireball fb : map[i][j]) {
                    sum += fb.m;
                }
            }
        }
        return sum;
    }
}
