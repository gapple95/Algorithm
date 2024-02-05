import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
	static int N; // 탑의 개수
	static int[] recep; // 레이저를 송신하는 탑 번호
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine()); // 탑의 개수 입력받음
		
		Stack<Integer> towers = new Stack<>(); // 탑의 높이를 저장하는 Stack
		Stack<Integer> idxs = new Stack<>(); // 탑의 인덱스를 저장하는 Stack
		recep = new int[N]; // 탑 개수만큼 배열 만든다.
		StringTokenizer st = new StringTokenizer(br.readLine());
		int nextNum = 0;
		for(int i = 0 ; i < N ; i++) {
			nextNum = Integer.parseInt(st.nextToken()); // 탑의 높이 입력받음
			for(int j = towers.size() ; j > 0 ; j--) { // 탑 높이 스택만큼 역으로 탐색하면서
				if(towers.peek() > nextNum) break; // 자신보다 높은 높이의 탑이 있으면 반복문 종료
				
				// 높이가 현재 받은 탑의 높이보다 낮다면 탑과 인덱스 Stack에서 하나씩 뺀다(해당 탑의 신호를 수신하지 못하기 때문에)
				// 이렇게 빼주기 때문에 탑 높이 스택만큼 탐색해도 시간초과가 나오지 않는다.
				towers.pop(); 
				idxs.pop(); 
			}
			
			// Stack에 아무것도 없으면 수신할 탑이 없는 것이므로, 0을 넣는다.
			if(towers.isEmpty()) {
				recep[i] = 0;
			} 
			// 수신할 탑이 있으면, 해당 탑의 인덱스를 넣어준다.
			else {
				recep[i] = idxs.peek();
			}
			
			// 아까 받은 탑의 높이와 인덱스를 각각 stack에 넣어준다.
			towers.add(nextNum);
			idxs.add(i+1); // i는 0부터 시작하지만 탑 번호는 1부터 시작하기 때문에 1을 더한다.
		}
		
		// 수신 인덱스가 담긴 배열을 출력한다.
		for(int i = 0 ; i <N ; i++) {
			if(i == N-1)System.out.print(recep[i]);
			else System.out.print(recep[i]+" ");
		}
		
	}

}