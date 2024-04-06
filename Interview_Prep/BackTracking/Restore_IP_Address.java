package BackTracking;

import java.util.*;

public class Restore_IP_Address {
    List<String> result = new ArrayList<>();
    public void solution(String input) {
        List<String> temp = new ArrayList<>();
        helper(result,input,temp,0);
    }
    private void helper(List<String> result, String input, List<String> temp , int start) {
        if(temp.size()==4) {
            if(input.length()==start)
                result.add(String.join(".", temp));
            return;
        }
        for(int j=start+1; j<= start+3 && j<=input.length(); j++) {
            String t = input.substring(start, j);
            if(Integer.parseInt(t) < 256 && (t.equals("0") || !t.startsWith("0"))) {
                temp.add(t);
                helper(result, input, temp, j);
                temp.remove(temp.size()-1);
            }
        }
    }
    private void print() {
        for(String temp : result) {
            System.out.println(temp);
        }
    }
    public static void main(String args[]) {

        int no_of_tests;
        String input;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of tests");
        no_of_tests = sc.nextInt();
        Restore_IP_Address s = new Restore_IP_Address();
        while (no_of_tests != 0) {
            System.out.println("Enter the String");
            input = sc.next();
            s.solution(input);
            System.out.println("The String after operation is");
            s.print();
            no_of_tests--;
        }
    }
}
