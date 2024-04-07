package Sliding_Window;
import java.util.*;
public class Max_Avg_Subarray {
    private double write_solution(int[] arr, int n, int k) {
        int i=0,j=0;
        double max_sum = Integer.MIN_VALUE, sum=0;
        while(j<n){
            if(j-i+1<k) {
                sum+=arr[j];
                j++;
            } else {
                sum+=arr[j];
                if(sum>max_sum)
                    max_sum = sum;
                sum-=arr[i];
                i++;
                j++;
            }
        }
        return max_sum/k;
    }
    public static void main(String[] args) {
        int no_of_tests;
        Scanner sc = new Scanner(System.in);
        no_of_tests = sc.nextInt();
        Max_Avg_Subarray s = new Max_Avg_Subarray();
        while(no_of_tests!=0){
            int n = sc.nextInt();
            int [] arr = new int[n];
            for(int i=0;i<n;i++)
            arr[i] = sc.nextInt();
            int k = sc.nextInt();
            System.out.println(s.write_solution(arr, n, k));
            no_of_tests--;
        }
    }
}
