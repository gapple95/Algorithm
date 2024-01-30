import java.util.Scanner;

public class Main {

	static int n;
	static int m;
	
	static int[] picked;
	static boolean[] isSelected;
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		
		n = s.nextInt();
		m = s.nextInt();
		
		picked = new int[m];
		isSelected = new boolean[n];
		
		def(0);
		
	}
	
	public static void def(int idx) {
		if(idx == m) { // 종료조건
			for(int i=0; i<idx ; i++) {
				System.out.print(picked[i] + " ");
			}
			System.out.println();
		} else { // 유도부분
			for(int i=1; i<=n; i++) {
				if(isSelected[i-1])
					continue;
				picked[idx] = i;
				isSelected[i-1] = true;
				
				def(idx+1);
				
				isSelected[i-1] = false;
				
			}
		}
	}

}
