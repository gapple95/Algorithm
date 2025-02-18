import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int a1 = Integer.parseInt(st.nextToken());
        int a0 = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int n0 = Integer.parseInt(st.nextToken());

        // 점근적 표기법 조건 확인
        boolean condition = (a1 * n0 + a0) <= (c * n0) && a1 <= c;

        System.out.println(condition ? 1 : 0);
    }
}
