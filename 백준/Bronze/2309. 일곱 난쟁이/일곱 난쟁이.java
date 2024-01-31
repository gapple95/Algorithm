import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args){
		// TODO Auto-generated method stub

		int[] arr = new int[9];
		
		Scanner s = new Scanner(System.in);
		
		for(int i=0; i<9; i++) {
			arr[i] = s.nextInt();
		}
		
		// 오름차순 정렬
//		int tmp;
//		for(int i=0; i<9; i++) {
//			for(int j=i+1; j<9; j++) {
//				if(arr[i] > arr[j]) {
//					tmp = arr[i];
//					arr[i] = arr[j];
//					arr[j] = tmp;
//				}
//			}
//		}
		// 오름차순 정렬
		Arrays.sort(arr);
		
		int sum = Arrays.stream(arr).sum();
		int diff = sum - 100;
		int a = 0, b = 0;
		
//		System.out.println(diff);
		
		for(int i=0; i<9; i++) {
			for(int j=0; j<9; j++) {
//				System.out.println("i " + arr[i] + ", j " + arr[j]);
				if(i==j) continue;
				if (arr[i] + arr[j] == diff) {
					a = i;
					b = j;
				}
					
			}
		}
		
		for(int i=0; i<9; i++) {
			if(i == a || i == b)
				continue;
			System.out.println(arr[i]);
		}
	}

}
