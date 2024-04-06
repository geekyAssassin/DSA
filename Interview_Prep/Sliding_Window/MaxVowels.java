package Sliding_Window;

import java.util.Scanner;

public class MaxVowels {
    public int write_solution(String s, int k){
        int count=0,i=0,n=s.length(),j=0, max_count = Integer.MIN_VALUE;
        while(j<n) {
            if(s.charAt(j) == 'a' || s.charAt(j) == 'e' || s.charAt(j) == 'i' || s.charAt(j) == 'o' || s.charAt(j) == 'u')
            count++;
            if(j-i+1<k)
            j++;
            else {
                if(count > max_count)
                max_count = count;

                if(s.charAt(i) == 'a' || s.charAt(i) == 'e' || s.charAt(i) == 'i' || s.charAt(i) == 'o' || s.charAt(i) == 'u')
                    count--;
                i++;
                j++;
            }
        }
        return max_count;
    }
    public static void main(String[] args) {
        int no_of_tests;
        Scanner sc = new Scanner(System.in);
        no_of_tests = sc.nextInt();
        MaxVowels s = new MaxVowels();
        while(no_of_tests!=0){
            String input = sc.next();
            int k = sc.nextInt();
            System.out.println(s.write_solution(input, k));
            no_of_tests--;
        }
    }
    
}
