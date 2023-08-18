package programmers.L2;

public class L2_StringZip {
    public static int solution(String s) {
        int answer = s.length();

        for(int i=1;i<=s.length() ;i++) {
            String result = "";
            String[]split = new String[s.length()];
            int count =0;
            int k=0;
            int zip_count=1;
            String temp;

            for(int j=0;j<s.length();j+=i) {
                if(j+i<=s.length())  {
                    split[count] =s.substring(j,j+i);
                    count++;
                }
                else {
                    split[count] = s.substring(j, s.length());
                    count++;
                    break;
                }
            }

            for(k =0;k<count-1;k++) {
                if( split[k].equals(split[k+1])) {
                    zip_count++;
                }
                else {
                    if(zip_count==1)
                        result=result+split[k];
                    else
                        result=result+zip_count+split[k];

                    zip_count=1;
                }
            }
            if((zip_count-1)!=0) {
                result = result + (zip_count - 1) + split[k];
            }
            else
                result = result +split[k];

            if(answer>result.length()) {
                answer = result.length();
            }
            result ="";
            count=0;
            zip_count=1;
        }

        return answer;
    }

    public static void main(String[] args) {

        int an =solution("aabbaccc");
        System.out.println(an);
    }
}
