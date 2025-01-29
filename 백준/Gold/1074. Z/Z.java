import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int r, c, count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        int size = (int) Math.pow(2, N);
        solve(0, 0, size);
    }

    public static void solve(int x, int y, int size) {
        if (size == 1) {
            System.out.println(count);
            return;
        }

        int newSize = size / 2;

        // 1사분면 (좌상)
        if (r < x + newSize && c < y + newSize) {
            solve(x, y, newSize);
        }
        // 2사분면 (우상)
        else if (r < x + newSize && c >= y + newSize) {
            count += newSize * newSize;
            solve(x, y + newSize, newSize);
        }
        // 3사분면 (좌하)
        else if (r >= x + newSize && c < y + newSize) {
            count += 2 * newSize * newSize;
            solve(x + newSize, y, newSize);
        }
        // 4사분면 (우하)
        else {
            count += 3 * newSize * newSize;
            solve(x + newSize, y + newSize, newSize);
        }
    }
}
