package Two_Pointers;

import java.util.Scanner;

public class Two_Sum_input_Sorted_Array {

    public static int[] twoSum(int[] numbers, int target) {
        int start=0,end=numbers.length-1,curr_sum=0;
        int start_index=-1,end_index=-1;
        while(start!=end) {
            curr_sum = numbers[start] + numbers[end];
            if(curr_sum == target)
                return new int[]{start+1,end+1};
            else if(curr_sum > target)
                end--;
            else
                start++;
        }
        return new int[] {start_index,end_index};
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
            int target = sc.nextInt();

            int [] index = twoSum(arr, target);
            for (int i : index) {
                System.out.print(i + " ");
            }
            System.out.println();
            no_of_tests--;
        }
    }
}
