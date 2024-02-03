import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 카드게임
 * 
 * 1~18까지 적힌 18장의 카드로 게임을 하고 있다.
 * 한 라운드에 한 장씩 카드를 낸 다음
 * 두사람이 낸 카드에 적힌수를 비교해서 점수를 계산한다.
 * 높은 수가 적힌 카드를 낸 사람은 두 카드에 적힌 수의 합만큼
 * 점수를 얻고, 낮은 수가 적힌 카드를 낸 사람은
 * 아무런 점수도 없다.
 * 아홉 라운드를 끝내고 총점을 따져서 더 높은 사람이
 * 게임의 승자가 된다. 두 사람의 총점이 같다면
 * 무승부이다.
 * 
 * 규영이가 내는 카드의 순서를 고정하면, 인영이가
 * 어떻게 카드를 내는지에 따른 9!가지 순서에 따라
 * 규영이의 승패가 정해질 것이다.
 * 이 떄, 규영이가 이기는 경우와 지는 경우가
 * 총 몇가지 인지 구하는 프로그램을 작성하라.
 * 
 * @입력
 * 첫 번째 줄에는 테스트 케이스의 수가 T가 주어진다.
 * 각 테스트 케이스의 첫 번재 줄에는
 * 아홉 개의 정수가 공백으로 구분되어 주어진다.
 * 각 정수는 1이상 18 이하이며, 같은 정수는 없다.
 * 규영이는 정수가 주어지는 순서대로 카드를 낸다고 생각하면된다.
 * 
 * @출력
 * 각 테스트 케이스마다 #x를 출력하고 한칸 띄운 후,
 * 인영이가 카드를 내는 9! 가지의 경우에 대해
 * 규영이가 게임을 이기는 경우의 수와 게임을 
 * 지는 경우의 수를 공백 하나로 구분하여 출력한다.
 * 
 * @해결방안
 * 모든 경우의 수를 생각해야하기 때문에
 * 순열로 구현해야한다.
 * 끝에가서 총점의 합을 따로 계산하여 저장하는
 * 함수를 만들어야한다.
 * 
 * 규영이카드는 고정이다.
 * 민영이카드는 가변적이다.
 * 
 */

public class Solution {

	static boolean[] isSelected;
	static boolean[] inIsSelected;
	static int[] picked;
	static int[] gyu, in;
	static int win, loss;
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int T = Integer.parseInt(st.nextToken());
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			isSelected = new boolean[19];
			gyu = new int[9];
			in = new int[9];
			win = 0;
			loss = 0;
			inIsSelected = new boolean[9];
			picked = new int[9];
			
			int tmp;
			for(int i=0; i<9; i++) {
				tmp = Integer.parseInt(st.nextToken());
				isSelected[tmp] = true;
				gyu[i] = tmp;
			}
			int cnt = 0;
			for (int i = 1; i < 19; i++) {
				if(isSelected[i]) continue;
				in[cnt++] = i;
			}
			perm(0);
			System.out.printf("#%d %d %d\n",t,win,loss);
		}
	}

	public static void perm(int idx) {
		if(idx == 9) {
			int gyu_win = 0;
			int in_win = 0;
			for (int i = 0; i < idx; i++) {
				//규영이 이길때
				gyu_win += gyu[i] > picked[i] ? gyu[i] + picked[i]:0;
				//인영이 이길때
				in_win += picked[i] > gyu[i] ? picked[i] + gyu[i]:0;
			}
			if(gyu_win > in_win)
				win++;
			else if(gyu_win < in_win) // 비기는 경우는 아예 제외
				loss++;
			return;
		}
		for(int i=0; i<9; i++) {
			if(inIsSelected[i]) continue;
			picked[idx] = in[i];
			inIsSelected[i] = true;
			perm(idx + 1);
			inIsSelected[i] = false;
		}
	}
}
