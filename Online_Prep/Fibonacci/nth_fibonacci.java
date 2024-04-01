import java.util.Scanner;

public class nth_fibonacci {
     long mod = 1000000007L;
     long fib(long n) {
        long[][] m = {{1,1},{1,0}};
        if(n==0)
            return 0;
        p(m,n-1);
        return m[0][0];
    }
    void p(long[][]m, long n) {
        if(n==0 || n ==1)
            return;
        long[][] r = {{1,1},{1,0}};
        p(m,n/2);
        mu(m, m);

        if(n%2!=0)
            mu(m, r);
    }
    
    void mu(long[][] m, long [][]r) {
        long w = (m[0][0]*r[0][0] + m[0][1]*r[1][0])%mod;
        long x = (m[0][0]*r[0][1] + m[0][1]*r[1][1])%mod;
        long y = (m[1][0]*r[0][0] + m[1][1]*r[1][0])%mod;
        long z = (m[1][0]*r[1][0] + m[1][1]*r[1][1])%mod;
        m[0][0] = w;m[0][1] = x;m[1][0] = y;m[1][1] = z;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        nth_fibonacci s = new nth_fibonacci();
        long n = sc.nextLong();
        System.out.println(s.fib(n));
    }
}