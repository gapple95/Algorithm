/*
 * 백준 문제 1205 등수구하기
 * 
 * 랭킹 리스트에 얻는 점수가 비오름차순으로 얻는다.
 * 예를 들어 랭킹 리스트가 100,90,90,80일 때 각각
 * 1,2,2,4등이 된다.
 * 점수의 개수 P가 주어지고
 * 리스트에 있는 점수N개가 비오름차순으로 주어지고,
 * 태수의 새로운 점수가 주어진다.
 * 
 * 이때 태수가 몇등인지 출력.
 * 만약 태수가 새로운 점수가 랭킹 리스트에 올라갈 수 없다면 -1
 * 
 * 입력
 * 리스트 갯수 N
 * 태수의 점수
 * 랭킹리스트 크기 P
 * 
 * 출력
 * 태수의 랭킹
 * 만약 랭킹에도 못든다면(전체 랭킹순위에서 가장 낮은 수위라면)
 * -1 출력
 * 
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner s = new Scanner(System.in);

		int n = s.nextInt();
		int score = s.nextInt();
		int p = s.nextInt();

		ArrayList<Integer> arr = new ArrayList<Integer>();
		int Min = Integer.MAX_VALUE;
		int tmp = 0;
		for (int i = 0; i < n; i++) {
			tmp = s.nextInt();
			arr.add(tmp);
			if(tmp < Min) Min = tmp;
		}
		arr.add(score);
		arr.sort(Comparator.reverseOrder());
		//
		if(arr.lastIndexOf(score)<p) {
			System.out.println(arr.indexOf(score) + 1);
		} else {
			System.out.println(-1);
		}
//		System.out.println(arr.toString());
	}

}
