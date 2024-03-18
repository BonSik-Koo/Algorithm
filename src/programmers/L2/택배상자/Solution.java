package programmers.L2.택배상자;

import java.util.*;

class Solution {
    public int solution(int[] order) {
        int answer = 0;
        Queue<Integer> mainBelt = new LinkedList<>();
        Stack<Integer> subBelt = new Stack<>();

        for(int i=1; i<=order.length; i++) {
            mainBelt.add(i);
        }

        for(int i=0; i<order.length; i++) {
            int box = order[i];

            if(!subBelt.isEmpty() && subBelt.peek() == box) {
                subBelt.pop();
                answer++;
                continue;
            }

            while(!mainBelt.isEmpty() && mainBelt.peek() != box) {
                int poll = mainBelt.poll();
                subBelt.add(poll);
            }
            // 종료조건
            if(mainBelt.isEmpty()) {
                break;
            }
            mainBelt.poll();
            answer++;
        }

        return answer;
    }
}