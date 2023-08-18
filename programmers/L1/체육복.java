import java.util.Arrays;

public class 체육복 {

    public static void main(String[] args) {
        int []lost = {4,2};
        int []reserve = {3,5};
        int result = solution(5,lost, reserve );
        System.out.println(result);
    }

    public static int solution(int n, int[] lost, int[] reserve) {
        int answer = 0;

        int[]array = new int[n+1];
        Arrays.fill(array,1);

        for(int index : lost)
            array[index]-=1;
        for(int index : reserve)
            array[index]+=1;

        Arrays.sort(lost); //이게 중요!!!
        for(int index : lost) {
            if(array[index]==0){ //대여 받아야 되는 학생
                if(index-1>0 && array[index-1]>=2){ //왼쪽 학생에게 대여
                    array[index-1]-=1;
                    array[index]+=1;
                }else if(index+1<=n && array[index+1]>=2) {//오른쪽 학생에게 대여
                    array[index+1]-=1;
                    array[index]+=1;
                }
            }
        }
        for(int i=1;i<=n;i++){
            if(array[i]>0)
                answer++;
        }
        return answer;
    }
}
