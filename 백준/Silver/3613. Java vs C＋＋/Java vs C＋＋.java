import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * 백준 3613 Java vs C++
 * 
 * 메모리 
 * 시간 
 * 
 * 자바에서 변수명은 첫단어는 소문자로 쓰고, 다음 단어부터는 첫 문자만 대문자로 쓴다.
 * 또, 모든 단어는 붙여쓴다.
 * ex) javaIdentifier, longAndMnemonicIdentifier, name, bAEKJOON
 * 
 * C++에서는 변수명에 소문자만 사용한다. 단어와 단어를 구분하기 위해서 밑줄(_)을 이용한다.
 * ex) c_identifier, long_and_mnemonic_identifier, name, b_a_e_k_j_o_o_n
 * 
 * C++ 형식의 변수명을 Java형식의 변수명으로, 또는 그 반대로 바꿔주는 프로그램을 만들려고 한다.
 * 
 * 프로그램은 먼저 변수명을 입력으로 받은 뒤, 이 변수명이 어떤 언어 형식인지를 알아내야한다.
 * 그다음, C++형식이면 Java, Java면 C++로 바꾸면 된다.
 * 만약 둘다 아니면 에러를 발생시킨다.
 * 
 * @입력
 * 첫째 줄에 변수명이 주어진다. 영어 알파벳과 밑줄('_')로만 이루어져 있고, 길이는 100을
 * 넘지 않는다.
 * 
 * @출력
 * 입력으로 주어진 변수명이 Java면 C++, C++이면 Java 둘다 아니면 "Error!"를 출력
 * 
 * @해결방안
 * 자바, 씨플플 둘다 소문자가 가능하다.
 * 구분은 대문자이냐, _이냐 정도.
 * 둘다 사용하면 error라고 생각해야할듯.
 * 
 * 1. 전체를 순회하면서 _를 만나면 boolean cpp true, 대문자를 만나면 boolean java를
 *    true로 지정한다. 둘다 true이면 error를 쓰기.
 *    대문자는 'A'보다 같거나 큰걸로 구분
 * 2. 형식 지정
 *    모두 똑같이 문자열을 순회하고 로직을 다르게한다.
 * 		2-1. C++일 경우 :
 * 			_을 발견하면 뺀다.
 * 			_다음으로 오는 단어는 대문자로 바꿔주기
 * 		2-2. Java인 경우 :
 * 			대문자를 만나면 뺀다.
 * 			대문자를 소문자로 만들고 _를 접두사로 붙여준다.
 * 
 * 생각보다 안되는 경우가 많다..
 * 1. 첫문자가 _이거나 대문자
 * 		_asdasd asdasd_ =>err
 * 2. _와 대문자 혼재
 * 		asd_asAs_as => err
 * 3. 마지막 글자가 _인 경우
 * 		asdasd_ => err
 * 4. 오로지 소문자로만 이루어진 경우 => 고대로
 * 		apple => apple
 * 5. _가 연속으로 나오는 경우
 * 		asdasd__asdasd => err
 * 6. 글자가 100 이상인 경우
 * 
 * asd_D => asd$로 나오는 반례 발견
 * 
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		String str = br.readLine();

		boolean cpp = false, java = false;
		if(str.charAt(0) == '_' || str.charAt(str.length() - 1) == '_' || (str.charAt(0) >= 'A' && str.charAt(0) <= 'Z' ) || str.length() > 100) {
			System.out.println("Error!");
			System.exit(0);
		}

		for (int i = 0; i < str.length(); i++) {
			char chr = str.charAt(i);
			// C++이면?
			if (chr == '_') {
				cpp = true;
				if(str.charAt(i+1) == '_' || (str.charAt(i+1) >= 'A' && str.charAt(i+1) <= 'Z')) {
					System.out.println("Error!");
					System.exit(0);
				}
					
				sb.append((char)(str.charAt(i + 1) - 'a' + 'A'));
				i++;
			}
			else if(chr >= 'A' && chr <= 'Z') {
				java = true;
				sb.append('_').append((char)(chr - 'A' + 'a'));
			}
			else {
				sb.append(chr);
			}
			if(cpp && java) {
				System.out.println("Error!");
				System.exit(0);
			}
				
		}
		
		System.out.println(sb);
	}

}