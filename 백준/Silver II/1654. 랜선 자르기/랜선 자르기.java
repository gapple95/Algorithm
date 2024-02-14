import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * 백준 1654 랜선 자르기
 * 
 * 메모리 15612
 * 시간 148
 * 
 * 캠프 때 쓸 N개의 랜선을 만들어야하는데 너무 바쁘다.
 * 자체적으로 K개의 랜선을 가지고 있다. 그러나 K개의 랜선은 길이가 제각각이다.
 * 박성원은 랜선을 모두 N개의 같은 길이의 랜선으로 만들고 싶었기 때무에 K개의 랜선을
 * 잘라서 만들어야한다. 예를들어 300cm 짜리 랜선에서 140cm짜리 랜선을 두 개
 * 잘라내면 20cm 버려야 한다. (이미 자른 랜선은 붙일 수 없다.)
 * 
 * 편의를 위해 랜선을 자르거나 만들때 손실되는 길이는 없다고 가정하며, 기존의
 * K개의 랜선으로 N개의 랜선을 만들 수 없는 경우는 없다고 가정하자. 그리고 자를때는
 * 항상 센티미터 단위로 정수길이만ㅋ믐 자른다.
 * N개보다 많이 만드는 것도 N개를 만드는 것에 포함된다.
 * 이때 만들 수 있는 최대 랜선의 길이를 구하는 프로그램을 작성하시오.
 * 
 * @입력
 * 첫째 줄에는 오영식이 이미 가지고 있는 랜선의 개수 K,그리고 필요한 랜선의 개수 N이
 * 입력된다. K는 1이상 10,000이하의 정수이고, N은 1이상 1,000,000이하의 정수이다.
 * 그리고 항상 K<=N이다. 
 * 그 후 K줄에 걸쳐 이미 가지고 있는 각 랜선의 길이가 센티미터 단위의 정수로 입력된다
 * 랜선의 길이는 2^31-1보다 작거나 같은 자연수이다.
 * 
 * @출력
 * 첫째 줄에 N개를 만들 수 있는 랜선의 최대 길이를 센티미터 단위의 정수로 출력한다.
 * 
 * @해결방안
 * 받은 배열 중 최댓값을 기준값으로 따로 빼내어 N 계산
 * 계산된 값이 N보다 크다? -> 더 쪼개야한다. 기준값을 0을 향하여 반
 * 계산된 값이 N보다 작다? -> 더 늘려야한다. 기준값을 최댓값을 향하여 반
 * N개를 찾으면 기준값을 반환.
 * 
 */

public class Main {

	static long[] arr;
	static long max;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int K = Integer.parseInt(st.nextToken());
		int N = Integer.parseInt(st.nextToken());

		arr = new long[K];

		for (int i = 0; i < K; i++) {
			arr[i] = Integer.parseInt(br.readLine());
		}

		Arrays.sort(arr);

		System.out.println(binarySearch(arr, N));
	}

	public static long binarySearch(long[] arr, int findNum) {
		long start = 1;
		long end = arr[arr.length-1];
		long mid = 0;
		long _k = 0;
		
		while(start <= end) {
			mid = (start + end) / 2;
			
			// 계산하기
			_k = cal(mid);
			
			if(_k>=findNum) {
				max = mid;
				start = mid+1;
			}
			//N개 이상의 랜선을 만들 수 없는 경우
			//더 짧은 길이로 시도해보기
			else {
				end = mid-1;
			}
		}
		
		return max;
	}
	
	public static long cal(long num) {
		long sum = 0;
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i] / num;
		}
		return sum;
	}
}