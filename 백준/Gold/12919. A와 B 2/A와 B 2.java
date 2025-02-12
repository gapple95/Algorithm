import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static String S, T;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        S = br.readLine();
        T = br.readLine();
        
        dfs(new StringBuilder(T));
        
        System.out.println(answer);
        br.close();
    }

    public static void dfs(StringBuilder current) {
        if (current.length() == S.length()) {
            if (current.toString().equals(S)) {
                answer = 1;
            }
            return;
        }
        
        // 마지막 문자가 'A'이면 A 제거
        if (current.charAt(current.length() - 1) == 'A') {
            current.deleteCharAt(current.length() - 1);
            dfs(current);
            current.append('A');  // 원상 복구
        }

        // 첫 번째 문자가 'B'이면 뒤집고 B 제거
        if (current.charAt(0) == 'B') {
            current.reverse().deleteCharAt(current.length() - 1);
            dfs(current);
            current.append('B').reverse();  // 원상 복구
        }
    }
}
