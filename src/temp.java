import java.io.*;
import java.util.*;
public class temp {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        String[] str = input.split(" ");
        long resul1 = sol(str[0]);
        long result2 = sol(str[1]);

        System.out.println(Math.max(resul1, result2));
    }
    private static long sol(String str){
        Deque<Character> op = new LinkedList<>();
        Deque<Long> num = new LinkedList<>();

        String t_num = "";
        for(int i=0;i<str.length();i++) {
            char c = str.charAt(i);
            if (c == '-' || c == '+' || c == '*' || i == str.length() - 1) {
                if (c == '*') { //곱셈 연산자
                    String temp = "";
                    while (++i < str.length()) {
                        char tc = str.charAt(i);
                        if (tc == '-' || tc == '+' || tc == '*' || i == str.length() - 1) {
                            if(i==str.length()-1){
                                temp += tc;
                            }
                            break;
                        }else {
                            temp += tc;
                        }
                    }
                    long postN = Long.valueOf(temp);
                    t_num = String.valueOf(Long.valueOf(t_num) * postN);
                    if(i==str.length()-1) {
                        num.addLast(Long.valueOf(t_num));
                        break;
                    }
                    i-=1;

                }else if(i==str.length()-1){
                    t_num += c;
                    num.addLast(Long.valueOf(t_num));
                }
                else { //나머지 연산자
                    op.addLast(c);
                    num.addLast(Long.valueOf(t_num));
                    t_num = "";
                }
            } else {
                t_num += c;
            }
        }

        while (!op.isEmpty()){
            char ch = op.pop();
            long preN = num.pop();
            long postN = num.pop();

            num.push(calculate(ch, preN, postN));
        }
        return num.pop();
    }
    private static long calculate(char op, long n1, long n2){
        long result = 0;
        if(op=='+'){
            result = n1 + n2;
        }else if(op =='-') {
            result = n1 - n2;
        }
        return result;
    }
}
