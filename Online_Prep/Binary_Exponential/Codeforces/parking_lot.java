package Codeforces;
import java.util.Scanner;

public class parking_lot {
    public long calculate_exponential(long a, long b) {
        long res = 1;
        while(b > 0) {
            if(b % 2 !=0) {
                res = res * a;
            }
            a = a * a;
            b >>= 1;
        }
        return res;
    }
    public long calculate(int n) {
        long a = 4L;
        long b = n-3;
        long res = calculate_exponential(a,b);
        return 6 * (res * 4) + (n-3) * 9* res;
    }

    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        parking_lot s = new parking_lot();
        System.out.println(s.calculate(n));
    }
}
