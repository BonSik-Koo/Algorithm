package baekjoon.greedy.G18185;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * !hard!
 *
 * <풀이법>
 * - 배열의 인덱스를 for 문을 통해 아래 조건을 반복한다. (결과적으로 방문한 인덱스 위치의 값은 0으로 만들어주어야 된다.)
 * - 또한 "num-1"인덱스 즉 젤 마지막 인덱스도 아래와 같은 조건의 반복을 만족하기위해서 두개의 인덱스를 추가해준다.
 *
 * 조건1) i번 인덱스의 값이 있을때 "i+1>i+2"처럼 인덱스의 값이 성립할때는 "i+1,i+2"인덱스의 값을 같게 해주어 "방법3"으로 계산하는것이 최소
 *   => 왜냐하면 3개를 동시에 처리하는경우가 최소가 되기때문에
 *   => "i+1,i+2" 인덱스 위치의 값차이 만큼 "방법2"통해 계산 해주는데 "i"번째 값이 앞의 차이보다 작을수 있으니 "i,|i+1 - i+2|"의 최소값으로 계산해준다.
 *
 * 조건2) i번 인덱스의 값이 있을때 "i+1<=i+2" 처럼 인덱스 값이 성립할때는 "방법3"으로 계산하면 된다.
 *   => "i,i+1,i+2" 인덱스 위치의 값의 최소값으로 계산해준다.
 *
 * 조건3) "i"인덱스위치의 값에 대해서 위 조건을 통해 계산 되었을때 "i"번째 인덱스 위치의 값이 "i+1,i+2"이보다 크면 값이 남아있기 때문에 남은 "i"인덱스의 값을 "방법1"로 계산해주어야된다.
 *
 */

public class G_18185 {

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(bf.readLine());
        int[] array = new int[num+2];
        int result = 0;
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for(int i=0;i<num;i++) {
            array[i] = Integer.parseInt(st.nextToken());
        }
        array[array.length-2]=0; array[array.length-1]=0;

        for(int i=0;i<num;i++) {
            int min =0;

            if(array[i+1] > array[i+2]) { //i+1이랑 i+2를 맞춰서 계산
                min = Math.min(array[i], Math.abs(array[i+1]-array[i+2]));
                array[i]-=min; array[i+1]-=min;
                result+=5*min;

                min = Math.min(array[i],array[i+1]); //3개 항목 모두 기준값으로 빼주기
                min = Math.min(min, array[i+1]);
                array[i]-=min; array[i+1]-=min; array[i+2]-=min;
                result+=7*min;

            }else { //array[i+1]<=array[i+2]
                min = Math.min(array[i], array[i+1]);
                min = Math.min(min, array[i+2]);
                array[i]-=min; array[i+1]-=min; array[i+2]-=min;
                result+=7*min;
            }

            min=array[i];
            result+=3*min;
            array[i]-=min;
        }

        System.out.println(result);
    }
}
