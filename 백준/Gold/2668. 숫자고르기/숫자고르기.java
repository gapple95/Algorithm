import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] map;
    static boolean[] visited;
    static boolean[] finished;
    static List<Integer> best = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        map = new int[N + 1]; // 1-based index 사용
        visited = new boolean[N + 1];
        finished = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            map[i] = Integer.parseInt(br.readLine());
        }

        // 1부터 N까지 모든 숫자에서 DFS 탐색
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                dfs(i, new ArrayList<>());
            }
        }

        // 결과 출력
        Collections.sort(best);
        System.out.println(best.size());
        for (int num : best) {
            System.out.println(num);
        }
    }

    static void dfs(int current, List<Integer> path) {
        if (visited[current]) {
            if (!finished[current]) {
                int idx = path.indexOf(current);
                best.addAll(path.subList(idx, path.size()));
            }
            return;
        }

        visited[current] = true;
        path.add(current);
        dfs(map[current], path);
        finished[current] = true;
    }
}
