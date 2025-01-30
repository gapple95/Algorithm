import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int r, c, count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        int A = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        
        sb.append(A*B);
        
        System.out.println(sb);
        
        br.close();
        
    }
}