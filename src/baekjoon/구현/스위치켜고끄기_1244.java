package baekjoon.구현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 스위치켜고끄기_1244 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n+1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=1;i<=n;i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int p = Integer.parseInt(br.readLine());
		for(int i=0;i<p;i++){
			st = new StringTokenizer(br.readLine());
			int sex = Integer.parseInt(st.nextToken());
			int number = Integer.parseInt(st.nextToken());
			
			if(sex==1){
				//초기
				int seq = 1;
				int value = number * seq;
				while(value<=n){
					arr[value] = (arr[value]==1)?0:1;
					seq++;
					value = number * seq;
				}
			}else{
				int left = number-1;
				int right = number+1;
				while(left>=1 && right<=n){
					if(arr[left] == arr[right]){
						arr[left] = (arr[left]==1)?0:1;
						arr[right] = (arr[right]==1)?0:1;
					}else{
						break;
					}
					left--;
					right++;
				}
				arr[number] = (arr[number]==1)?0:1;
			}
			
		}
		
		// 출력
		for(int i=1;i<=n;i++){
			System.out.print(arr[i]);
			if(i%20==0){
				System.out.println();
			}else{
				System.out.print(" ");
			}
		}
	}
}
