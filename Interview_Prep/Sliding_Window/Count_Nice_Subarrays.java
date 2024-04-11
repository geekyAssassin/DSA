package Sliding_Window;

import java.util.*;

public class Count_Nice_Subarrays {
    public static int numberOfSubarrays(int[] nums, int k) {
        int start = 0, end = 0, count = 0, nice = 0,flag=0;
        while (end < nums.length) {
            if (nums[end] % 2 != 0)
                count++;
            while(count > k) {
                if(nums[start++]%2!=0)
                    count--;
                flag = start;
            }
            if (count == k){
                nice+=(start+1-flag);
                while(nums[start]%2==0) {
                    start++;
                    nice++;
                }
            }
            end++;
        }
        return nice;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        while (test != 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = sc.nextInt();
            int goal = sc.nextInt();
            System.out.println(numberOfSubarrays(arr, goal));
            test--;
        }
    }
}
