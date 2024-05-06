package Two_Pointers;

import java.util.*;

public class Next_Permutation {
    public static void nextPermutation(int[] nums) {
        int pivot = getPeak(nums) - 1;
        if (pivot != -1) {
            int pos_of_right_most_successor = getRightMostSuccessor(nums, pivot);
            swap(nums, pivot, pos_of_right_most_successor);
        }
        reverse(nums, pivot + 1);

    }

    public static void swap(int[] nums, int pivot, int pos_of_right_most_successor) {
        int t = nums[pivot];
        nums[pivot] = nums[pos_of_right_most_successor];
        nums[pos_of_right_most_successor] = t;
    }

    public static void reverse(int[] nums, int start) {
        int end = nums.length - 1;
        while (start < end) {
            swap(nums, start, end);
            start++;
            end--;
        }
    }

    public static int getRightMostSuccessor(int[] nums, int pivot) {
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[pivot] < nums[i])
                return i;
        }
        return -1;
    }

    public static int getPeak(int[] nums) {
        int n = nums.length;
        for (int i = n - 1; i > 0; i--) {
            if (nums[i - 1] < nums[i])
                return i;
        }
        return 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();

        while (t-- != 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            nextPermutation(arr);
            for (int i : arr) {
                System.out.print(i);
            }
        }
    }

}
