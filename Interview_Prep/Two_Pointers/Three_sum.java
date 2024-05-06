package Two_Pointers;

import java.util.*;

public class Three_sum {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int i, j, k, n = nums.length;
        Arrays.sort(nums);
        for (i = 0; i < n-2; i++) {
            if( (i>0) && nums[i] == nums[i-1]){
                continue;
            }
            j = i + 1;
            k = n - 1;
            while (j < k) {
                if (nums[i] + nums[j] + nums[k] == 0) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(nums[k]);
                    res.add(new ArrayList<>(temp));
                    j++;
                    k--;
                    while (nums[j] == nums[j - 1] && j < k)
                        j++;
                } else if (nums[i] + nums[j] + nums[k] > 0)
                    k--;
                else
                    j++;
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
            List<List<Integer>> res = threeSum(arr);
            for (List<Integer> list : res) {
                for (Integer num : list) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }
        }
    }
}