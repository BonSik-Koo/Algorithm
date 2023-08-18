package programmers.L2;

import java.util.*;

public class Tuple {

    public static List<Integer> solution(String s) {
        Map<Integer, String> map = new HashMap<>();
        String str ="";
        for(int i=0 ;i<s.length() ;i++ ) {
            if(s.charAt(i)>='0' && s.charAt(i) <='9') {
                str+=s.charAt(i);
            }
            else if(s.charAt(i) ==',')
            {
                if(str.length()!=0) {
                    str+=s.charAt(i);
                }
            }
            else { // "{", "}"
                if(str.length()!=0) {
                    map.put(str.length() , str);
                    str="";
                }
            }
        }

        List<Integer> arr = new ArrayList<>();
        for (int y: map.keySet()) {
            String s1 = map.get(y);
            String[] split = s1.split(",");
            for(int j=0; j<split.length ; j++){
                int temp = Integer.parseInt(split[j]);
                Optional<Integer> any = arr.stream()
                        .filter(a -> (temp == a))
                        .findAny();
                if(any.isEmpty()){
                    arr.add(temp);
                }
            }
        }

        String tem = arr.toString();
        System.out.println(tem);
        return arr;
    }

    public static void main(String[] args) {
        String temp =  "{{4,2,3},{3},{2,3,4,1},{2,3},{2,3,4,1,100}}";
        solution(temp);
    }

}
