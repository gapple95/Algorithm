import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static class Book {
		String title;
		int count;
		
		public Book(String title, int count) {
			this.title = title;
			this.count = count;
		}
	}
	
	public static void main(String[] args) throws IOException {
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		Map<String, Integer> books = new HashMap<>();
		
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			if(books.containsKey(str)) {
				books.put(str, books.get(str) + 1);				
			} else {
				books.put(str, 0);
			}
		}
		
		PriorityQueue<Book> pq = new PriorityQueue<>((o1, o2) -> {
			if(o1.count < o2.count)
				return 1;
			else if(o1.count > o2.count)
				return -1;
			else {
				return o1.title.compareTo(o2.title);
			}
		});
		
		for(String key: books.keySet()) {
			pq.add(new Book(key, books.get(key)));
		}
		
		sb.append(pq.poll().title);
		System.out.println(sb);
		
		br.close();
	}

}
