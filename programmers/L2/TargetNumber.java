package L2;

import java.util.Vector;

public class TargetNumber {

    public int answer=0;
    public int solution(int[] numbers, int target) {
        dfs(numbers, 0,target, 0);

        return answer;
    }

    public void dfs(int []number, int depth, int target, int sum) {
        if(number.length == depth){
            if(target == sum)
                answer++;
        }
        else{
            dfs(number, depth+1, target, sum +number[depth]);
            dfs(number, depth+1, target, sum -number[depth]);
        }
    }

}
