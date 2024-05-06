package Sorting;

import java.util.*;

public class Insert_interval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        ArrayList<int[]> ans = new ArrayList<>();
        int i = 0;
        // add all the intervals ending before newInterval starts
        while (i < intervals.length && intervals[i][1] < newInterval[0]) {
            ans.add(new int[] { intervals[i][0], intervals[i][1] });
            i++;
        }
        // merge all overlapping intervals to one considering newInterval
        while(i < intervals.length && intervals[i][0] <= newInterval[1]) {
            newInterval[0] = Math.min(intervals[i][0], newInterval[0]);
            newInterval[1] = Math.max(intervals[i][1], newInterval[1]);
            i++;
        }
        ans.add(newInterval);// add the union of intervals we got

        // add all the rest
        while(i< intervals.length) {
            ans.add(new int[] { intervals[i][0], intervals[i][1] });
            i++;
        }

        int[][] result = new int[ans.size()][];
        for(int j=0;j<result.length;j++) {
            int a[] = new int[2];
            for(int k=0;k<2;k++) {
                a[k] = ans.get(j)[k];
            }
            result[j] = a;                
        }
        return result;

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        Insert_interval p = new Insert_interval();
        while (t-- != 0) {
            int n = sc.nextInt();
            int[][] arr = new int[n][2];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 2; j++) {
                    arr[i][j] = sc.nextInt();
                }
            }
            int start = sc.nextInt();
            int end = sc.nextInt();
            int[][] result = p.insert(arr, new int[] { start, end });
        }

    }
}
