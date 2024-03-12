import java.util.*;

class Permutations_Of_String {
    public void solution(String input) {
        Set<String> output = new HashSet<String>();
        permute(input,0,output);
        System.out.println(output);
    }

    public void permute(String input, int start, Set<String> output) {
        if(start >=input.length()) {
            output.add(input);
            return;
        }

        for(int i=0;i<input.length();i++){
            StringBuilder sb = new StringBuilder(input);
            sb.setCharAt(i, input.charAt(start));
            sb.setCharAt(start, input.charAt(i));
            permute(sb.toString(), start+1, output);
            sb.setCharAt(i, input.charAt(start));
            sb.setCharAt(start, input.charAt(i));
        }
    }

    public static void main(String args[]) {

        int no_of_tests;
        String input;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of tests");
        no_of_tests = sc.nextInt();
        Permutations_Of_String s = new Permutations_Of_String();
        while (no_of_tests != 0) {
            System.out.println("Enter the String");
            input = sc.next();
            s.solution(input);
            System.out.println("The String after operation is"+input);
            no_of_tests--;
        }
    }
}