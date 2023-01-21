package programmers.L3.코딩테스트공부패키지;

import java.util.Arrays;

/**
 * DP(동적계획법) 풀이!!
 * 다시 풀 문제!!!
 */
public class DP_코딩테스트공부 {

    public static int solution(int alp, int cop, int[][] problems) {
        int answer = 0;

        int alpMax=0, copMax=0;
        for(int i=0;i<problems.length;i++){
            alpMax=Math.max(alpMax, problems[i][0]);
            copMax=Math.max(copMax, problems[i][1]);
        }
        int[][]field = new int[alpMax+2][copMax+2]; //alpMax,copMax 값을 맞추기 위해 && 알고력,코딩력 상승시 인덱스 에러를 피하기 위해
        for(int i=0;i< field.length;i++)
            Arrays.fill(field[i], Integer.MAX_VALUE);

        //초기상태에 문제를 다풀수있는 경우
        if(alp>=alpMax && cop>=copMax)
            return 0;

        //예외 경우 처리
        if(alp>alpMax)
            alp = alpMax;
        if(cop>copMax)
            cop = copMax;

        field[alp][cop]=0;
        for(int i=alp;i<=alpMax;i++){ //현재 알고력
            for(int j=cop;j<=copMax;j++){ //현재 코딩력

                //알고력 상승
                field[i+1][j] = Math.min(field[i+1][j], field[i][j]+1);

                //코딩력 상승
                field[i][j+1] = Math.min(field[i][j+1], field[i][j]+1);

                for(int[]problem: problems){

                    //문제를 풀수 없는 경우
                    if( i<problem[0] || j<problem[1])
                        continue;

                    if(i+problem[2]>alpMax && j+problem[3]>copMax) {
                        field[alpMax][copMax] = Math.min(field[alpMax][copMax], field[i][j]+problem[4]);
                    }else if(i+problem[2]>alpMax) {
                        field[alpMax][j+problem[3]] = Math.min(field[alpMax][j+problem[3]], field[i][j]+problem[4]);
                    }else if(j+problem[3]>copMax){
                        field[i+problem[2]][copMax] = Math.min(field[i+problem[2]][copMax], field[i][j]+problem[4]);
                    }else {
                        field[i + problem[2]][j + problem[3]] = Math.min(field[i + problem[2]][j + problem[3]], field[i][j] + problem[4]);
                    }
                }

            }
        }
        answer = field[alpMax][copMax];
        return answer;
    }

    public static void main(String[] args) {

        int alp = 10;
        int cop = 10;
        int[][] problems = {{10,15,2,1,2},{20,20,3,3,4}};
        int solution = solution(alp, cop, problems);
        System.out.println(solution);
    }
}
