package programmers.kakao.엔플러스1카드게임;

// 그리디 문제
import java.util.*;
public class Solution {
    public int solution(int coin, int[] cards) {
        List<Integer> myCards = new ArrayList<>();
        List<Integer> waitCards = new ArrayList<>();

        int N = cards.length;
        int round = 1;
        int cardIdx = N/3;

        for(int i=0; i<N/3; i++){
            myCards.add(cards[i]);
        }

        while(cardIdx < N) {
            // 각 라운드 마다 카드 2개를 뽑는다.
            waitCards.add(cards[cardIdx++]);
            waitCards.add(cards[cardIdx++]);
            boolean isSuccess = false;

            // 동전 0개를 사용해서 다음 라운드 진행
            for(Integer card : myCards){
                Integer needNum = (N+1) - card;
                if(myCards.contains(needNum)){
                    myCards.remove(card);
                    myCards.remove(needNum);
                    isSuccess = true;
                    break;
                }
            }
            if(isSuccess) {
                round++;
                continue;
            }

            // 동전 1개를 사용해서 다음 라운드 진행
            if(coin < 1){
                break;
            }
            for(Integer card : waitCards){
                Integer needCard = (N+1) - card;
                if(myCards.contains(needCard)) {
                    waitCards.remove(card);
                    myCards.remove(needCard);
                    isSuccess = true;
                    break;
                }
            }
            if(isSuccess) {
                round++;
                coin-=1;
                continue;
            }

            // 동전 2개를 사용해서 다음 라운드 진행
            if(coin < 2) {
                break;
            }
            for(Integer card : waitCards) {
                Integer needCard = (N+1) - card;
                if(waitCards.contains(needCard)) {
                    waitCards.remove(card);
                    waitCards.remove(needCard);
                    isSuccess = true;
                    break;
                }
            }
            if(isSuccess) {
                round++;
                coin-=2;
                continue;
            }

            // 모두 불가능 한 경우
            break;
        }

        return round;
    }

}
