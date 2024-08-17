package String_Processing;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Good_Substrings {
    public int count_good_substrings(String input, String bad, int k) {
        
        Set<Long> current_hashes = new HashSet<>();
        int n = input.length();

        int p = 31; // Nearest Prime Number to total no. of alphabets
        long m = 1000000009L;

        for(int j=0;j<n;j++) {
            int badCount=0;
            long hash = 0;
            long pow =1;         
            for(int i=j;i<n;i++) {
                badCount+= bad.charAt(input.charAt(i) - 'a')== '0' ? 1: 0;
                if(badCount > k)
                    break;
                hash = (hash + (input.charAt(i) - 'a' + 1) * pow) % m;
                pow = (p* pow)%m;
                current_hashes.add(hash);
            }
        }

        return current_hashes.size();
    }

    public static void main(String args[]) {

        int no_of_tests;
        String input;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of tests");
        no_of_tests = sc.nextInt();
        Good_Substrings s = new Good_Substrings();
        while (no_of_tests != 0) {
            System.out.println("Enter the String");
            input = sc.next();
            String bad = sc.next();
            int k = sc.nextInt();
            
            System.out.println(s.count_good_substrings(input, bad, k));
            no_of_tests--;
        }
    }
    
}
