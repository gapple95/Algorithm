import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    
    static class Nation {
        int num, gold, silver, cooper, rank;

        public Nation(int num, int gold, int silver, int cooper) {
            this.num = num;
            this.gold = gold;
            this.silver = silver;
            this.cooper = cooper;
        }

        public boolean equals(Nation o) {
            return o.gold == gold && o.silver == silver && o.cooper == cooper;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Nation> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int gold = Integer.parseInt(st.nextToken());
            int silver = Integer.parseInt(st.nextToken());
            int cooper = Integer.parseInt(st.nextToken());

            list.add(new Nation(num, gold, silver, cooper));
        }

        // 정렬 (금 > 은 > 동)
        Collections.sort(list, (o1, o2) -> {
            if(o1.gold != o2.gold) return Integer.compare(o2.gold, o1.gold);
            if(o1.silver != o2.silver) return Integer.compare(o2.silver, o1.silver);
            return Integer.compare(o2.cooper, o1.cooper);
        });

        // 랭킹 계산
        int rank = 1;
        list.get(0).rank = rank;
        int cnt = 1;

        for (int i = 1; i < N; i++) {
            if(list.get(i).equals(list.get(i-1))) {
                list.get(i).rank = rank;
                cnt++;
            } else {
                rank += cnt; // 랭킹 갱신
                list.get(i).rank = rank;
                cnt = 1;
            }
        }

        // K 국가의 랭킹 찾기
        for(Nation nation : list) {
            if(nation.num == K) {
                System.out.println(nation.rank);
                break;
            }
        }
    }
}
