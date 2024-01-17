class Solution {
    public int[] solution(int n) {
        int[] dx = { 0, 1, -1 };
		int[] dy = { 1, 0, -1 };

		// Scanner s = new Scanner(System.in);


		int max = n * (n + 1) / 2;

		int[][] arr = new int[n][n];

		int[] answer = new int[max];

		int count = 1;

		int d = 0;
		int x = 0, y = 0;
		while (count <= max) {
			// 범위 내
			if ((x >= 0 & x < n) && (y >= 0 & y < n)) {
				// 한번도 안 지난 것이라면
				if (arr[y][x] == 0) {
					arr[y][x] = count;
					count++;
				} else {
					y -= dy[d];
					x -= dx[d];
					d++;
				}
			}
			// 범위 외
			else {
				// 뒤로
				if (x >= 0 & x < n) {
					y -= dy[d];
				}
				if (y >= 0 & y < n) {
					x -= dx[d];
				}
				// 방향 바꾸기
				d++;
			}
			d = d % 3;
			x += dx[d];
			y += dy[d];
//			arr[y][x] = count;
		}
//		for (int[] i : arr)
//			System.out.println(Arrays.toString(i));
		count = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(arr[i][j] == 0)
					break;
				answer[count] = arr[i][j];
				count++;
			}
		}
		// System.out.println(Arrays.toString(answer));
        return answer;
    }
}