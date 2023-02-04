package L2;

import java.util.ArrayList;
import java.util.Collections;

public class DecimalFind {

    static ArrayList<Long> nums = new ArrayList<Long>();
    static ArrayList<Character> op = new ArrayList<Character>();
    static char[][]chars = {{'*', '-', '+',}, {'*', '+', '-',}, {'-', '*', '+',}, {'-', '+', '*',}, {'+', '*', '-',}, {'+', '-', '*',} };
    static ArrayList<Long> result = new ArrayList<Long>();

    public static long solution(String numbers) {
        long answer = 0L;
        String str = "";

        for(int i=0;i< numbers.length() ;i++) {
            if(numbers.charAt(i) >='0' && numbers.charAt(i) <='9') {
                str+=numbers.charAt(i);
            }
            else {
                nums.add(Long.parseLong(str));
                str="";
                op.add(numbers.charAt(i));
            }
        }
        nums.add(Long.parseLong(str));

        priorcaluatie();
        answer= Collections.max(result);
        return answer;
    }
    public static void priorcaluatie() {
        for(int i=0;i <chars.length;i ++) {
            ArrayList<Long> arr = new ArrayList<Long>(nums);
            ArrayList<Character> chl = new ArrayList<Character>(op);

            for(int j=0; j< chars[i].length; j++ ) {

                for(int k=0;k< chl.size() ;k ++ ) {
                    if(chl.get(k) == chars[i][j]){
                        long re = calu(arr.get(k), arr.get(k+1), chl.get(k));
                        arr.remove(k);
                        arr.remove(k);
                        chl.remove(k);
                        arr.add(k,re);
                        k--;
                    }
                }
            }
            result.add(Math.abs(arr.get(0)));
        }
    }
    //계산하는 함수
    public static long calu(long x, long y, char op) {
        switch(op) {
            case '+': return x+y;
            case '-' : return x-y;
            case '*' : return x*y;
        }
        return 0;
    }

    public static void main(String[] args) {
        Long result = solution("200-300-500-600*40+500+500");
        System.out.println(result);
    }
}
