import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {

    static int[] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    static int[] dy = {-1, -1, -1, 0, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int H = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[][] map = new int[H][W];
        Deque<int[]> q = new ArrayDeque<>();

        for (int i = 0; i < H; i++) {
            String tmp = br.readLine();
            for (int j = 0; j < W; j++) {
                map[i][j] = tmp.charAt(j) == '.' ? 0 : tmp.charAt(j) - '0';
                if (map[i][j] == 0) {
                    q.offer(new int[]{i, j});
                }
            }
        }

        int time = 0;

        while (!q.isEmpty()) {
            int level = q.size();
            boolean changed = false;

            for (int i = 0; i < level; i++) {
                int[] cur = q.poll();

                for (int d = 0; d < 8; d++) {
                    int nr = cur[0] + dy[d];
                    int nc = cur[1] + dx[d];

                    if (nr >= 0 && nr < H && nc >= 0 && nc < W && map[nr][nc] > 0) {
                        map[nr][nc]--;
                        if (map[nr][nc] == 0) {
                            q.offer(new int[]{nr, nc});
                            changed = true;
                        }
                    }
                }
            }

            if (changed) {
                time++;
            }
        }

        System.out.println(time);
    }
}
