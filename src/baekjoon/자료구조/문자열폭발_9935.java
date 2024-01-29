package baekjoon.자료구조;

import java.util.*;
import java.io.*;
public class 문자열폭발_9935 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String n = br.readLine();
        String m = br.readLine();

        Stack<Character> chars = new Stack<>();
        for(int i=0; i<n.length(); i++){
            chars.add(n.charAt(i));

            if(chars.size() < m.length()) {
                continue;
            }

            boolean exist = true;
            for(int j=0; j<m.length(); j++){
                if(chars.get(chars.size()-m.length()+j) != m.charAt(j)) {
                    exist = false;
                    break;
                }
            }

            if(exist) {
                for(int j=0; j<m.length(); j++){
                    chars.pop();
                }
            }
        }

        if(chars.isEmpty()) {
            System.out.println("FRULA");
        } else{
            StringBuilder sb = new StringBuilder();
            for(char ch : chars) {
                sb.append(ch);
            }
            System.out.println(sb.toString());
        }
    }
}
