import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N];

        Stack<int[]> stack = new Stack<>(); // 높이와 인덱스 쌍을 저장하는 단일 스택 사용

        StringTokenizer st = new StringTokenizer(br.readLine());
        int get_num;
        for (int i = 0; i < N; i++) {
            get_num = Integer.parseInt(st.nextToken());

            // 더 큰 탑을 찾을 때까지 스택에서 요소를 팝합니다.
            while (!stack.isEmpty() && stack.peek()[0] <= get_num) {
                stack.pop();
            }

            // 스택이 비어있으면 왼쪽에 더 높은 탑이 없습니다.
            // 그렇지 않으면 왼쪽에 가장 높은 탑이 스택의 맨 위에 있습니다.
            if (stack.isEmpty()) {
                arr[i] = 0;
            } else {
                arr[i] = stack.peek()[1];
            }

            // 현재 탑의 높이와 인덱스를 스택에 푸시합니다.
            stack.push(new int[]{get_num, i + 1});
        }

        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i]+ " ");
        }
    }
}