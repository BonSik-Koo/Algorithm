package baekjoon.투포인터;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 좋다_1253 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int []arr = new int[N];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++){
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		//정렬
		Arrays.sort(arr);
		
		int result = 0;
		for(int i=0;i<N;i++){
			int left = 0;
			int right = N-1; //음수가 존재하므로 끝에서 부터 시작해야됨.
			
			while(true){
				//투 포인트가 자신 자신을 가리킬 때
				if(left == i){
					left++;
				}else if(right == i){
					right--;
				}
				
				//탈출 조건
				if(left >=right){
					break;
				}
				
				if(arr[i] > arr[left]+arr[right]){
					left++;
				}else if(arr[i] < arr[left]+arr[right]){
					right--;
				}else{
					result++;
					break;
				}
			}
		}
		
		System.out.println(result);
	}
}
