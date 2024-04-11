package Sliding_Window;
import java.util.*;
public class Max_consecutive_Ones {
    public static int longestOnes(int[] nums,int k) {
        int size = 0,start=0,end=0;
        int count_of_zero=0;
        while(end<nums.length) {
            if(nums[end]==0)
                count_of_zero++;
            if(count_of_zero > k) {    
                if(nums[start]==0)           
                count_of_zero--;
                start++;
            }
            size = Integer.max(size, end-start+1);
            end++;
        }
        return size;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int tests = sc.nextInt();
        while (tests-- != 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for(int i=0;i<n;i++)
            arr[i] = sc.nextInt();
            int k = sc.nextInt();
            System.out.println(longestOnes(arr, k));
        }
    }
}
