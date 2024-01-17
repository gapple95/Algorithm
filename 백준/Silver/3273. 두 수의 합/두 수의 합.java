import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		
		int N = s.nextInt();
		int[] arr = new int[N];
		for(int i=0; i<N; i++)
			arr[i] = s.nextInt();
		
		int x = s.nextInt();
		
		int count = 0;
		for(int i=0; i<N; i++) {
			for(int j=i+1; j<N; j++) {
				if(arr[i]+arr[j] == x) {
					count++;
                    break;
                }
			}
		}
		
		System.out.println(count);
				
	}

}
