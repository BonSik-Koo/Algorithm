import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * - greedy 풀기!!
 * - bfs 풀이 => 끝까지 풀이유가 없으니 최소한으로 되는 경우를 찾으면 됌 => but 이 풀이는 "메모리 초과"!!!!!!
 * - 다시풀기 : 정해진 규칙이 있으니 거꾸로 풀기
 */
public class G_12904 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringBuffer s = new StringBuffer(bf.readLine());
        StringBuffer t = new StringBuffer(bf.readLine());

        boolean check = false;
        while(s.length() <= t.length()) {

            if(t.charAt(t.length()-1) == 'A') {
                t.deleteCharAt(t.length()-1);
            }else {
                t.deleteCharAt(t.length()-1);
                t.reverse();
            }

            if(t.toString().equals(s.toString())){
                check = true;
                break;
            }
        }

        if(check == true)
            System.out.println(1);
        else
            System.out.println(0);
    }
}
