package Strings;
import java.util.*;

public class Score_Of_parenthesis {
    Stack<Character> st = new Stack<>();
    public int solution(String s) {
        int i=0;
        int score=0,res=0;
        while(i<s.length()) {
            int temp_score=0;
            while(s.charAt(i) == '(')
            {
                st.push(s.charAt(i));
                i++;
            }
            while(!st.empty() && s.charAt(i)== ')') {
                temp_score= (int) Math.pow(2, res);
                i++;
                res++;
                st.pop();
            }
            score+=temp_score;
            res=0;
        }
        return score;
    }
    public static void main(String args[]) {

        int no_of_tests;
        String input;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of tests");
        no_of_tests = sc.nextInt();
        Score_Of_parenthesis s = new Score_Of_parenthesis();
        while (no_of_tests != 0) {
            System.out.println("Enter the String");
            input = sc.next();
            System.out.println("The String after operation is"+s.solution(input));
            no_of_tests--;
        }
    }
}
