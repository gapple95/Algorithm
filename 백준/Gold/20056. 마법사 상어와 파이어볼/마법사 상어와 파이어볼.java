import java.io.*;
import java.util.*;

public class Main {
    static class Fireball {
        int m, s, d, r, c;

        public Fireball(int m, int s, int d, int r, int c) {
            this.m = m;
            this.s = s;
            this.d = d;
            this.r = r;
            this.c = c;
        }
    }

    static int N, M, K;
    static Queue<Fireball>[][] map;
    static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new LinkedList[N + 1][N + 1];
        for (int i = 1; i <= N; i++)
            for (int j = 1; j <= N; j++)
                map[i][j] = new LinkedList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            map[r][c].add(new Fireball(m, s, d, r, c));
        }

        while (K-- > 0) {
            moveFireballs();
            splitFireballs();
        }
        
        int sum = 0;
        for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				while(!map[i][j].isEmpty())
					sum += map[i][j].poll().m;
			}
		}

        sb.append(sum);
        System.out.println(sb);
        
        br.close();
    }
    
  
    
    static void moveFireballs() {
        List<Fireball> movingFireballs = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                while (!map[i][j].isEmpty()) { // O(1) 연산
                    Fireball fb = map[i][j].poll();
                    int nx = (i + dy[fb.d] * fb.s % N + N) % N;
                    int ny = (j + dx[fb.d] * fb.s % N + N) % N;

                    if (nx == 0) nx = N;
                    if (ny == 0) ny = N;

                    movingFireballs.add(new Fireball(fb.m, fb.s, fb.d, nx, ny));
                }
            }
        }

        // 이동 완료된 파이어볼 다시 추가
        for (Fireball fb : movingFireballs) {
            map[fb.r][fb.c].add(fb);
        }
    }

    
    static void splitFireballs() {
        List<Fireball> mergingFireballs = new ArrayList<>();

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j].size() < 2) continue;

                int sum_m = 0, sum_s = 0;
                int count = map[i][j].size();
                boolean isOdd = false, isEven = false;

                while (!map[i][j].isEmpty()) {
                    Fireball fb = map[i][j].poll();
                    sum_m += fb.m;
                    sum_s += fb.s;
                    if (fb.d % 2 == 0) isEven = true;
                    else isOdd = true;
                }

                sum_m /= 5;
                sum_s /= count;

                if (sum_m == 0) continue; // 소멸 조건

                int[] newDirs = (isOdd && isEven) ? new int[]{1, 3, 5, 7} : new int[]{0, 2, 4, 6};
                for (int d : newDirs) {
                    mergingFireballs.add(new Fireball(sum_m, sum_s, d, i, j));
                }
            }
        }

        // 병합된 파이어볼 다시 추가
        for (Fireball fb : mergingFireballs) {
            map[fb.r][fb.c].add(fb);
        }
    }

    
    
}
