import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Anna_and_Valentines_Day {

    public static String getWinner(int n, int m, List<Long> numbers) {
        List<Integer> trailingZeros = new ArrayList<>();
        int totalDigits = 0;
        for (long num : numbers) {
            int trailing = 0;
            while (num % 10 == 0) {
                trailing++;
                num = num / 10;
                totalDigits++;
            }
            trailingZeros.add(trailing);
            while (num > 0) {
                num = num / 10;
                totalDigits++;
            }
        }
        trailingZeros.sort(Collections.reverseOrder());
        int removed = 0;
        for (int i = 0; i < trailingZeros.size(); i += 2) {
            removed += trailingZeros.get(i);
        }
        if (totalDigits - removed > m)
            return "Sasha";
        else
            return "Anna";

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- != 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();
            List<Long> numbers = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                numbers.add(sc.nextLong());
            }
            Collections.sort(numbers, Collections.reverseOrder());
            System.out.println(getWinner(n, m, numbers));
        }
    }
}
