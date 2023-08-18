package SWExpertAcademyProblem.실전실습문제.수식계산기;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * - 스택 활용 문제
 * - 문제:https://swexpertacademy.com/main/talk/codeBattle/problemDetail.do?contestProbId=AYTQktlaOqMDFARs&categoryId=AYYpK-5qsTMDFARc&categoryType=BATTLE&battleMainPageIndex=1
 * - 힌트:https://swexpertacademy.com/main/talk/solvingClub/boardCommuView.do?solveclubId=AYWjN5DaiAsDFAQK&searchClsftn=AYWjN5DqiA0DFAQK&schClsName=Notice&searchCondition=COMMU_DETAIL-COMMU_TITLE-NICK_NAME_TAG&commuId=AYZtkz3Ko6EDFARc&orderBy=&searchKeyword=&sortingType=DATE_DESC&pageSize=10&pageIndex=1
 */
class UserSolution {

    class  Calculator{

        //[9]A [8]B [7]C [6]AB [5]AC [4]BC [3]AA [2]BB [1]CC [0]숫자
        Stack<int[]> data;     //변수 스택
        Stack<Character> ops;  //연산자 스택

        public Calculator() {
            data = new Stack<>();
            ops = new Stack<>();
        }

        public void push_data(int []data){
            this.data.add(data);
        }

        public void push_ops(char ops){
            this.ops.add(ops);
        }

        public Boolean priority_ops(char pre_ops, char new_ops) {
            if(pre_ops == '(')
                return true;

            if(pre_ops=='*' && new_ops=='(')
                return true;

            if(new_ops=='*' || new_ops=='(')
                return true;

            if(new_ops =='+' || new_ops=='-')
                return false;

            return false;
        }

        public void calculate() {
            char ops = this.ops.pop();

            int[] data1 = data.pop();
            int[] data2 = data.pop();
            int []result = new int[10];

            if(ops == '+'){
                for(int i=0;i<10;i++){
                    result[i] = data1[i]+data2[i];
                }
            }
            else if(ops=='-'){
                for(int i=0;i<10;i++){
                    result[i] = data2[i]-data1[i];
                }
            }
            else if(ops=='*'){

                //A
                result[9] = data1[9]*data2[0]+data2[9]*data1[0];
                //B
                result[8] = data1[8]*data2[0]+data2[8]*data1[0];
                //C
                result[7] = data1[7]*data2[0]+data2[7]*data1[0];
                //AB
                result[6] = data1[9]*data2[8] + data1[8]*data2[9];
                //AC
                result[5] = data1[9]*data2[7]+data1[7]*data2[9];
                //BC
                result[4] = data1[8]*data2[7]+data1[7]*data2[8];
                //AA
                result[3] = data1[9]*data2[9];
                //BB
                result[2] = data1[8]*data2[8];
                //CC
                result[1] = data1[7]*data2[7];
                //숫자
                result[0] = data1[0]*data2[0];
            }

            this.data.add(result);
        }
    }

    Calculator calculator;
    Map<String, Integer> count; //같은 수식 체크를 위한 map
    Map<Integer, int[]> storage;  //수식 저장소

    void init() {
        calculator = new Calculator();
        count = new HashMap<>();
        storage = new HashMap<>();
    }

    int addExpression(int mID, char mExpression[]) {

        for(int i=0;i<mExpression.length;i++){ //마지막에 개행이니
            char c = mExpression[i];
            if(c == '\0') {
                break;
            }
            int [] temp = new int[10];
            Arrays.fill(temp,0);

            //숫자나 변수일 경우 -> 그냥 push
            if('0' <= c && c<='9') {
                int num = Character.getNumericValue(mExpression[i]);
                temp[0] = num;
                calculator.push_data(temp);
                continue;
            }
            if('A' <= c && c<='C'){
                int index = 'C'-c+7;
                temp[index] = 1;
                calculator.push_data(temp);
                continue;
            }

            //연산자 일경우
            if(calculator.ops.isEmpty()){ //스택에 아무 연산자도 없을 경우
                calculator.push_ops(c);
            }
            else {
                //')' 연산자 일 경우
                if(c==')'){
                    while (calculator.ops.peek()!='('){
                        calculator.calculate();
                    }
                    calculator.ops.pop(); //'(' 연산자 제거
                    continue;
                }

                //그외 연산자 일 경우
                //스택의 마지막 연산자와 현재 연산자의 우선순위 비교
                if(calculator.priority_ops(calculator.ops.peek(), c)){
                    calculator.push_ops(c);
                }else {
                    calculator.calculate(); //그 전 항 계산
                    i--; //다시 해당 연산자부터 시작
                }
            }
        }

        //남은거 계산
        while (!calculator.ops.isEmpty())
            calculator.calculate();

        //마지막 계산 결과 값
        int answer =0;
        int[] result = calculator.data.pop();

        //마지막 계산 배열로 key 만들기
        String key = "";
        for(int i=9;i>=0;i--)
            key+=String.valueOf(result[i]);

        //map 에 있는지 판별
        if(count.get(key)!=null){              //동일한 수식이 존재할 경우
            answer = count.get(key);             //동일한 수식 개수 리턴
            count.replace(key, answer+1); //동일한 수식이니 개수 업데이트

        }else {                                     //동일한 수식이 없는 경우
            //진짜 변수 개수 새기
            if(result[9]!=0 || result[6]!=0 || result[5]!=0 || result[3]!=0) //A가 존재
                answer-=1;
            if(result[8]!=0 || result[6]!=0 || result[4]!=0 || result[2]!=0) //B가 존재
                answer-=1;
            if(result[7]!=0 || result[5]!=0 || result[4]!=0 || result[1]!=0) //C가 존재
                answer-=1;

            count.put(key, 1);
        }

        storage.put(mID, result);
        return answer;
    }

    int calcExpression(int mID, int mA, int mB, int mC) {

        int answer = 0;
        int[] result = storage.get(mID);

        answer+=(result[9]*mA);
        answer+=(result[8]*mB);
        answer+=(result[7]*mC);
        answer+=(result[6]*mA*mB);
        answer+=(result[5]*mA*mC);
        answer+=(result[4]*mB*mC);
        answer+=(result[3]*mA*mA);
        answer+=(result[2]*mB*mB);
        answer+=(result[1]*mC*mC);
        answer+=result[0];

        return answer;
    }
}