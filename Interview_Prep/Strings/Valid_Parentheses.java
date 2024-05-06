package Strings;

import java.util.*;

public class Valid_Parentheses {
    Stack<Character> p = new Stack<>();
    public boolean solution(String s) {
        if (s.length() % 2 != 0)
            return false;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '(' || s.charAt(i) == '{' || s.charAt(i) == '[')
                p.push(s.charAt(i));
            else {
                if(s.charAt(i) == ')' && p.pop() == '(' )
                    continue;
                else if(s.charAt(i) == '}' && p.pop() == '{' )
                    continue;
                else if(s.charAt(i) == ']' && p.pop() == '[' )
                    continue;
                else
                    return false;
            }

        }
        return true;
    }

    public static void main(String args[]) {

        int no_of_tests;
        String input;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of tests");
        no_of_tests = sc.nextInt();
        Valid_Parentheses s = new Valid_Parentheses();
        while (no_of_tests != 0) {
            System.out.println("Enter the String");
            input = sc.next();
            System.out.println("The String after operation is"+s.solution(input));
            no_of_tests--;
        }
    }
}