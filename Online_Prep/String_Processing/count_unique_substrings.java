package String_Processing;

import java.util.*;

class count_unique_substrings {
    public int count_different_substrings(String input) {
        int count = 0;
        int n = input.length();

        int p = 31; // Nearest Prime Number to total no. of alphabets
        long m = 1000000009L;

        long[] pow = new long[n];
        pow[0] = 1;

        for (int i = 1; i < n; i++) {
            pow[i] = (pow[i-1]*p) % m;
        }

        long[] hash = new long[n+1];
        Arrays.fill(hash, 0);
        for(int i=0;i<n;i++) {
            hash[i+1] = (hash[i] + (input.charAt(i) - 'a' + 1) * pow[i]) % m;
        }

        for(int j=1;j<=n;j++) {
            Set<Long> hs = new HashSet<>();
            for(int i=0;i<=n-j;i++) {
                long current_hash = (hash[i+j] + m -hash[i])% m;
                current_hash = (current_hash*pow[n-i-1]) %m;
                hs.add(current_hash);
            }
            count+= hs.size();
        }

        return count;
    }

    public static void main(String args[]) {

        int no_of_tests;
        String input;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of tests");
        no_of_tests = sc.nextInt();
        count_unique_substrings s = new count_unique_substrings();
        while (no_of_tests != 0) {
            System.out.println("Enter the String");
            input = sc.next();
            
            System.out.println(s.count_different_substrings(input));
            no_of_tests--;
        }
    }
}