package BackTracking;

import java.util.*;

class PalindromePartitioning {
    List<List<String>> result = new ArrayList<>();

    public void solution(String input) {
        helper(result, new ArrayList<>(), input);
    }

    private void helper(List<List<String>> result, List<String> tmplist, String input) {
        if (input == null || input.length() == 0) {
            result.add(new ArrayList<>(tmplist));
        }
        for (int i = 1; i <= input.length(); i++) {
            String temp = input.substring(0,i);
            if(!isPalindrome(temp))
            continue;
            tmplist.add(temp);
            helper(result, tmplist, input.substring(i,input.length()));
            tmplist.remove(tmplist.size() - 1);
        }
    }

    private boolean isPalindrome(String temp) {
        int start = 0;
        int end = temp.length() - 1;
        while (start <= end) {
            if (temp.charAt(start)!= temp.charAt(end))
                return false;
            start++;
            end--;
        }
        return true;
    }

    private void print() {
        for (List<String> row : result) {
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
            System.out.println("The String after operation is");
            s.print();
            no_of_tests--;
        }
    }
}
