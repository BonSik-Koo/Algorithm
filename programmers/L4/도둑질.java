//DP 문제
class Solution {
    public void dp(int[]dp, int[]money, int start, int end){
        for(int i=start; i<end; i++){
            dp[i] = Math.max(dp[i-1], dp[i-2]+money[i]);
        }
    }
    public int solution(int[] money) {
        
        //초기화
        int[] dp1; //첫 집 도둑질 하지 않은 경우
        int[] dp2; //첫 집을 도둑질 한 경우
        dp1 = new int[money.length];
        dp2 = new int[money.length];
        dp1[0] = 0; dp1[1] = money[1];
        dp2[0] = money[0]; dp2[1] = money[0];
        
        //dp 시작
        dp(dp1, money, 2, money.length);
        dp(dp2, money, 2, money.length-1);
        
        //결과 찾기
        int answer = Math.max(dp1[money.length-1], dp2[money.length-2]);
        return answer;
    }
}
