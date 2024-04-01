package BackTracking;

import java.util.*;

class PalindromePartitioning {
    List<List<String>> result = new ArrayList<>();
    public void solution(String input) {
        helper(result,new ArrayList<>(), input, 0);
    }

    public void helper(List<List<String>> result, List<String> tmplist, String input, int start) {
        if(!tmplist.isEmpty()) {
            
        }
    }
    
    private void print() {
        for(List<String> row : result) {
            for (String element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }


    public static void main(String args[]) {

        int no_of_tests;
        String input;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of tests");
        no_of_tests = sc.nextInt();
        PalindromePartitioning s = new PalindromePartitioning();
        while (no_of_tests != 0) {
            System.out.println("Enter the String");
            input = sc.next();
            s.solution(input);
            System.out.println("The String after operation is"+input);
            no_of_tests--;
        }
    }
}
