import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        HashSet<String> set = new HashSet<>();

        int len = S.length();

        // 모든 부분 문자열을 HashSet에 저장
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j <= len; j++) {  // 부분 문자열 추출
                set.add(S.substring(i, j));
            }
        }

        System.out.println(set.size());
    }
}
