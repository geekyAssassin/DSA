package Two_Pointers;

import java.util.*;

public class Boats_To_save_people {
    public static int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int start = 0, end = people.length-1, total=0;
        while(start<=end) {
            if(people[end] + people[start] <= limit)
            {
                start++;
            }
            end--;
            total++;
        }
        return total;
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
            int limit = sc.nextInt();
            System.out.println(numRescueBoats(arr, limit));
            no_of_tests--;
        }
    }
}
