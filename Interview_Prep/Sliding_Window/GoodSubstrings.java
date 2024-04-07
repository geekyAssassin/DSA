package Sliding_Window;
import java.util.*;
class GoodSubstrings {
    public int write_solution(String s, int k){
        int count =0,i=0,n=s.length(),j=0;
        HashMap<Character,Integer>  map = new HashMap<>();
        while(j<n) {
            map.put(s.charAt(j), map.getOrDefault(s.charAt(j), 0)+1);
            if(j-i+1<k)
            j++;
            else {
                if(map.size()==k)
                count++;

                int val = map.get(s.charAt(i));
                val = val-1;
                if(val == 0)
                    map.remove(s.charAt(i));
                else 
                    map.put(s.charAt(i), val);
                i++;
                j++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        int no_of_tests;
        Scanner sc = new Scanner(System.in);
        no_of_tests = sc.nextInt();
        GoodSubstrings s = new GoodSubstrings();
        while(no_of_tests!=0){
            String input = sc.next();
            System.out.println(s.write_solution(input, 3));
            no_of_tests--;
        }
    }
}