package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

public class G_17609 {

    static class Word {
        String word; int index;
        public Word(String word, int index) {
            this.word = word;
            this.index = index;
        }
    }
    public static int check(String word, int j, int k) {
        while (true) {
            if(word.charAt(j) == word.charAt(k)){
                if(j+1 == k-1 || k-j==1)
                    break;
                j++; k--;
            }else {
                return 1;
            }
        }
        return 0;
    }
    public static void main(String[]args) throws IOException {
        List<Word> words = new ArrayList<>();
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(bf.readLine());
        int []result = new int[T];

        for(int i=0;i<T;i++)
            words.add(new Word(bf.readLine(), i));

        //실제 판별 로직
        for(int i=0;i<T;i++) {
            String word = words.get(i).word;
            int j=0, k=word.length()-1;
            int num =0;

            while (true) {

                if(word.charAt(j) == word.charAt(k)) {
                    if(k-j ==1 || j+1==k-1) //종료 조건
                        break;
                    j++; k--;

                }else {
                    //2두가지 경우에 대해서 check
                    int check1 = check(word, j + 1, k);
                    int check2 = check(word, j, k - 1);

                    if(check1==1 && check2==1){ //2개다 안되는 경우 -> 일반 문자열열
                       num = 2;
                        break;
                    }else { //1개라도 되는경우 -> 유사회문
                        num = 1;
                        break;
                    }
                }
            }
            result[words.get(i).index] = num;
        }

        for(int temp : result)
            System.out.println(temp);
    }
}
