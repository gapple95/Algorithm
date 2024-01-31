import java.util.Arrays;
import java.util.Scanner;

/*
 * 백준 2309 일곱난쟁이
 * 
 * 일과를 마치고 돌아온 난쟁이가 일곱명이 아닌 아홉명이였던것!
 * 
 * 뛰어난 수학적 직관력을 가지고 있던 백성공주는,
 * 다행스럽게도 일곱 난쟁이의 키의 합이 100이 됨을 기억해냈다.
 * 
 * 아홉 난쟁이의 키가 주어졌을 때, 백성공주를 도와 일곱 난쟁이를 찾는 프로그램을
 * 작성하시오.
 * 
 * @입력
 * 아홉 개의 줄에 걸쳐 난쟁이의 키가 주어진다.
 * 전부 다해서 100을 넘지 않는 자연수이며 다 다르다.
 * 가능한 정답이 여러가지인 경우 아무거나 출력한다.
 * 
 * @출력
 * 일곱 난쟁이의 키를 오름차순으로 출력한다. 무조건 답이 있다.
 * 
 * 재귀로 모든 난쟁이의 키를 더하고, 100이면 출력
 * 
 */

public class Main {

	static int[] arr;
	static int[] dwap;
	
	static boolean[] isSelected;
	static boolean success;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner s = new Scanner(System.in);
		
		arr = new int[9];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = s.nextInt();
		}
		
		dwap = new int[7];
		isSelected = new boolean[9];
		

		df(0, 0, 0);
		
	}
	
//	static int sum() {
//		int sum = 0;
//		for (int i = 0; i < dwap.length; i++) {
//			sum += dwap[i];
//		}
//		return sum;
//	}
	
	static void df(int idx, int sum, int start) {
		if(success)
			return;
		if(idx == 7 && sum == 100) { // 기저조건
			Arrays.sort(dwap);
			for (int i : dwap) {
				System.out.println(i);
			}
			success = true;
			return;
		} else if(idx == 7) {
			return;
		}
		else {
			for (int i = start; i < arr.length; i++) {
				if(isSelected[i])
					continue;
				dwap[idx] = arr[i];
				isSelected[i] = true;
				
				df(idx + 1, sum + arr[i], i);
				isSelected[i] = false;
				
			}
		}
	}

}