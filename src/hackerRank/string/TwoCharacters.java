package hackerRank.string;

import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result2 {

    /*
     * Complete the 'alternate' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    static int result = 0;
    public static int alternate(String s) {
        // Write your code here
        List<Character> charArr = new ArrayList<>();
        List<Character> selectArr = new ArrayList<>();

        //문자 종류 추출
        for(int i=0; i<s.length();i++){
            char c = s.charAt(i);
            if(!charArr.contains(c)){
                charArr.add(c);
            }
        }

        //로직 시작
        dfs(s, 0, charArr, selectArr);
        return result;
    }
    private static void dfs(String s, int index, List<Character> charArr, List<Character> selectArr){
        if(selectArr.size() == 2){
            result = Math.max(result, countStr(s, selectArr));
            return;
        }

        for(int i=index;i<charArr.size();i++){
            selectArr.add(charArr.get(i));
            dfs(s, i+1, charArr, selectArr);
            selectArr.remove(charArr.get(i));
        }
    }
    private static int countStr(String s, List<Character> selectArr){
        char preChar = '0';
        int count = 0;

        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);

            //특정 문자 2개를 제외한 다른 문자인 경우
            if(selectArr.contains(c)){
                if(preChar != c){ //정상적인 문자열 인 경우
                    count++;
                    preChar = c;
                }else { //정상적이지 않은 문자열 인 경우
                    return 0;
                }
            }
        }

        return count;
    }
}

public class TwoCharacters {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int l = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        int result = Result2.alternate(s);

        System.out.println(result);
//        bufferedWriter.write(String.valueOf(result));
//        bufferedWriter.newLine();
//
//        bufferedReader.close();
//        bufferedWriter.close();
    }
}
