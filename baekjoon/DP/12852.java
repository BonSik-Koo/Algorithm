import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//DP 문제
public class DP_12852 {

    public static void main(String []args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());


        int[]dp = new int[n+1]; //최대 n번
        int[] beforeInd = new int[n+1]; //이전 인덱스 값 저장 배열

        //DP 로직
        dp[1] = 0;
        for(int i=2;i<=n;i++){
            dp[i] = dp[i-1] + 1; // "n-1'에 해당, 이전 횟수에 +1
            beforeInd[i] = i-1;

            if(i%3==0 && dp[i] > dp[i/3] + 1){ // "n%3" 에 해당, "n-1"의 횟수보다 작은 경우
                dp[i] = dp[i/3] + 1; //횟수 증가
                beforeInd[i] = i/3;
            }

            //만약 6인 경우 2,3번째의 횟수를 모두 비교해야 하니 "if"문
            if(i%2==0 && dp[i] > dp[i/2] +1){
                dp[i] = dp[i/2] + 1;
                beforeInd[i] = i/2;
            }
        }

        //결과 출력하기
        System.out.println(dp[n]);
        int preIndex = beforeInd[n];
        System.out.print(n + " ");
        while (preIndex >= 1){
            System.out.print(preIndex + " ");
            preIndex = beforeInd[preIndex];
        }
    }
}
