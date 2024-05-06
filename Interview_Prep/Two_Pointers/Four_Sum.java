package Two_Pointers;

import java.util.*;

public class Four_Sum {

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int i, j, start, end, n = nums.length;
        for (i = 0; i < n - 3; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            for (j = i + 1; j < n - 2; j++) {
                if (j > i+1 && nums[j] == nums[j - 1])
                    continue;
                start = j + 1;
                end = n - 1;
                // To prevert Integer Overflow for cases [100000000,100000000,100000000,100000000]
                long sum = nums[i] + nums[j];
                long curr_diff = ((long) target - sum);
                while (start < end) {
                    
                    if (nums[start]+nums[end] == curr_diff) {
                        List<Integer> temp = new ArrayList<>();
                        temp.add(nums[i]);
                        temp.add(nums[j]);
                        temp.add(nums[start]);
                        temp.add(nums[end]);
                        res.add(new ArrayList<>(temp));
                        start++;
                        end--;
                        while (nums[start] == nums[start - 1] && start < end)
                            start++;
                    } else if (nums[start]+nums[end] > curr_diff)
                        end--;
                    else
                        start++;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- != 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++)
                arr[i] = sc.nextInt();
            int target = sc.nextInt();
            List<List<Integer>> res = fourSum(arr, target);
            for (List<Integer> list : res) {
                for (Integer num : list) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }
        }
    }

}
