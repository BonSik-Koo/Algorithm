package programmers.L3.추석트래픽_1차;
import java.util.*;

public class Solution {
    public int solution(String[] lines) {
        List<Job> jobs = new ArrayList<>();
        for(String line : lines){
            String[] str = line.split(" ");
            long endTime = getMiliSecond(str[1]);
            long startTime = endTime -
                    (long)(Double.parseDouble(str[2].replace(str[2].charAt(str[2].length()-1), ' ')) * 1000) + 1;

            Job job = new Job(startTime, endTime);
            jobs.add(job);
        }

        int answer = 0;
        for(int i=0; i<jobs.size(); i++){
            int endResult = 1;
            int startResult = 1;
            Job job = jobs.get(i);
            for(int j=i+1; j<jobs.size(); j++){
                Job target = jobs.get(j);
                // 끝 날짜 기준
                if(job.endTime+999>=target.startTime && job.startTime<=target.endTime){
                    endResult++;
                }
                // 시작 날짜 기준
                if(job.startTime+999>=target.startTime && job.startTime<=target.endTime){
                    startResult++;
                }
            }
            answer = Math.max(answer, Math.max(endResult, startResult));
        }

        return answer;
    }

    public long getMiliSecond(String dateTime){
        String[] str = dateTime.split(":");
        long hour = Integer.parseInt(str[0]) * 60 * 60 * 1000;
        long min = Integer.parseInt(str[1]) * 60 * 1000;
        long sec = (long) (Double.parseDouble(str[2]) * 1000);
        return hour + min + sec;
    }

    static class Job{
        long startTime;
        long endTime;
        public Job(long startTime, long endTime){
            this.startTime = startTime;
            this.endTime = endTime;
        }
    }
}
