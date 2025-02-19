import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int aSize = Integer.parseInt(st.nextToken());
        int bSize = Integer.parseInt(st.nextToken());

        HashSet<Integer> A = new HashSet<>();
        HashSet<Integer> B = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < aSize; i++) {
            A.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < bSize; i++) {
            B.add(Integer.parseInt(st.nextToken()));
        }

        // (A - B)
        HashSet<Integer> aDiff = new HashSet<>(A);
        aDiff.removeAll(B);

        // (B - A)
        HashSet<Integer> bDiff = new HashSet<>(B);
        bDiff.removeAll(A);

        // 정답 출력
        System.out.println(aDiff.size() + bDiff.size());
    }
}
