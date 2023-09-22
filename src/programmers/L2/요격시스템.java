package programmers.L2;

import java.util.Arrays;

public class 요격시스템 {
	public int solution(int[][] targets) {
		//끝 시간 기준으로 오름차순
		Arrays.sort(targets, (o1, o2) -> {return o1[1]-o2[1];});
		
		int answer = 0;
		int lastEndTime = -1;
		for(int i=0; i<targets.length; i++){
			int startT = targets[i][0];
			int endT = targets[i][1];
			
			//처음인 경우
			if(lastEndTime==-1){
				answer++;
				lastEndTime = endT-1;
			}
			
			//같이 요격이 가능한 경우
			if(startT<=lastEndTime && lastEndTime<=endT){
				continue;
			}
			
			answer++;
			lastEndTime = endT-1;
		}
		
		return answer;
	}
}
