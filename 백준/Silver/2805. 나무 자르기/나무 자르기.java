import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] trees = new int[N];
        int top_trees = 0;

        for (int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
            top_trees = Math.max(top_trees, trees[i]);
        }

        int start = 0;
        int end = top_trees;
        int result = 0;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            long sum = 0; // 나무 길이 합은 long으로 선언
            for (int tree : trees) {
                if (tree > mid) {
                    sum += tree - mid;
                }
            }

            if (sum >= M) { // 필요한 나무를 충족하면 더 높은 절단 높이 시도
                result = mid;
                start = mid + 1;
            } else { // 필요한 나무를 충족하지 못하면 절단 높이를 낮춤
                end = mid - 1;
            }
        }

        System.out.println(result);
    }
}
