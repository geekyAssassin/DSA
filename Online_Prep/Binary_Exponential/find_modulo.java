import java.util.Scanner;

public class find_modulo {
    public long write_solution(long a, long b, long m) {
        a = a % m;
        long res = 1l;
        while (b > 0) {
            if (b % 2 != 0)
                res = res * a % m;
            a = a * a % m;
            b >>= 1;
        }
        return res;

    }

    public static void main(String[] args) {
        int no_of_tests;
        Scanner sc = new Scanner(System.in);
        no_of_tests = sc.nextInt();
        find_modulo s = new find_modulo();
        while (no_of_tests != 0) {
            long a = sc.nextLong();
            long b = sc.nextLong();
            long m = sc.nextLong();
            System.out.println(s.write_solution(a, b, m));
            no_of_tests--;
        }
    }
}