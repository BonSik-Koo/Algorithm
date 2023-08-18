import java.util.Arrays;

/**
 * greedy 풀이
 * - 큰수부터 시작하게 되면 반드시 answer++ 이 일어남
 * - 인덱스를 양쪽에서 사용하기
 */
public class 구명보트 {

    public int solution(int[] people, int limit) {
        int answer = 0;

        Arrays.sort(people);
        int minIndex =0;
        for(int maxIndex=people.length-1;minIndex<=maxIndex;maxIndex--) {
            if(people[maxIndex] + people[minIndex] <= limit)
                minIndex++;
            answer++;
        }

        return answer;
    }

    public void 테스트() {

    }
}
