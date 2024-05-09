import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = null;
	
		int N = Integer.parseInt(br.readLine());
		
		Queue<Integer> q = new ArrayDeque<>();
		int back = -1;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			
			String cmd = st.nextToken();
			
			switch(cmd) {
			case "push" : {
				back = Integer.parseInt(st.nextToken());
				q.add(back);
				break;
			}
			case "front" : {
				if(q.isEmpty())
					sb.append(-1);
				else
					sb.append(q.peek());
				break;
			}
			case "back" : {
				if(q.isEmpty())
					sb.append(-1);
				else
					sb.append(back);
				break;
			}
			case "pop" : {
				if(q.isEmpty())
					sb.append(-1);
				else
					sb.append(q.poll());
				break;
			}
			case "size" : {
				sb.append(q.size());
				break;
			}
			case "empty" : {
				sb.append(q.isEmpty()?1:0);
				break;
			}
			}
			if(!cmd.equals("push"))
				sb.append("\n");
		}
		
		System.out.println(sb);
	}

}