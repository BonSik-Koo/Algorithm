package programmers.L2;

import java.util.Arrays;

/**
 * - DFS 풀이
 * - 혼자 푼 문제
 */

public class 양궁대회 {

    static public int[] answer;
    static public int score = 1;
    static public int[] Info;
    static public boolean check =false;
    public static int[] solution(int n, int[] info) {

        Info = info;
        answer = new int[11];
        int []current = new int[11];
        Arrays.fill(answer,0); Arrays.fill(current,0);
        int temp =0;
        for(int i=0;i<11;i++){
            if(info[i]!=0)
                temp += 10-i;
        }
        dfs(0,n,0,temp,current);
        if(check == false){ //라이언이 이길수 없는 경우
            answer = new int[1];
            answer[0] = -1;
        }
        return answer;
    }

    //count : 라이언의 남은 화살 개수
    public static void dfs(int index, int count, int lionScore, int ApitchScore, int[]current) {

        if(count==0 && (score <=lionScore-ApitchScore) ){ //답이 될수 있는 경우
            check = true; //최소 하나라도 답이 있었다는 표시
            if(score <lionScore-ApitchScore) { //새롭게 라이언이 이기는 경우
                score = lionScore-ApitchScore;
                answer = current.clone(); //깊은 복사
            }else { //이전과 점수가 동일한 경우 -> 작은 과녁점수가 많은 것이 선택됌
                for(int i=10;i>=0;i--) {
                    if(answer[i] > current[i]) //기존에 답이 채택
                        break;
                    else if(answer[i] < current[i]) {
                        answer = current.clone(); //깊은 복사
                        break;
                    }
                }
            }
            return;
        }else if(count ==0 && lionScore-ApitchScore <=0)
            return;

        for(int i=index;i<11;i++) {
            int ApitchN = Info[i]; //특정 과녁점수의 아파치 화살 개수

            int newCount =ApitchN+1;
            int plus = 10-i;
            int sub = 10-i;
            if(Info[i]==0)
                sub = 0;
            if(count-ApitchN<=0) { //라이언이 점수를 얻을수 없는 과녁점수
                plus = 0;
                sub =0;
                newCount = count;
            }

            current[i] = newCount;
            lionScore+=plus;
            ApitchScore -=sub;
            count = count-newCount; //라이언의 남은 화살 개수
            dfs(i+1, count, lionScore, ApitchScore, current);
            current[i] = 0;
            lionScore-=plus;
            ApitchScore +=sub;
            count = count+newCount;
        }
    }

    public static void main(String[] args) {
        int n= 1;
        //int []info = {2,1,1,1,0,0,0,0,0,0,0};
        int [] info = {1,0,0,0,0,0,0,0,0,0,0};
        //int []info = 	{0,0,1,2,0,1,1,1,1,1,1};
        //int []info = {0,0,0,0,0,0,0,0,3,4,3};

        int[] solution = solution(n, info);
        System.out.println(Arrays.toString(solution));
    }
}
