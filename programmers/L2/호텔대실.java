package programmers.L2;

import java.util.Arrays;

/**
 * - 누적합(범위, 구간을 다루는 문제이면!)
 * - 보고 푼 문제
 */
public class 호텔대실 {

    private static final int maxSize = 24 * 60 + 60;
    private static final int clean = 10;

    public int solution(String[][] book_time) {

        int []room = new int[maxSize];
        Arrays.fill(room,0);

        for(int i=0;i<book_time.length;i++){
            int start = convertToMinute(book_time[i][0]);
            int end = convertToMinute(book_time[i][1])+clean;

            room[start] += 1; room[end] += -1;
        }

        int answer = 0;
        for(int i=1;i<maxSize;i++){
            room[i] += room[i-1];
            answer = Math.max(answer, room[i]);
        }
        return answer;
    }

    public static int convertToMinute(String time){ //분으로 변환

        String[] split = time.split(":");
        int hour = Integer.parseInt(split[0]);
        int minute = Integer.parseInt(split[1]);
        return hour*60 + minute;
    }
}
