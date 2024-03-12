import java.util.*;

class Letter_Case_Permutations {
    public void solution(String input) {
        List<String> output = new ArrayList <>();
        permute(input,0,output);
        System.out.println(output);
    }

    public void permute(String input,int start,List<String> output) {
        if(start == input.length())
        {
            output.add(input);
            return;
        }
        StringBuilder sb = new StringBuilder(input);
        if(Character.isDigit(sb.charAt(start))){
            permute(sb.toString(),start+1,output);
        }
        else {
            sb.setCharAt(start, Character.toLowerCase(sb.charAt(start)));
            permute(sb.toString(), start+1, output);
            sb.setCharAt(start, Character.toUpperCase(sb.charAt(start)));
            permute(sb.toString(), start+1, output);
        }
        
    }
    public static void main(String args[]) {

        int no_of_tests;
        String input;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of tests");
        no_of_tests = sc.nextInt();
        Letter_Case_Permutations s = new Letter_Case_Permutations();
        while (no_of_tests != 0) {
            System.out.println("Enter the String");
            input = sc.next();
            s.solution(input);
            System.out.println("The String after operation is"+input);
            no_of_tests--;
        }
    }
}