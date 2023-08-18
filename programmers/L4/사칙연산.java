//DP 문제
public class 사칙연산 {

    int[][][] arr = new int[2][200][200]; //범위의 최대, 최소 값 저장 배열
    int []nums;
    char[] oper;
    //초기화
    public void init(String[] arr){

        for(int i=0;i<200;i++){
            for(int j=0;j<200;j++){
                this.arr[0][i][j] = Integer.MIN_VALUE; //구간별 최대값 담는 배열
                this.arr[1][i][j] = Integer.MAX_VALUE; //구간별 최소값을 담는 배열
            }
        }

        nums = new int[arr.length/2+1];
        oper = new char[arr.length/2];
        for(int i=0;i<arr.length;i++){
            if(i%2==0) { //숫자 인경우
                nums[i/2] = Integer.valueOf(arr[i]);
            }else { //연산자 인 경우
                oper[i/2] = arr[i].charAt(0);
            }
        }
    }

    public int cal(int flag, int start, int end){
        if(start == end){
            return this.arr[flag][start][end] = nums[start];
        }

        //이미 계산된 값인 지 판별
        if(flag==0){ //최대
            if(this.arr[0][start][end]!=Integer.MIN_VALUE){
                return this.arr[0][start][end];
            }
        }else { //최소
            if(this.arr[1][start][end] != Integer.MAX_VALUE){
                return this.arr[1][start][end];
            }
        }

        int ret = (flag==0)?Integer.MIN_VALUE:Integer.MAX_VALUE;
        if(flag==0){ //최대값을 구하는 경우
            for(int mid=start; mid<end; mid++){
                if(this.oper[mid]=='-'){ //왼쪽 항이 최대 , 오른쪽 항이 최소가 되어야 되는 경우
                    ret = Math.max(ret, cal(0,start,mid) - cal(1,mid+1, end));
                }else { //왼쪽, 오른쪽 항 모두 최대가 되어야 되는 경우
                    ret = Math.max(ret, cal(0,start,mid) + cal(0,mid+1, end));
                }
            }
        }else { //최소값을 구하는 경우
            for(int mid = start; mid<end; mid++){
                if(this.oper[mid]=='-'){ //왼쪽항이 최소, 오른쪽 항이 최대가 되어야 되는 경우
                    ret = Math.min(ret, cal(1,start,mid) - cal(0,mid+1, end));
                }else { //왼쪽, 오른쪽 항 모두 최소가 되어야 되는 경우
                    ret = Math.min(ret, cal(1,start,mid) + cal(1, mid+1, end));
                }
            }
        }

        return this.arr[flag][start][end] = ret; //구간 별 가능한 최대, 최소의 값을 구해서 업데이트
    }

    public int solution(String arr[]) {

        init(arr);
        int answer = cal(0, 0, arr.length/2);

        return answer;
    }

}
