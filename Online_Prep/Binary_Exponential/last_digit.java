import java.util.Scanner;
class last_digit {
    public static long write_solution(long a, long b) {
        long m = 10L;
        long res = 1l;
        while (b > 0) {
            if (b % 2 != 0)
                res = (res * a) % m;
            a = (a * a) % m;
            b >>= 1;
        }
        return res;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int no_of_tests = sc.nextInt();
        while (no_of_tests != 0) {
            long a = sc.nextLong();
            long b = sc.nextLong();
            System.out.println(write_solution(a, b));
            no_of_tests--;
        }
    }
}
