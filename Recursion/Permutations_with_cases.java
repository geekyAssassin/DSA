import java.util.*;

class Permutations_with_cases {
    Set<String> output = new HashSet<>();
    public void solution(String input) {
        permute(input, input.charAt(0)+"", 1,input.length());
        System.out.println(output);
    }

    public void permute(String input, String sb, int start, int n) {
        if (start >= n) {
            output.add(sb);
            return;
        }
        
        permute(input, sb+" "+input.charAt(start), start+1, n);
        permute(input, sb+""+input.charAt(start), start+1, n);
        
    }

    public static void main(String args[]) {

        int no_of_tests;
        String input;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of tests");
        no_of_tests = sc.nextInt();
        Permutations_with_cases s = new Permutations_with_cases();
        while (no_of_tests != 0) {
            System.out.println("Enter the String");
            input = sc.next();
            s.solution(input);
            System.out.println("The String after operation is" + input);
            no_of_tests--;
        }
    }
}