import java.util.Scanner;

public class Main {
	
	public static int[] h;
	public static int[][] d;
	public static int MX = 500000;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		
		h = new int[51];
		d = new int[51][MX+1];
		
		for(int i=1 ; i<=n ; i++) h[i] = sc.nextInt();
		for(int i=1 ; i<=MX ; i++) d[0][i] = -1;
	    for(int i=1 ; i<=n ; i++) {
	        for(int j=0 ; j<=MX ; j++) {
	            d[i][j] = d[i-1][j];
	            if(j - h[i] >= 0 && d[i-1][j-h[i]] != -1)
	                d[i][j] = max(d[i][j], d[i-1][j-h[i]] + h[i]);
	            if(h[i] - j >= 0 && d[i-1][h[i]-j] != -1)
	                d[i][j] = max(d[i][j], d[i-1][h[i]-j] + j);
	            if(j + h[i] <= MX && d[i-1][j+h[i]] != -1)
	                d[i][j] = max(d[i][j], d[i-1][j+h[i]]);
	        }
	    }
		
	    if(d[n][0] <= 0) System.out.println("-1");
	    else System.out.println(d[n][0]);
	}
	
	private static int max(int a, int b) {
		if(a>b) return a;
		else return b;
	}
}