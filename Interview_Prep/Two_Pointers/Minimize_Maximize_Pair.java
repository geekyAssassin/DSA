package Two_Pointers;

import java.util.*;

public class Minimize_Maximize_Pair {
    public static int minPairSum(int[] nums) {
        Arrays.sort(nums);
        int max_sum = 0 , start=0,end = nums.length -1;
        while (start < end) {
            if(nums[start] + nums[end] > max_sum)
                max_sum = nums[start] + nums[end];
            start++;
            end--;
        }
        return max_sum;
    }
    public static void main(String[] args) {
        int no_of_tests, length;
        Scanner sc = new Scanner(System.in);
        no_of_tests = sc.nextInt();
        while (no_of_tests != 0) {
            length = sc.nextInt();
            int[] arr = new int[length];
            for (int i = 0; i < length; i++) {
                arr[i] = sc.nextInt();
            }
            System.out.println(minPairSum(arr));
            no_of_tests--;
        }
    }
}
