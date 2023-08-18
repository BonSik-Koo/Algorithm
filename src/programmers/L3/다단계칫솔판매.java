package programmers.L3;

import java.util.HashMap;
import java.util.Map;

/**
 * dfs 풀이법
 */
public class 다단계칫솔판매 {

    static Map<String, String> map = new HashMap<>();
    static Map<String, Integer> pay = new HashMap<>();

    public static void divide(String name, int payPrice) {
        
        if(name.equals("-")) //center 인경우
            return;

        int main_money = (int) Math.ceil(payPrice * 0.9);
        int rest_money = payPrice - main_money;

        if( rest_money <1) { //1원 미만인 경우 이득을 분배하지 않음
            pay.replace(name, pay.get(name) + payPrice);
        }else {
            pay.replace(name, pay.get(name) + main_money);
             divide(map.get(name), rest_money);
        }
        return;
    }

    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        for(int i=0;i<enroll.length;i++) {
            map.put(enroll[i], referral[i]);
            pay.put(enroll[i], 0);
        }

        for(int i=0;i<seller.length;i++) {
            divide(seller[i], amount[i] * 100);
        }

        int count =0;
        for( String name : enroll) {
            answer[count] = pay.get(name);
            count++;
        }

        return answer;
    }

    public static void main(String[]args) {
        String []enroll ={"john", "mary", "edward", "sam", "emily", "jaimie", "tod", "young"};
        String []referral = {"-", "-", "mary", "edward", "mary", "mary", "jaimie", "edward"};

        //String []seller  = {"young", "john", "tod", "emily", "mary"};
        //int[] amount = {12, 4, 2, 5, 10};

        String []seller = {"sam", "emily", "jaimie", "edward"};
        int [] amount = {2, 3, 5, 4};

        solution(enroll, referral, seller, amount);
    }
}
