package programmers.L3;

/**
 * - 누적합 + 두포인터 문제
 * - 구현부분에서 보고 푼 문제
 */
public class 광고삽입 {

    public static void main(String[] args) {

        String playTime = "02:03:55";
        String advTime = "00:14:15";
        String[] logs = {"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"};

        String solution = solution(playTime, advTime, logs);
        System.out.println(solution);
    }

    public static String solution(String play_time, String adv_time, String[] logs) {

        int playTimeSecond = timeToSeconds(play_time);
        int advTimeSecond = timeToSeconds(adv_time);
        int[]total = new int[playTimeSecond+1];

        for(String log: logs) {
            String[] split = log.split("-");
            int startSecond = timeToSeconds(split[0]);
            int endSecond = timeToSeconds(split[1]);

            //누적합 표시 , 부등호 주위!!
            for(int i=startSecond;i<endSecond;i++)
                total[i]++;
        }

        //임시 최대값 선정
        long tempMax =0; //(최대 logs=30만개)*(최대 겹치는 초 수=100*3600+60*60+60) =>int 초과
        for(int i=0;i<advTimeSecond;i++){
            tempMax += total[i];
        }

        //투 포인터로 한칸(1초)씩 이동하며 최대값 비교
        long realMax = tempMax;
        int realStartSecond = 0;
        for(int i=1,j=advTimeSecond; j<=playTimeSecond; i++,j++){
            tempMax += total[j] - total[i-1]; //하나의 블럭을 한칸씩 shift 하는것 처럼 생각
            if(realMax < tempMax){
                realStartSecond = i;
                realMax = tempMax;
            }
        }

        String answer = secondsToTime(realStartSecond);
        return answer;
    }

    //시간을 초로 바꾸는 함수
    public static int timeToSeconds(String time) {
        String[] str = time.split(":");
        int h = Integer.parseInt(str[0]) * 3600;
        int m = Integer.parseInt(str[1]) * 60;
        int s = Integer.parseInt(str[2]);
        return h+m+s;
    }

    //초를 시간(String)으로 바꾸는 함수
    public static String secondsToTime(int second){
        StringBuilder sb = new StringBuilder();

        for(int i=2;i>=0;i--){
            int t = (int) (second/Math.pow(60,i));
            second %= (int)(Math.pow(60,i));

            if(t<10) //답의 포멧이 "05:~:~" 일때는 앞에 '0'을 붙혀주기 위해서
                sb.append(0).append(t);
            else
                sb.append(t);

            if(i!=0)
                sb.append(":"); //구분자 삽입
        }
        return sb.toString();
    }
}
