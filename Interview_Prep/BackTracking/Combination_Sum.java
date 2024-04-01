package BackTracking;

import java.util.*;

public class Combination_Sum {

    public List<List<Integer>> result = new ArrayList<>();

    private List<List<Integer>> computeResult(int arr[], int target) {
        Arrays.sort(arr);
        helper(result, new ArrayList<>(), arr, target, 0);
        return result;
    }

    private void helper(List<List<Integer>> result, List<Integer> tmpList, int[] arr, int target, int start) {
        if (target < 0)
            return;
        else if (target == 0) {
            result.add(new ArrayList<>(tmpList));
        } else {
            for (int i = start; i < arr.length; i++) {
                tmpList.add(arr[i]);
                helper(result, tmpList, arr, target - arr[i], i);
                tmpList.remove(tmpList.size() - 1);
            }
        }
    }

    private void print() {
        for (List<Integer> row : result) {
            for (Integer element : row) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        Combination_Sum p = new Combination_Sum();
        while (t-- != 0) {
            int n = sc.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = sc.nextInt();
            }
            int target = sc.nextInt();
            p.computeResult(arr, target);
            p.print();
        }

    }
}
