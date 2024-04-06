package BackTracking;

import java.util.*;

public class Letter_Combinations_Phone {
    String[] phone_map = {"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> result = new ArrayList<>();
    private void solution(String input) {
        helper(result, new StringBuilder(),input, 0);
        for (String string : result) {
            System.out.println(string);            
        }
    }

    private void helper(List<String> result, StringBuilder resString, String input, int currIndex) {
        if(currIndex == input.length())
        {
            result.add(resString.toString());
            return;
        }
        String letters = phone_map[input.charAt(currIndex)-'2'];
        for(char c : letters.toCharArray()) {
            resString.append(c);
            helper(result, resString, input, currIndex+1);
            resString.deleteCharAt(resString.length()-1);
        }
    }

    public static void main(String args[]) {

        int no_of_tests;
        String input;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of tests");
        no_of_tests = sc.nextInt();
        Letter_Combinations_Phone s = new Letter_Combinations_Phone();
        while (no_of_tests != 0) {
            System.out.println("Enter the String");
            input = sc.next();
            s.solution(input);
            no_of_tests--;
        }
    }
}
