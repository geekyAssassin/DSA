package Sliding_Window;

import java.util.*;

public class Minimun_Size_Subarray {
    public int write_solution(int[] nums, int target) {
        int size = Integer.MAX_VALUE, start = 0, end = 0, n = nums.length, sum = 0;
        while (end < n) {
            sum += nums[end++];
            while (sum >= target) {
                size = Integer.min(size, end - start);
                sum -= nums[start++];
            }
        }
        if (size != Integer.MAX_VALUE)
            return size;
        else
            return 0;
    }

    public static void main(String[] args) {
        int no_of_tests, length;
        Scanner sc = new Scanner(System.in);
        no_of_tests = sc.nextInt();
        Minimun_Size_Subarray s = new Minimun_Size_Subarray();
        while (no_of_tests != 0) {
            length = sc.nextInt();
            int[] arr = new int[length];
            for (int i = 0; i < length; i++) {
                arr[i] = sc.nextInt();
            }
            int target = sc.nextInt();
            System.out.println(s.write_solution(arr, target));
            no_of_tests--;
        }
    }
}
