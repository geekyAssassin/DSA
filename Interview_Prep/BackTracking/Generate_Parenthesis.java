package BackTracking;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Generate_Parenthesis {
    List<String> result = new ArrayList<>();

    public void solution(int n) {
        helper(result, "", n, 0, 0);
        for (String string : result) {
            System.out.println(string);
        }
    }

    private void helper(List<String> result, String current, int n, int open, int close) {
        if(current.length() == n*2)
        {
            result.add(current);
            return;
        }
        if(open< n)
        helper(result, current+"(", n, open+1, close);
        if(close< open)
        helper(result, current+")", n, open, close+1);
    }

    public static void main(String args[]) {

        int no_of_tests;
        int input;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of tests");
        no_of_tests = sc.nextInt();
        Generate_Parenthesis s = new Generate_Parenthesis();
        while (no_of_tests != 0) {
            System.out.println("Enter the String");
            input = sc.nextInt();
            s.solution(input);
            no_of_tests--;
        }
    }
}
