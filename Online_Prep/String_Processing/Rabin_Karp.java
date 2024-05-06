package String_Processing;

import java.util.*;

public class Rabin_Karp {

    private List<Integer> rabin_karp(String pattern, String text) {
        List<Integer> occurences = new ArrayList<>();
        int p = 31;
        long m = 1000000009;
        int M = pattern.length();
        int N = text.length();


        long[] pow = new long[N];pow[0]=1;
        
        for(int i=1;i<N;i++)
            pow[i] = (pow[i-1]*p)%m;
        
        long[] hash_text = new long[N+1];
        hash_text[0]=0;
        for(int i=0;i<N;i++)
            hash_text[i+1] = (hash_text[i] + (text.charAt(i) - 'A' + 1)*pow[i])%m;
        long hash_pattern = 0;
        for(int i=0;i<M;i++)
            hash_pattern = (hash_pattern + (pattern.charAt(i) - 'A' + 1)*pow[i])%m;

        for(int i=0;i+M-1<N;i++) {
            long cur_hash = (hash_text[i+M] - hash_text[i] + m)%m;
            if(cur_hash == hash_pattern * pow[i]%m)
                occurences.add(i);
        }
        return occurences;
       
    }
    public static void main(String[] args) {
        int no_of_tests;
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of tests");
        no_of_tests = sc.nextInt();
        Rabin_Karp s = new Rabin_Karp();
        while (no_of_tests != 0) {
            System.out.println("Enter the Pattern and the Text");
            String pattern = sc.next();
            String text = sc.next();
            
            List<Integer> occurences = s.rabin_karp(pattern, text);
            for (int start: occurences) {
                System.out.print(start + " ");                
            }
            System.out.println();
            no_of_tests--;
        }
    }

    
    
}
