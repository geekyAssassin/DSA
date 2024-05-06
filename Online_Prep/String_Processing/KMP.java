package String_Processing;

import java.util.*;

public class KMP {

    private int[] generatePrefix(String pattern) {
        int m = pattern.length();
        int[] result = new int[m];
        result[0]=0;
        for(int i=1;i<m;i++) {
            int j = result[i-1];
            while(j>0 && pattern.charAt(i)!=pattern.charAt(j))
                j = result[j-1];
            if(pattern.charAt(i) == pattern.charAt(j))
                j++;
            result[i]=j;            
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the no of tests");
        int tests = sc.nextInt();
        KMP k = new KMP();
        while(tests--!=0) {
            System.out.println("Enter Pattern and text");
            String pattern = sc.next();
            // String text = sc.next();
            int[] prefix = k.generatePrefix(pattern);
            for (Integer integer : prefix) {
                System.out.print(integer);                
            }
            System.out.println();
        }
    }
    
}
