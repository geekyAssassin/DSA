import java.util.Scanner;

class binomial_exponential {
    public long write_solution(long a, long b) {
        if (b == 0) {
            return 1;
        }
        long res = write_solution(a, b / 2);
        if (b % 2 !=0) {
            return res *res * a;
        } else {
            return res * res;
        }

    }

    public static void main(String[] args) {
        int no_of_tests;
        Scanner sc = new Scanner(System.in);
        no_of_tests = sc.nextInt();
        binomial_exponential s = new binomial_exponential();
        while (no_of_tests != 0) {
            long a = sc.nextLong();
            long b = sc.nextLong();
            System.out.println(s.write_solution(a, b));
            no_of_tests--;
        }
    }
}