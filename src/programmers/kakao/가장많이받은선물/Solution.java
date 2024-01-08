package programmers.kakao.가장많이받은선물;

import java.util.*;
class Solution {
    public int solution(String[] friends, String[] gifts) {
        Map<String, Integer> index = new HashMap<>();
        int[][] state = new int[friends.length][friends.length]; // 두 사람 간의 서로 선물을 준 개수
        int[][] giftState = new int[friends.length][2]; // 각 사람의 주고, 받은 선물 개수
        for(int i=0; i<friends.length; i++){
            index.put(friends[i], i); // 인덱스 캐시 저장
            Arrays.fill(state[i], 0);
            Arrays.fill(giftState[i], 0);
        }

        for(String gift : gifts){
            String[] temp = gift.split(" ");
            int from = index.get(temp[0]);
            int to = index.get(temp[1]);

            state[from][to] ++;
            giftState[from][0] ++; // 준 개수 늘리기
            giftState[to][1] ++; // 받은 개수 늘리기
        }

        int answer = Integer.MIN_VALUE;
        for(int i=0; i< friends.length;i ++){
            int sum = 0; // 각 사람이 다음달에 받을 선물의 개수
            for(int j=0; j<friends.length; j++){
                if(i == j){
                    continue;
                }

                int fromGift = state[i][j];
                int toGift = state[j][i];
                if(fromGift > toGift){
                    sum++;
                }else if(fromGift == toGift){
                    // 지수를 기준으로 판단
                    int fromSize = giftState[i][0] - giftState[i][1]; // 준 사람의 지수
                    int toSize = giftState[j][0] - giftState[j][1]; // 받은 사람의 지수
                    if(fromSize > toSize){
                        sum++;
                    }
                }
            }
            answer = Math.max(answer, sum);
        }

        return answer;
    }
}