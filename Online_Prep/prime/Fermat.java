import java.io.*;
import java.math.*;

public class Fermat {

    static int power(int a,int n,int p) {
        int res = 1;
        a = a%p;
        while(n>0) {
            // If n is odd, multiply 'a' with result
            if ((n & 1) == 1)
                res = (res * a) % p;

            // n must be even now
            n = n >> 1; // n = n/2
            a = (a * a) % p;
        }
        return res;
    }

    static boolean isPrime(int n,int k) {
        if(n <=1 || n == 4) return false;
        if(n<=3) return true;

        while(k > 0) {
            int a = 2 + (int)(Math.random() % (n - 4));

            if(power(a,n-1,n)!=1)
            return false;

            k--;
        }
        return true;
    }
    public static void main(String ars[]) {
        int k = 3;
        if(isPrime(15, k))
        System.out.println("True");
        else
        System.out.println("False");
    }
}
