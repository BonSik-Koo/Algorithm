package baekjoon.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * !다시 풀 문제!
 * - greedy -> 이분탐색
 * - 통나무의 길이를 이분탐색으로 정하는것 -> 통나무의 길이가 늘어났다가 줄어들었다가를 반복됌
 * - 통나무의 길이를 1씩 늘려 테스트를 하면 시간 초과
 * - 작은 위치를 출력시켜야 하기 때문에 뒤에서 부터 탐색 시작!
 */
public class G_1114 {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        Long l = Long.parseLong(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        List<Long> pos = new ArrayList<>();
        pos.add(0L); pos.add(l);
        st = new StringTokenizer(bf.readLine());
        for(int i=0;i<k;i++)
            pos.add(Long.parseLong(st.nextToken()));
        Collections.sort(pos); //뒤에서 부터 짜르기 위해

        long left = 0;
        long right = l;
        long firstCutPos = 0;
        long MaxLength = l;
        while(left<=right) {
            long midLength = left + (right- left)/2;
            long cutCount =0;
            long diff = 0;
            long temp_firstCutPos = -1;
            long temp = 0;

            for(int i=k;i>=0;i--) { //짜를수 있는 위치를 반복 탐색
                diff += pos.get(i+1) - pos.get(i);
                if(diff > midLength) { //짤라야 되는 시점, 기준 길이보다 넘는 시점의 앞부분을 커트
                    cutCount++;
                    diff = pos.get(i+1) - pos.get(i);
                    temp_firstCutPos = pos.get(i+1);
                    if(diff > midLength) { //만약 기준 길이로 만들수 없는 경우 -> 기준 길이를 늘려야 된다.
                        cutCount = c + 1;
                        break;
                    }
                }
            }

            if(cutCount < c) { //더 짜를수 있는 횟수가 있는 경우
                temp = pos.get(1);
            }else {
                temp = temp_firstCutPos;
            }

            if(cutCount <= c) { //기준길이로 모두 만들었고 기준길이를 줄여야 되는경우
                firstCutPos = temp;
                MaxLength = Math.min(midLength, MaxLength);
                right = midLength -1;
            }else { //기준 길이를 늘려야 되는경우(짜르는 개수가 초과된 경우 또는 기준 길이로 만들수 없는 경우)
                left = midLength + 1;
            }
        }
        System.out.println(MaxLength + " " + firstCutPos);
    }

}
