package Sliding_Window;

import java.util.*;

public class Longest_Subarray_with_Deff_less_than_k {
    public static int longestSubarray(int[] nums, int limit) {
        int start = 0, end = 0, size = 0, curr_diff = 0;
        while (end < nums.length) {
            curr_diff = Math.abs(nums[start] - nums[end]);
            if (curr_diff > limit) {
                curr_diff = nums[start];
                start++;
            } else
                size = Integer.max(size, end - start + 1);
            end++;
        }
        return size;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int test = sc.nextInt();
        while (test != 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = sc.nextInt();
            int limit = sc.nextInt();
            System.out.println(longestSubarray(arr, limit));
            test--;
        }

    }
}
