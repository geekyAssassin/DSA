package Heaps.Easy;

import java.util.*;

class IsHeap {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int testCases = scanner.nextInt();

        while (testCases-- > 0) {
            int n = scanner.nextInt();
            int[] arr = new int[n];
            for (int i = 0; i < n; i++) {
                arr[i] = scanner.nextInt();
            }

            if (isMinHeap(arr, n)) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }

    // Checks if the given array represents a min-heap
    private static boolean isMinHeap(int[] arr, int n) {
        for (int i = 0; i <= (n - 2) / 2; i++) {
            int left = 2 * i + 1;
            int right = 2 * i + 2;

            if (left < n && arr[i] > arr[left]) {
                return false;
            }
            if (right < n && arr[i] > arr[right]) {
                return false;
            }
        }
        return true;
    }
}
