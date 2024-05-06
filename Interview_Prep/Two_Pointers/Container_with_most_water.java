package Two_Pointers;

import java.util.*;

public class Container_with_most_water {
    public static int maxArea(int[] height) {
        int start = 0, end = height.length - 1, area = 0;
        while (start < end) {
            area = Integer.max(area, Integer.min(height[start], height[end]) * (end - start));
            if (height[start] > height[end])
                end--;
            else
                start++;
        }
        return area;

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
            System.out.println(maxArea(arr));
            no_of_tests--;
        }
    }
}
