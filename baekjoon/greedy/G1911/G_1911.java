package baekjoon.greedy.G1911;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * !주위!
 * 흙길 좌표 인덱스를 배열로 사용하게 되면 10억개기 때문에 메모리 초과발생
 */
public class G_1911 {
    public static class dump implements Comparable<dump> {
        int start; int end;
        public dump(int start, int end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public int compareTo(dump o) {
            if(this.start == o.start)
                return this.end - o.end;
            return this.start - o.start;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());
        int num = Integer.parseInt(st.nextToken());
        int length = Integer.parseInt(st.nextToken());
        List<dump> list = new ArrayList<>();
        int result =0;

        for(int i=0;i<num;i++) {
            st = new StringTokenizer(bf.readLine());
            list.add(new dump(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list); //start 오름차순, 같은 start 는 end 오름차순

        int priorEnd = list.get(0).end;
        int priorStart =list.get(0).start;
        int remainLength=0;
        for(int i=1;i<num;i++){
            dump dump = list.get(i);
            if(dump.start < priorEnd) {
                priorEnd = dump.end;
                continue;
            }else{
                result += ((priorEnd-priorStart)%length > 0 ? (priorEnd-priorStart)/length+1 : (priorEnd-priorStart)/length);
                remainLength = (length* ((priorEnd-priorStart)%length > 0 ? (priorEnd-priorStart)/length+1 : (priorEnd-priorStart)/length)) - (priorEnd-priorStart);
                if(dump.start > priorEnd-1 + remainLength) {
                    priorStart = dump.start;
                }else{
                    priorStart = priorEnd + remainLength;
                }
            }
            priorEnd = dump.end;
        }

        result += ((priorEnd-priorStart)%length > 0 ? (priorEnd-priorStart)/length+1 : (priorEnd-priorStart)/length); //마지막꺼 계산
        System.out.println(result);
    }
}
