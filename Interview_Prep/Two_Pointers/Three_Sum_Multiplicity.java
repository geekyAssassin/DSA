package Two_Pointers;

import java.util.*;

public class Three_Sum_Multiplicity {
    public static int threeSumMulti(int[] arr, int target) {
        long res = 0;
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int a : arr) {
            count.put(a, count.getOrDefault(a, 0) + 1);
        }
        for (Integer element1 : count.keySet()) {
            for (Integer element2 : count.keySet()) {
                int k = target - element1 - element2;
                if (count.containsKey(k)) {
                    int frequency1 = count.get(element1);
                    int frequency2 = count.get(element2);
                    int frequency3 = count.get(k);
                    if (element1 == element2 && element2 == k)
                        res += (frequency1 * (frequency1 - 1) * (frequency1 - 2)) / 6;
                    else if (element1 == element2 && element2 != k)
                        res += (frequency1 * (frequency1 - 1) * (frequency3)) / 2;
                    else if (element1 < element2 && element2 < k)
                        res += (frequency1 * frequency2 * frequency3);
                }

            }
        }
        return (int) (res % (1e9 + 7));

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
            System.out.println(threeSumMulti(arr, target));
            no_of_tests--;
        }
    }
}