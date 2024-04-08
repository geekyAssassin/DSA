package Sliding_Window;

import java.util.*;

public class Binary_Subarrays_with_Sum {
    public static int numSubarraysWithSum(int[] nums, int goal) {
        int total = 0, start = 0, end = 0, count = 0,temp=0;
        while (end < nums.length) {
            total += nums[end];
            if(nums[end] ==1)
            temp=0; // Used to store the previus valid subarrays
            while (total == goal && start<=end) {
                total -= nums[start];
                start++;
                temp++;
            }
            if (total > goal) {
                total -= nums[start];
                start++;
            }
            count+=temp;
            end++;
        }
        return count;

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
            System.out.println(numSubarraysWithSum(arr, goal));
            test--;
        }
    }
}