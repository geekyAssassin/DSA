package Sliding_Window;

import java.util.*;

public class No_of_Avg_Subarray {
    private double write_solution(int[] arr, int k, int threshold) {
        int i = 0, j = 0, n = arr.length, sum = 0, count = 0;
        while (j < n) {
            if (j - i + 1 < k) {
                sum += arr[j];
                j++;
            } else {
                sum += arr[j];
                if (sum / k >= threshold)
                    count++;
                sum -= arr[i];
                i++;
                j++;
            }
        }
        System.gc();
        return count;
    }

    public static void main(String[] args) {
        int no_of_tests;
        Scanner sc = new Scanner(System.in);
        no_of_tests = sc.nextInt();
        No_of_Avg_Subarray s = new No_of_Avg_Subarray();
        while (no_of_tests != 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = sc.nextInt();
            int k = sc.nextInt();
            int threshold = sc.nextInt();
            System.out.println(s.write_solution(arr, k, threshold));
            no_of_tests--;
        }
    }
}
