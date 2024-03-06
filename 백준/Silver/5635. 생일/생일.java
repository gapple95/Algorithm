import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/*
 * 백준 5635 생일
 * 
 * 메모리
 * 시간
 * 
 * 어떤 반에 있는 학생들의 생일이 주어졌을 떄, 가장 나이가 적은 사람과
 * 가장 많은 사람을 구하는 프로그램을 작성하시오.
 * 
 * @입력
 * 첫째 줄에 반에 있는 학생의 수 n이 주어진다.(1~100)
 * 
 * 다음 n개 줄에는 각 학생의 이름과 생일이 "이름 dd mm yyyy"와 같은
 * 형식으로 주어진다. 이름은 그 학생의 이름이며, 최대 15글자로 이루어져 있다.
 * dd mm yyyy는 생일 일, 월, 연도이다.
 * yyyy(1990~2010), mm(1~12), dd(1~31)
 * 주어지는 생일은 올바른 날짜이며, 연, 월, 일은 0으로 시작하지 않는다.
 * 이름이 같거나, 생일이 같은 사람은 없다.
 * 
 * @출력
 * 첫째 줄에 가장 나이가 적은 사람의 이름, 둘째줄에 가장 나이가 많은
 * 사람 이름을 출력한다.
 * 
 * @해결방안
 * human 클래스를 만들고 comparable을 구현하여 우선순위 큐를 활용한다.
 * 
 */

public class Main {

	static class Human implements Comparable<Human> {
		String name;
		int dd, mm, yyyy;

		public Human(String name, int dd, int mm, int yyyy) {
			super();
			this.name = name;
			this.dd = dd;
			this.mm = mm;
			this.yyyy = yyyy;
		}

		@Override
		public int compareTo(Human o) {
			if (this.yyyy < o.yyyy)
				return 1;
			else {
				if (this.yyyy == o.yyyy) {
					if (this.mm < o.mm)
						return 1;
					else {
						if (this.mm == o.mm) {
							if (this.dd < o.dd)
								return 1;
							return -1;
						}
						return -1;
					}
				}
				return -1;
			}
		}

	}

	static class Human_reverse implements Comparable<Human_reverse> {
		String name;
		int dd, mm, yyyy;

		public Human_reverse(String name, int dd, int mm, int yyyy) {
			super();
			this.name = name;
			this.dd = dd;
			this.mm = mm;
			this.yyyy = yyyy;
		}

		@Override
		public int compareTo(Human_reverse o) {
			if (this.yyyy > o.yyyy)
				return 1;
			else {
				if (this.yyyy == o.yyyy) {
					if (this.mm > o.mm)
						return 1;
					else {
						if (this.mm == o.mm) {
							if (this.dd > o.dd)
								return 1;
							return -1;
						}
						return -1;
					}
				}
				return -1;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());

		PriorityQueue<Human> q_min = new PriorityQueue<Human>();
		PriorityQueue<Human_reverse> q_max = new PriorityQueue<Human_reverse>();

		String name;
		int d, m, y;
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			name = st.nextToken();
			d = Integer.parseInt(st.nextToken());
			m = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			q_min.offer(new Human(name, d, m, y));
			q_max.offer(new Human_reverse(name, d, m, y));
		}
		sb.append(q_min.peek().name).append("\n").append(q_max.peek().name);

		System.out.println(sb);
	}

}