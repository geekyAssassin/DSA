package Sorting;

import java.util.*;

class Merge_Intervals {

    private int[][] computeResult(int intervals[][]) {
        Arrays.sort(intervals, (o1, o2) -> o1[0] - o2[0]);
        int previous_start = intervals[0][0];
        int previous_end = intervals[0][1];
        ArrayList<int[]> ans = new ArrayList<>();
        int i = 1;

        while (i < intervals.length) {
            int current_start = intervals[i][0];
            int current_end = intervals[i][1];

            if (current_start <= previous_end) {
                previous_end = Math.max(current_end, previous_end);
            } else {
                ans.add(new int[] { previous_start, previous_end });
                previous_start = current_start;
                previous_end = current_end;
            }
            i++;
        }
        ans.add(new int[] { previous_start, previous_end });
        int[][] result = new int[ans.size()][2];
        for (int j = 0; j < ans.size(); j++) {
            int[] a = new int[2];
            for (int k = 0; k < 2; k++) {
                a[k] = ans.get(j)[k];
            }
            result[j] = a;
        }
        return result;
    }

    private void print(int[][] result) {
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < 2; j++) {
                System.out.print(result[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        Merge_Intervals p = new Merge_Intervals();
        while (t-- != 0) {
            int n = sc.nextInt();
            int[][] arr = new int[n][2];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 2; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
            int[][] result = p.computeResult(arr);
            p.print(result);
        }

    }
}