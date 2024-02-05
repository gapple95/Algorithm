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

		Stack<Integer> s1 = new Stack<>(); // 탑을 담는 스택
		Stack<Integer> s2 = new Stack<>(); // 탑의 인덱스를 담는 스택

		StringTokenizer st = new StringTokenizer(br.readLine());
		int get_num;
		for (int i = 0; i < N; i++) {
			get_num = Integer.parseInt(st.nextToken());
			
			// 스택 내부를 자신보다 큰 값이 있는지 확인하고 나보다 작으면 제거
			for(int j=s1.size(); j>0; j--) {
				if(s1.peek() > get_num)
					break;
				s1.pop();
				s2.pop();
			}
			
			// 모두 제거 후 스택이 다 비어진다면? 그게 가장 큰것이니 인덱스는 0 
			// 안비어져 있다면 가장 큰것이 안쪽에 있다는 의미 => 안쪽의 인덱스를 정답 배열에 넣기
			if (s1.isEmpty()) {
				arr[i] = 0;
			} else {
				arr[i] = s2.peek();
			}

			// 모든 계산후, 스택에 값을 넣고, 다음 인덱스도 넣는다.
			s1.push(get_num);
			s2.push(i+1);
		}

		for (int i = 0; i < N; i++) {
            if(i==N-1)System.out.print(arr[i]);
            else System.out.print(arr[i] + " ");
		}


	}

}