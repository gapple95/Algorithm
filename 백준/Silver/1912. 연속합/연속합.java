import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		int array[];
		int sum[];
		int n,i;
		int max=0;
		
		Scanner sc = new Scanner(System.in);
		
		n = sc.nextInt();
		array = new int[n];
		sum = new int[n];
		
		for(i=0 ; i<n ; i++) {
			array[i] = sc.nextInt();
		}
		
		sum[0]=array[0];
		max=sum[0];
		for(i=1 ; i<n ;i++) {
			if(sum[i-1]+array[i] > array[i]) {
				sum[i] = sum[i-1] + array[i];
				
			}else {
				sum[i] = array[i];
			}
            if(sum[i] > max) max = sum[i];
		}
		System.out.println(max);
	}

}
