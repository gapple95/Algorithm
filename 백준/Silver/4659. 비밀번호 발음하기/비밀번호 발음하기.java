import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String vowels = "aeiou"; // 모음 리스트

        while (true) {
            String password = br.readLine();
            if (password.equals("end")) break; // 종료 조건

            boolean hasVowel = false; // 모음 포함 여부
            boolean isAcceptable = true;
            int vowelCount = 0, consonantCount = 0; // 연속된 모음/자음 개수

            for (int i = 0; i < password.length(); i++) {
                char ch = password.charAt(i);

                // 1. 모음 확인
                boolean isVowel = vowels.indexOf(ch) != -1; 
                if (isVowel) hasVowel = true;

                // 2. 연속된 같은 글자 확인 (ee, oo 제외)
                if (i > 0 && ch == password.charAt(i - 1) && ch != 'e' && ch != 'o') {
                    isAcceptable = false;
                    break;
                }

                // 3. 모음/자음 연속 체크
                if (isVowel) {
                    vowelCount++;
                    consonantCount = 0;
                } else {
                    consonantCount++;
                    vowelCount = 0;
                }

                if (vowelCount >= 3 || consonantCount >= 3) {
                    isAcceptable = false;
                    break;
                }
            }

            if (!hasVowel) isAcceptable = false; // 모음이 하나도 없으면 false

            // 출력
            if (isAcceptable) {
                sb.append("<").append(password).append("> is acceptable.\n");
            } else {
                sb.append("<").append(password).append("> is not acceptable.\n");
            }
        }
        System.out.print(sb);
    }
}
